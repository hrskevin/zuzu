package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public final class ShortType extends AbstractType
{
    public static final @NotNull ShortType INSTANCE = new ShortType();

    private ShortType()
    {
    }

    @Override public int getImmediateSize()
    {
        return 16;
    }

    @Override public Class<?> getJavaClass()
    {
        return Short.TYPE;
    }

    @Override public @NotNull String getName()
    {
        return "short";
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
