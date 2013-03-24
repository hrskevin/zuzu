package zuzu.compiler.parser;

import static zuzu.compiler.parser.ZuzuLexer.*;

import org.antlr.runtime.Token;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.Pair;

import zuzu.lang.type.NullType;
import zuzu.lang.type.TypeReference;
import zuzu.lang.type.Types;

public final class ZuzuTokenFactory implements TokenFactory<ZuzuToken>
{
    private ZuzuIdentifier.HygieneTag _hygieneTag = null;

    /*----------------------
     * TokenFactory methods
     */

    @Override
    public ZuzuToken create(Pair<TokenSource, CharStream> source,
        int type,
        String text,
        int channel,
        int start,
        int stop,
        int line,
        int charPositionInLine)
    {
        if (text == null)
        {
            text = source.b.getText(new Interval(start, stop));
        }

        TypeReference literalType = null;
        Object literalValue = null;

        switch (type)
        {
        case ID:
        case SUBSTITUTION:
            return new ZuzuIdentifier(source, type, text, channel, start, stop, line, charPositionInLine, _hygieneTag);

        case CHAR:
            literalType = Types.CHAR;
            literalValue = new Character(processEscapes(text, 1, text.length() - 1).charAt(0));
            break;

        case STRING:
            literalType = Types.STRING;
            literalValue = processEscapes(text, 1, text.length() - 1);
            break;

        case TEXT_FRAGMENT:
            literalType = Types.STRING;
            literalValue = processEscapes(text, 0, text.length());
            break;

        case VERBATIM_STRING:
            literalType = Types.STRING;
            literalValue = text.substring(2, text.length() - 2);
            break;

        case DECIMAL_INTEGER:
        case HEX_INTEGER:
        {
            if (text.endsWith("L"))
            {
                literalType = Types.LONG;
                literalValue = Long.decode(text.substring(0, text.length() - 1));
            }
            else
            {
                literalType = Types.INT;
                literalValue = Integer.decode(text);
            }
            break;
        }

        case ZuzuLexer.FIXED_FLOAT:
        case ZuzuLexer.SCIENTIFIC_FLOAT:
        {
            String str = text;
            if (str.endsWith("F"))
            {
                literalType = Types.FLOAT;
                literalValue = new Float(text.substring(0, text.length() - 1));
            }
            else
            {
                literalType = Types.DOUBLE;
                literalValue = new Double(text);
            }
            break;
        }

        case ZuzuLexer.TRUE:
            literalType = Types.BOOL;
            literalValue = Boolean.TRUE;
            break;

        case ZuzuLexer.FALSE:
            literalType = Types.BOOL;
            literalValue = Boolean.FALSE;
            break;

        case ZuzuLexer.NULL:
            literalType = NullType.INSTANCE;
            break;

        case ZuzuLexer.TAGGED_STRING:
        {
            int tstart = text.indexOf('"') + 1;
            int tend = text.lastIndexOf('"');
            literalType = Types.STRING;
            literalValue = text.substring(tstart, tend);
            break;
        }

        default:
        }

        if (literalType != null)
        {
            return new ZuzuLiteralToken(source, type, text, channel, start, stop, line, charPositionInLine,
                literalType, literalValue);
        }

        return new ZuzuToken(source, type, text, channel, start, stop, line, charPositionInLine);
    }

    @Override
    public ZuzuToken create(int type, String text)
    {
        // FIXME: can't use null source
        return create(null, type, text, Token.DEFAULT_CHANNEL, 0, 0, 0, 0);
    }

    /*--------------------------
     * ZuzuTokenFactory methods
     */

    /**
     * The current hygiene tag that will be set on all identifiers created by this factory. Is null
     * by default.
     * 
     * @see #setHygieneTag
     */
    ZuzuIdentifier.HygieneTag getHygieneTag()
    {
        return _hygieneTag;
    }

    /**
     * Sets hygiene tag for new identifiers.
     * 
     * @see #getHygieneTag()
     */
    void setHygieneTag(ZuzuIdentifier.HygieneTag tag)
    {
        _hygieneTag = tag;
    }

    private String processEscapes(String text, int start, int end)
    {
        int firstEscape = text.indexOf('\\', start);
        if (firstEscape < start || firstEscape >= end)
        {
            return text.substring(start, end);
        }

        StringBuilder builder = new StringBuilder(end - start);
        builder.append(text, start, firstEscape);

        for (int i = firstEscape; i < end; ++i)
        {
            char c = text.charAt(i);
            if (c == '\\')
            {
                if (++i == end)
                    break;
                c = text.charAt(i);
                switch (c)
                {
                case 't':
                    c = '\t';
                    break;
                case 'r':
                    c = '\r';
                    break;
                case 'n':
                    c = '\n';
                    break;
                case 'u':
                    if (++i == end)
                        break;
                    c = (char) Short.parseShort(text.substring(i, Math.min(i + 4, end)), 16);
                }
            }
            builder.append(c);
        }
        return builder.toString();
    }

}
