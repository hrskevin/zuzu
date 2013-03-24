package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public final class FloatType extends AbstractType
{
    public static final @NotNull FloatType INSTANCE = new FloatType();

    private FloatType()
    {
    }

    @Override public int getImmediateSize()
    {
        return 32;
    }

    @Override public Class<?> getJavaClass()
    {
        return Float.TYPE;
    }

    @Override public @NotNull String getName()
    {
        return "float";
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
