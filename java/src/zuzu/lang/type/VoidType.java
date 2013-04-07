package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public class VoidType extends AbstractType
{
    static public final VoidType INSTANCE = new VoidType();

    VoidType()
    {
    }

    @Override
    public @NotNull
    BuiltinType getBuiltinType()
    {
        return BuiltinType.VOID;
    }

    @Override public final int getImmediateSize()
    {
        return 0;
    }

    @Override public @NotNull String getName()
    {
        return "void";
    }

    @Override public Class<?> getJavaClass()
    {
        return Void.TYPE;
    }

    @Override public boolean isPrimitive()
    {
        return true;
    }

    @Override public final boolean isVoid()
    {
        return true;
    }

}
