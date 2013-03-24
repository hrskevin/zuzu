package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public class NeverType extends VoidType
{
    static final NeverType INSTANCE = new NeverType();

    private NeverType()
    {
    }

    @Override public @NotNull String getName()
    {
        return "never";
    }

    @Override public boolean isNever()
    {
        return true;
    }

    @Override public boolean isSubtypeOf(@NotNull Type that)
    {
        return true;
    }
}
