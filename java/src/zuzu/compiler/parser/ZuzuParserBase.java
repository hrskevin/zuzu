package zuzu.compiler.parser;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.Nullable;

public abstract class ZuzuParserBase extends Parser
{
    private ZuzuTokenFactory _tokenFactory;

    /*--------------
     * Construction
     */

    public ZuzuParserBase(@Nullable TokenStream input)
    {
        super(input);
        if (input != null)
        {
            TokenFactory<?> factory = input.getTokenSource().getTokenFactory();
            if (!(factory instanceof ZuzuTokenFactory))
            {
                factory = new ZuzuTokenFactory();
            }
            setTokenFactory(factory);
        }
    }

    /*----------------
     * Parser methods
     */

    @Override
    public ZuzuToken consume()
    {
        return (ZuzuToken) super.consume();
    }

    public ZuzuTokenFactory getTokenFactory()
    {
        return _tokenFactory;
    }

    @Override
    public void setTokenFactory(TokenFactory<?> factory)
    {
        _tokenFactory = (ZuzuTokenFactory) factory;
        super.setTokenFactory(factory);
    }

    @Override
    public ZuzuToken match(int tokenType)
    {
        return (ZuzuToken) super.match(tokenType);
    }

    @Override
    public ZuzuToken matchWildcard() throws RecognitionException
    {
        return (ZuzuToken) super.matchWildcard();
    }
}
