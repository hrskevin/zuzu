package zuzu.parser;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.v4.runtime.TokenStream;

public abstract class ZuzuParserBase extends Parser
{
    private ZuzuTokenFactory _tokenFactory;

    /*--------------
     * Construction
     */

    public ZuzuParserBase(TokenStream input)
    {
        super(input);
        TokenFactory<?> factory = input.getTokenSource().getTokenFactory();
        if (!(factory instanceof ZuzuTokenFactory))
        {
            factory = new ZuzuTokenFactory();
        }
        setTokenFactory(factory);
    }

    /*----------------
     * Parser methods
     */

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

}
