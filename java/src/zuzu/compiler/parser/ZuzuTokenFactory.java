package zuzu.compiler.parser;

import static zuzu.compiler.parser.ZuzuLexer.ID;
import static zuzu.compiler.parser.ZuzuLexer.SUBSTITUTION;

import org.antlr.runtime.Token;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.Pair;

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
        switch (type)
        {
        case ID:
        case SUBSTITUTION:
            return new ZuzuIdentifier(source, type, text, channel, start, stop, line, charPositionInLine, _hygieneTag);
        default:
            return new ZuzuToken(source, type, text, channel, start, stop, line, charPositionInLine);
        }
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
}
