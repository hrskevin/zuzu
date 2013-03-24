package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public final class ByteType extends AbstractType
{
    public static final @NotNull ByteType INSTANCE = new ByteType();

    private ByteType()
    {
    }

    @Override public int getImmediateSize()
    {
        return 8;
    }

    @Override public Class<?> getJavaClass()
    {
        return Byte.TYPE;
    }

    @Override public @NotNull String getName()
    {
        return "byte";
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
