package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public final class AnyType extends AbstractType
{
    static final AnyType INSTANCE = new AnyType();

    private AnyType()
    {
    }

    @Override
    public @NotNull
    BuiltinType getBuiltinType()
    {
        return BuiltinType.ANY;
    }

    @Override public @NotNull String getName()
    {
        return "any";
    }

    @Override public @NotNull Class<?> getJavaClass()
    {
        return Any.class;
    }

    @Override public boolean isAny()
    {
        return true;
    }
}
