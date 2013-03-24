package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public final class DoubleType extends AbstractType
{
    public static final @NotNull DoubleType INSTANCE = new DoubleType();

    private DoubleType()
    {
    }

    @Override public int getImmediateSize()
    {
        return 16;
    }

    @Override public Class<?> getJavaClass()
    {
        return Double.TYPE;
    }

    @Override public @NotNull String getName()
    {
        return "double";
    }

    @Override public boolean isFloating()
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
