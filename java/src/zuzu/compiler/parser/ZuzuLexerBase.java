package zuzu.compiler.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenFactory;

public abstract class ZuzuLexerBase extends Lexer
{
    private String _endTag = "";

    /*
     * Construction
     */

    public ZuzuLexerBase(CharStream input)
    {
        super(input);
        setTokenFactory(new ZuzuTokenFactory());
    }

    /*----------------
     * Lexer methods
     */

    @Override
    public ZuzuTokenFactory getTokenFactory()
    {
        return (ZuzuTokenFactory) super.getTokenFactory();
    }

    @Override
    public void setTokenFactory(TokenFactory<?> factory)
    {
        if (!(factory instanceof ZuzuTokenFactory))
        {
            ZuzuTokenFactory.class.cast(factory);
        }

        super.setTokenFactory(factory);
    }

    /*-----------------------
     * ZuzuLexerBase methods
     */

    /*
     * Helper methods for use in rule actions and predicates.
     */
    protected void startTaggedToken(int mode, boolean deferToken)
    {
        String str = getText();
        int end = str.length() - 1;
        char startChar = str.charAt(end);
        char endChar = (startChar == '{') ? '}' : startChar;
        _endTag = endChar + str.substring(1, end) + str.charAt(0);

        if (deferToken)
        {
            more();
        }
        pushMode(mode);
    }

    protected boolean isEndTag()
    {
        return getText().endsWith(_endTag);
    }

    protected boolean isExactEndTag()
    {
        return getText().equals(_endTag);
    }

}
