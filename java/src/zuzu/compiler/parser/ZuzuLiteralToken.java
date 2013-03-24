package zuzu.compiler.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Pair;

import zuzu.lang.type.Type;
import zuzu.lang.type.TypeReference;

public class ZuzuLiteralToken extends ZuzuToken
{
    /*-------
     * State
     */

    private static final long serialVersionUID = 1L;

    private final Type _literalType;
    private final Object _literalValue;

    /*--------------
     * Construction
     */

    ZuzuLiteralToken(Pair<TokenSource, CharStream> source,
        int tokenType,
        String text,
        int channel,
        int start,
        int stop,
        int line,
        int charPositionInLine,
        TypeReference literalType,
        Object literalValue)
    {
        super(source, tokenType, text, channel, start, stop, line, charPositionInLine);
        _literalType = literalType.getType();
        _literalValue = literalValue;
    }

    /*-------------------
     * ZuzuToken methods
     */

    @Override
    public final boolean isLiteral()
    {
        return true;
    }

    @Override
    public Type literalType()
    {
        return _literalType;
    }

    @Override
    public Object literalValue()
    {
        return _literalValue;
    }
}
