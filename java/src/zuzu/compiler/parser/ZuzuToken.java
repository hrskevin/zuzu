package zuzu.compiler.parser;

import static zuzu.compiler.parser.ZuzuLexer.*;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Pair;

import zuzu.lang.type.Type;

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

    /*-------------------
     * ZuzuToken methods
     */

    public int getColumn()
    {
        return getCharPositionInLine();
    }

    public String identifierName()
    {
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
        return false;
    }

    public boolean isLiteral()
    {
        return false;
    }

    public Type literalType()
    {
        return null;
    }

    public Object literalValue()
    {
        return null;
    }
}
