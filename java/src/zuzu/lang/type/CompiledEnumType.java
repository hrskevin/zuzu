package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public class CompiledEnumType extends AbstractType
{
    /*-------
     * State
     */

    private final @NotNull Class<?> _class;
    private final @NotNull CompiledEnumType _nullnessVariant;
    private final boolean _nonNull;

    /*--------------
     * Construction
     */

    private CompiledEnumType(@NotNull Class<?> javaClass, @NotNull CompiledEnumType nullVariant)
    {
        _class = javaClass;
        _nullnessVariant = nullVariant;
        _nonNull = true;
    }

    CompiledEnumType(@NotNull Class<?> javaClass)
    {
        _class = javaClass;
        _nullnessVariant = new CompiledEnumType(javaClass, this);
        _nonNull = false;
    }

    /*
     * Type methods
     */

    @Override public boolean equals(Type that)
    {
        // TODO take parameters into account
        return that.isClass() && _class.equals(that.getJavaClass())
            && this.isDeclaredNonNull() == that.isDeclaredNonNull();
    }

    @Override public boolean hasParameters()
    {
        return _class.getTypeParameters().length > 0;
    }

    @Override public @NotNull String getName()
    {
        return _class.getName();
    }

    @Override public final Class<?> getJavaClass()
    {
        return _class;
    }

    @Override public @NotNull CompiledEnumType getNonNullVariant()
    {
        return isDeclaredNonNull() ? this : _nullnessVariant;
    }

    @Override public @NotNull CompiledEnumType getNullnessVariant()
    {
        return _nullnessVariant;
    }

    @Override public @NotNull CompiledEnumType getNullVariant()
    {
        return isDeclaredNonNull() ? _nullnessVariant : this;
    }

    @Override public final boolean isDeclaredNonNull()
    {
        return _nonNull;
    }

    @Override public final boolean isEnum()
    {
        return true;
    }

    @Override public final boolean isReferenceType()
    {
        return true;
    }

    @Override public boolean isSubtypeOf(@NotNull Type that)
    {
        if (that.isAny())
        {
            return true;
        }

        if (!(that.isInterface() || that.isEnum()))
        {
            return false;
        }

        if (this.isDeclaredNonNull() && !that.isDeclaredNonNull())
        {
            return false;
        }

        // TODO: take class parameters into account

        return that.getJavaClass().isAssignableFrom(_class);
    }
}
