package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public final class LongType extends IntegralType
{
    public static final @NotNull LongType INSTANCE = new LongType();

    private LongType()
    {
    }

    @Override
    public @NotNull
    BuiltinType getBuiltinType()
    {
        return BuiltinType.LONG;
    }

    @Override public int getValueBits()
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

    @Override
    public int getPrecision()
    {
        return 64;
    }

}
