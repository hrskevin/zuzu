package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public final class BooleanType extends AbstractType
{
    public static final @NotNull BooleanType INSTANCE = new BooleanType();

    private BooleanType()
    {
    }

    @Override public int getImmediateSize()
    {
        return 1;
    }

    @Override public Class<?> getJavaClass()
    {
        return Boolean.TYPE;
    }

    @Override public @NotNull String getName()
    {
        return "boolean";
    }

    @Override public boolean isBoolean()
    {
        return true;
    }

    @Override public boolean isPrimitive()
    {
        return true;
    }

}
