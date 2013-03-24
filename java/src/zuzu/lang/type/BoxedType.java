package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public final class BoxedType extends AbstractType
{
    private final @NotNull Class<?> _javaClass;
    private final @NotNull Type _boxedType;

    BoxedType(@NotNull Class<?> javaClass, @NotNull Type boxedType)
    {
        _javaClass = javaClass;
        _boxedType = boxedType;
    }

    @Override public @NotNull Type getBoxedType()
    {
        return _boxedType;
    }

    @Override public Class<?> getJavaClass()
    {
        return _javaClass;
    }

    @Override
    public @NotNull
    String getName()
    {
        return _javaClass.getName();
    }

    @Override public boolean isBoxed()
    {
        return true;
    }

    @Override public boolean isBoolean()
    {
        return _boxedType.isBoolean();
    }

    @Override public boolean isCharacter()
    {
        return _boxedType.isCharacter();
    }

    @Override public boolean isFloating()
    {
        return _boxedType.isFloating();
    }

    @Override public boolean isInteger()
    {
        return _boxedType.isInteger();
    }

    @Override public boolean isNumeric()
    {
        return _boxedType.isNumeric();
    }

    @Override public boolean isUnsigned()
    {
        return _boxedType.isUnsigned();
    }
}
