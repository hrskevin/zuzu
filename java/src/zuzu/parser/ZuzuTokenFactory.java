package zuzu.parser;

import static zuzu.parser.ZuzuLexer.ID;
import static zuzu.parser.ZuzuLexer.SUBSTITUTION;

import org.antlr.runtime.Token;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Pair;

public final class ZuzuTokenFactory implements TokenFactory<ZuzuToken>
{
	private ZuzuIdentifier.HygieneTag _hygieneTag = null;

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
		return create(null, type, text, Token.DEFAULT_CHANNEL, 0, 0, 0, 0);
	}
}
