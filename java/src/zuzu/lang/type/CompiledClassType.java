package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public class CompiledClassType extends AbstractType
{
    /*-------
     * State
     */

    private final @NotNull Class<?> _class;
    private final @NotNull CompiledClassType _nullnessVariant;
    private final boolean _nonNull;

    /*--------------
     * Construction
     */

    private CompiledClassType(@NotNull Class<?> javaClass, @NotNull CompiledClassType nullVariant)
    {
        _class = javaClass;
        _nullnessVariant = nullVariant;
        _nonNull = true;
    }

    CompiledClassType(@NotNull Class<?> javaClass)
    {
        _class = javaClass;
        _nullnessVariant = new CompiledClassType(javaClass, this);
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

    @Override public @NotNull CompiledClassType getNonNullVariant()
    {
        return isDeclaredNonNull() ? this : _nullnessVariant;
    }

    @Override public @NotNull CompiledClassType getNullnessVariant()
    {
        return _nullnessVariant;
    }

    @Override public @NotNull CompiledClassType getNullVariant()
    {
        return isDeclaredNonNull() ? _nullnessVariant : this;
    }

    @Override public final boolean isClass()
    {
        return true;
    }

    @Override public final boolean isDeclaredNonNull()
    {
        return _nonNull;
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

        if (!(that.isInterface() || that.isClass()))
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
