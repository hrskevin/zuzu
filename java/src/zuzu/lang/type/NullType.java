package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public class NullType extends AbstractType
{
    public static final NullType INSTANCE = new NullType();

    private NullType()
    {
    }

    @Override public @NotNull String getName()
    {
        return "Null";
    }

    @Override public Class<?> getJavaClass()
    {
        return null;
    }

    @Override public boolean isNull()
    {
        return true;
    }
}
