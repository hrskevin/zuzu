package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public final class FloatType extends FloatingType
{
    public static final @NotNull FloatType INSTANCE = new FloatType();

    private FloatType()
    {
    }

    @Override
    public @NotNull
    BuiltinType getBuiltinType()
    {
        return BuiltinType.FLOAT;
    }

    @Override public int getValueBits()
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

    @Override
    public int getPrecision()
    {
        return 23;
    }

    @Override
    public boolean isExplicitlyCastableTo(@NotNull Type that)
    {
        return isNumeric();
    }

}
