package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public class IntegerType extends IntegralType
{
    public static final @NotNull IntegerType INSTANCE = new IntegerType();

    private IntegerType()
    {
    }

    @Override
    public @NotNull
    BuiltinType getBuiltinType()
    {
        return BuiltinType.INT;
    }

    @Override public int getValueBits()
    {
        return 32;
    }

    @Override public Class<?> getJavaClass()
    {
        return Integer.TYPE;
    }

    @Override public @NotNull String getName()
    {
        return "int";
    }

    @Override
    public int getPrecision()
    {
        return 32;
    }

}
