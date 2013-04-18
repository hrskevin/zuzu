package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public final class ByteType extends IntegralType
{
    public static final @NotNull ByteType INSTANCE = new ByteType();

    private ByteType()
    {
    }

    @Override
    public @NotNull
    BuiltinType getBuiltinType()
    {
        return BuiltinType.BYTE;
    }

    @Override public int getValueBits()
    {
        return 8;
    }

    @Override public Class<?> getJavaClass()
    {
        return Byte.TYPE;
    }

    @Override
    public int getPrecision()
    {
        return 8;
    }

    @Override public @NotNull String getName()
    {
        return "byte";
    }

}
