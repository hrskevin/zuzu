package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public class IntegerType extends AbstractType
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

    @Override public int getImmediateSize()
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
