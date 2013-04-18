package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public final class ShortType extends IntegralType
{
    public static final @NotNull ShortType INSTANCE = new ShortType();

    private ShortType()
    {
    }

    @Override
    public @NotNull
    BuiltinType getBuiltinType()
    {
        return BuiltinType.SHORT;
    }

    @Override public int getValueBits()
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

    @Override
    public int getPrecision()
    {
        return 16;
    }

}
