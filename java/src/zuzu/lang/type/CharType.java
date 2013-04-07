package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public final class CharType extends AbstractType
{
    public static final @NotNull CharType INSTANCE = new CharType();

    private CharType()
    {
    }

    @Override
    public @NotNull
    BuiltinType getBuiltinType()
    {
        return BuiltinType.CHAR;
    }

    @Override public int getImmediateSize()
    {
        return 16;
    }

    @Override public Class<?> getJavaClass()
    {
        return Character.TYPE;
    }

    @Override public @NotNull String getName()
    {
        return "char";
    }

    @Override public boolean isCharacter()
    {
        return true;
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

    @Override
    public boolean isSubrepOf(@NotNull Type that)
    {
        return that == this || that == IntegerType.INSTANCE;
    }

    @Override public boolean isUnsigned()
    {
        return true;
    }
}
