package zuzu.compiler.parser;

import static zuzu.compiler.parser.ZuzuLexer.*;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Pair;

public class ZuzuToken extends CommonToken
{
    /*--------------
     * Construction
     */

    /**
	 * 
	 */
    private static final long serialVersionUID = 2127750656582292033L;

    ZuzuToken(Pair<TokenSource, CharStream> source,
        int type,
        String text,
        int channel,
        int start,
        int stop,
        int line,
        int charPositionInLine)
    {
        super(source, type, channel, start, stop);
        this.text = text;
        this.line = line;
        this.charPositionInLine = charPositionInLine;
    }

    ZuzuToken(ZuzuToken token)
    {
        this(token.source, token.type, token.text, token.channel, token.start, token.stop, token.line,
            token.charPositionInLine);
    }

    /*--------------------
     * Token type methods
     */

    public static boolean isIdentifier(int tokenType)
    {
        switch (tokenType)
        {
        case ID:
        case SUBSTITUTION:
            return true;
        default:
            return false;
        }
    }

    public static boolean isLiteral(int tokenType)
    {
        switch (tokenType)
        {
        case CHAR:
        case STRING:
        case VERBATIM_STRING:
        case DECIMAL_INTEGER:
        case TAGGED_STRING:
        case TEXT_FRAGMENT:
        case HEX_INTEGER:
        case FIXED_FLOAT:
        case SCIENTIFIC_FLOAT:
        case TRUE:
        case FALSE:
        case NULL:
            return true;
        default:
            break;
        }

        return false;
    }

    /*-------------------
     * ZuzuToken methods
     */

    public int getColumn()
    {
        return getCharPositionInLine();
    }

    public String identifierName()
    {
        switch (getType())
        {
        case ID:
            return getText();

        case ANNOTATION:
        case SUBSTITUTION:
            return getText().substring(1);

        default:
            break;
        }

        return "";
    }

    public boolean isComment()
    {
        switch (getType())
        {
        case COMMENT:
        case ML_COMMENT:
        case TEXT_COMMENT:
        case TEXT_ML_COMMENT:
        case TAGGED_COMMENT:
            return true;
        default:
            break;
        }

        return false;
    }

    public boolean isIdentifier()
    {
        return getType() == ID;
    }

    @SuppressWarnings("boxing") public Object literalValue()
    {
        final int type = getType();
        String text = getText();

        switch (type)
        {
        case CHAR:
            return processEscapes(text, 1, text.length() - 1).charAt(0);

        case STRING:
            return processEscapes(text, 1, text.length() - 1);

        case TEXT_FRAGMENT:
            return processEscapes(text, 0, text.length());

        case VERBATIM_STRING:
            return text.substring(2, text.length() - 2);

        case DECIMAL_INTEGER:
        case HEX_INTEGER:
            if (text.charAt(text.length() - 1) == 'L')
            {
                return Long.decode(text.substring(0, text.length() - 1));
            }
            long l = Long.decode(text);
            int i = (int) l;
            return i == l ? i : l;

        case ZuzuLexer.FIXED_FLOAT:
        case ZuzuLexer.SCIENTIFIC_FLOAT:
            if (text.charAt(text.length() - 1) == 'F')
            {
                return Float.parseFloat(text.substring(0, text.length() - 1));
            }
            else
            {
                double d = Double.parseDouble(text);
                float f = (float) d;
                return f == d ? f : d;
            }

        case ZuzuLexer.TRUE:
            return true;

        case ZuzuLexer.FALSE:
            return false;

        case ZuzuLexer.NULL:
            return null;

        case ZuzuLexer.TAGGED_STRING:
            int start = text.indexOf('"') + 1;
            int end = text.lastIndexOf('"');
            return text.substring(start, end);

        default:
            break;
        }

        return void.class;
    }

    private String processEscapes(String str, int start, int end)
    {
        int firstEscape = text.indexOf('\\', start);
        if (firstEscape < start || firstEscape >= end)
        {
            return str.substring(start, end);
        }

        StringBuilder builder = new StringBuilder(end - start);
        builder.append(str, start, firstEscape);

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
