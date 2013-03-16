package zuzu.compiler.parser;

import java.util.ArrayList;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.v4.runtime.TokenSource;

import zuzu.compiler.parser.ZuzuParser.TokenExprContext;

public class ZuzuTokenListSource implements TokenSource
{
    private final ZuzuToken _first;
    private final ArrayList<TokenExprContext> _tokens;
    private int _next = 0;

    ZuzuTokenListSource(ArrayList<TokenExprContext> tokens)
    {
        _tokens = tokens;
        _first = tokens.get(0).token;
    }

    @Override
    public ZuzuToken nextToken()
    {
        return _next < _tokens.size() ? _tokens.get(_next++).token : null;
    }

    @Override
    public int getLine()
    {
        return _first.getLine();
    }

    @Override
    public int getCharPositionInLine()
    {
        return _first.getCharPositionInLine();
    }

    @Override
    public CharStream getInputStream()
    {
        return _first.getInputStream();
    }

    @Override
    public String getSourceName()
    {
        return _first.getTokenSource().getSourceName();
    }

    @Override
    public void setTokenFactory(TokenFactory<?> factory)
    {
    }

    @Override
    public TokenFactory<?> getTokenFactory()
    {
        return _first.getTokenSource().getTokenFactory();
    }

}
