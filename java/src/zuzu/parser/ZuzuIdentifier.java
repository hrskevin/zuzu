package zuzu.parser;

import static zuzu.parser.ZuzuLexer.ID;
import static zuzu.parser.ZuzuLexer.SUBSTITUTION;

import java.io.Serializable;
import java.util.UUID;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Pair;

public final class ZuzuIdentifier extends ZuzuToken implements Comparable<ZuzuIdentifier>
{

    /*-------
     * State
     */

    /**
	 * 
	 */
    private static final long serialVersionUID = -4782507432651506546L;

    final private HygieneTag _hygieneTag;
    final private String _name;
    final private String _javaName;

    /*--------------
     * Construction
     */

    ZuzuIdentifier(Pair<TokenSource, CharStream> source,
        int type,
        String text,
        int channel,
        int start,
        int stop,
        int line,
        int charPositionInLine,
        HygieneTag hygieneTag)
    {

        super(source, type, text, channel, start, stop, line, charPositionInLine);
        _hygieneTag = hygieneTag;
        switch (type)
        {
        case ID:
            _name = text.intern();
            break;
        case SUBSTITUTION:
            _name = text.substring(1).intern();
            break;
        default:
            throw new Error("Bad identifier type " + type);
        }
        _javaName = zuzuToJavaIdentifier(_name).intern();
    }

    ZuzuIdentifier(ZuzuIdentifier id, HygieneTag tag)
    {
        super(id);
        _hygieneTag = tag;
        _name = id._name;
        _javaName = id._javaName;
    }

    public static ZuzuIdentifier newUniqueId(String prefix)
    {
        return newUniqueId(prefix, null);
    }

    public static ZuzuIdentifier newUniqueId(String prefix, ZuzuToken sourceToken)
    {
        UUID uuid = UUID.randomUUID();
        String name = String.format("%s$%x", prefix, uuid.getLeastSignificantBits());
        Pair<TokenSource, CharStream> source = null;
        int start = 0, stop = 0, line = 0, col = 0;
        if (sourceToken != null)
        {
            source = new Pair<TokenSource, CharStream>(sourceToken.getTokenSource(), sourceToken.getInputStream());
            start = sourceToken.getStartIndex();
            stop = sourceToken.getStopIndex();
            line = sourceToken.getLine();
            col = sourceToken.getCharPositionInLine();
        }
        return new ZuzuIdentifier(source, ID, name, DEFAULT_CHANNEL, start, stop, line, col, null);
    }

    public ZuzuIdentifier cloneWithHygiene(HygieneTag newTag)
    {
        return new ZuzuIdentifier(this, newTag);
    }

    public ZuzuIdentifier cloneWithoutHygiene()
    {
        return new ZuzuIdentifier(this, null);
    }

    /*----------------
     * Object methods
     */

    @Override
    public boolean equals(Object that)
    {
        return that instanceof ZuzuIdentifier && equalsWithHygiene((ZuzuIdentifier) that);
    }

    public boolean equals(ZuzuIdentifier that)
    {
        return equalsWithHygiene(that);
    }

    @Override
    public int hashCode()
    {
        return _javaName.hashCode();
    }

    /*
     * Comparable methods
     */

    @Override
    public int compareTo(ZuzuIdentifier that)
    {
        return Comparator.HYGIENIC.compare(this, that);
    }

    /*------------------------
     * ZuzuIdentifier methods
     */

    public boolean equalsWithHygiene(ZuzuIdentifier that)
    {
        return this._hygieneTag == that._hygieneTag && equalsWithoutHygiene(that);
    }

    public boolean equalsWithoutHygiene(ZuzuIdentifier that)
    {
        return _javaName.equals(that._javaName);
    }

    public HygieneTag getHygieneTag()
    {
        return _hygieneTag;
    }

    public String getName()
    {
        return _name;
    }

    public String getJavaName()
    {
        return _javaName;
    }

    /*----------------
     * Static methods
     */

    /**
     * Returns Java-compatible version of {@link #identifierName()}:
     * <ul>
     * <li>Converts instances of '-' followed by a lowercase letter to just the uppercase version of
     * the letter (e.g. "get-value" becomes "getValue")</li>
     * <li>Converts identifier ending in '?' by converting the first letter to uppercase, prepending
     * "is" and removing the '?' (e.g. "done?" becomes "isDone").</li>
     * </ul>
     */
    public String zuzuToJavaIdentifier(String idName)
    {
        final boolean hasQuestion = idName.endsWith("?");
        if (hasQuestion || idName.indexOf('-') >= 0)
        {
            StringBuilder builder = new StringBuilder(idName.length() + 1);
            int i = 0, end = idName.length();
            if (hasQuestion)
            {
                --end;
                builder.append("is");
                ++i;
                builder.append(Character.toUpperCase(idName.charAt(0)));
            }
            for (; i < end; ++i)
            {
                char c = idName.charAt(i);
                // Convert "-x" into "-X" (e.g. get-the-value becomes getTheValue).
                if (c == '-')
                {
                    if (++i == end)
                        break;
                    c = Character.toUpperCase(idName.charAt(i));
                }
                builder.append(c);
            }
            idName = builder.toString();
        }
        return idName;
    }

    /*---------------------------------------------------------------------------------
     * Nested classes
     */

    public static final class HygieneTag implements Comparable<HygieneTag>, Serializable
    {
        /**
		 * 
		 */
        private static final long serialVersionUID = -1727224695287710119L;

        private final UUID _id;

        public HygieneTag()
        {
            _id = UUID.randomUUID();
        }

        @Override
        public boolean equals(Object that)
        {
            return _id.equals(that);
        }

        @Override
        public int hashCode()
        {
            return _id.hashCode();
        }

        /**
         * Compares tag with another tag. Returns one if {@code that} is null.
         * 
         * @see #compare
         */
        @Override
        public int compareTo(HygieneTag that)
        {
            return that == null ? 1 : this._id.compareTo(that._id);
        }

        /**
         * Compares two potentially null tags where null tag comes before non-null tags and ordering
         * is otherwise arbitrary (but consistent).
         */
        public static int compare(HygieneTag tag1, HygieneTag tag2)
        {
            if (tag1 == tag2)
            {
                return 0;
            }
            else if (tag1 == null)
            {
                return -1;
            }
            else
            {
                return tag1.compareTo(tag2);
            }
        }
    };

    public static enum Comparator implements java.util.Comparator<ZuzuIdentifier>, Serializable
    {
        HYGIENIC(true), NONHYGIENIC(false);

        private final boolean _isHygienic;

        private Comparator(boolean isHygienic)
        {
            _isHygienic = isHygienic;
        }

        @Override
        public int compare(ZuzuIdentifier id1, ZuzuIdentifier id2)
        {
            int result = id1._javaName.compareTo(id2._javaName);
            if (result == 0 && _isHygienic)
            {
                result = HygieneTag.compare(id1._hygieneTag, id2._hygieneTag);
            }
            return result;
        }
    }
}
