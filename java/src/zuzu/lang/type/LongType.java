package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public final class LongType extends AbstractType
{
    public static final @NotNull LongType INSTANCE = new LongType();

    private LongType()
    {
    }

    @Override public int getImmediateSize()
    {
        return 64;
    }

    @Override public Class<?> getJavaClass()
    {
        return Long.TYPE;
    }

    @Override public @NotNull String getName()
    {
        return "long";
    }

    @Override public boolean isInteger()
    {
        return true;
    }

    @Override public boolean isPrimitive()
    {
        return true;
    }

    @Override public boolean isNumeric()
    {
        return true;
    }
}
