package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public final class CharType extends IntegralType
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

    @Override public int getValueBits()
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

    @Override
    public int getPrecision()
    {
        return 16;
    }

    @Override public boolean isCharacter()
    {
        return true;
    }

    @Override public boolean isUnsigned()
    {
        return true;
    }
}
