package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;
import zuzu.lang.annotation.Nullable;

public abstract class AbstractType implements Type
{
    /*-------------------------
     * Type reference methods
     */

    @Override
    public @NotNull
    Type getType()
    {
        return this;
    }

    /*--------------
     * Type methods
     */

    @Override public boolean equals(Type that)
    {
        return this == that;
    }

    @Override
    public int getArity()
    {
        // Eventualy we may support tuple types with higher arity.
        return isVoid() ? 0 : 1;
    }

    @Override public @NotNull Type getBoxedType()
    {
        return this;
    }

    @Override
    public @Nullable
    BuiltinType getBuiltinType()
    {
        return null;
    }

    @Override public int getValueBits()
    {
        return 0;
    }

    @Override public abstract @NotNull String getName();

    @Override public @NotNull Type getNonNullVariant()
    {
        return this;
    }

    @Override public @NotNull Type getNullVariant()
    {
        return this;
    }

    @Override public @NotNull Type getNullnessVariant()
    {
        return this;
    }

    @Override
    public int getPrecision()
    {
        return 0;
    }

    @Override public boolean hasParameters()
    {
        return false;
    }

    @Override public boolean isAny()
    {
        return false;
    }

    @Override public boolean isBoolean()
    {
        return false;
    }

    @Override public boolean isBoxed()
    {
        return false;
    }

    @Override public boolean isCharacter()
    {
        return false;
    }

    @Override public boolean isClass()
    {
        return false;
    }

    @Override public boolean isDeclaredNonNull()
    {
        return false;
    }

    @Override public boolean isEnum()
    {
        return false;
    }

    @Override
    public boolean isExplicitlyCastableTo(@NotNull Type that)
    {
        return isSubtypeOf(that);
    }

    @Override public boolean isFloating()
    {
        return false;
    }

    @Override public boolean isFunction()
    {
        return false;
    }

    @Override
    public boolean isImplicitlyCastableTo(@NotNull Type that)
    {
        return this.isSubtypeOf(that);
    }

    @Override public boolean isInteger()
    {
        return false;
    }

    @Override public boolean isInterface()
    {
        return false;
    }

    @Override
    public boolean isInvalid()
    {
        return false;
    }

    @Override public boolean isNever()
    {
        return false;
    }

    @Override public boolean isNull()
    {
        return false;
    }

    @Override public boolean isNumeric()
    {
        return false;
    }

    @Override public boolean isPrimitive()
    {
        return false;
    }

    @Override public boolean isReferenceType()
    {
        return false;
    }

    @Override public boolean isRuntimeType()
    {
        return false;
    }

    @Override public boolean isSubtypeOf(@NotNull Type that)
    {
        return equals(that) || that.isAny();
    }

    @Override
    public boolean isSubrepOf(@NotNull Type that)
    {
        return equals(that);
    }

    @Override public boolean isUnsigned()
    {
        return false;
    }

    @Override public boolean isVoid()
    {
        return false;
    }
}
