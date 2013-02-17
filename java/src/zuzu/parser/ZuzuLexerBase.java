package zuzu.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;

public abstract class ZuzuLexerBase extends Lexer
{
	private String _endTag = "";
	
	public ZuzuLexerBase(CharStream input)
	{
		super(input);
	}
	
	protected void startTaggedToken(int mode)
	{
		String str = getText();
		int end = str.length() - 1;
		_endTag = str.charAt(end) + str.substring(1, end) + str.charAt(0);
		
		more();
		pushMode(mode);
	}
	
	protected boolean isEndTag()
	{
		return getText().endsWith(_endTag);
	}
}
