package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public final class DoubleType extends AbstractType
{
    public static final @NotNull DoubleType INSTANCE = new DoubleType();

    private DoubleType()
    {
    }

    @Override
    public @NotNull
    BuiltinType getBuiltinType()
    {
        return BuiltinType.DOUBLE;
    }

    @Override public int getValueBits()
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

    @Override
    public int getPrecision()
    {
        return 52;
    }

    @Override public boolean isFloating()
    {
        return true;
    }

    @Override
    public boolean isExplicitlyCastableTo(@NotNull Type that)
    {
        return isNumeric();
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
