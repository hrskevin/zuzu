package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;
import zuzu.lang.annotation.Nullable;

public interface Type extends TypeReference
{
    boolean equals(Type that);

    /**
     * If this {@link #isBoxed()}, returns the unboxed version of the type, otherwise returns this
     * type.
     */
    @NotNull Type getBoxedType();

    @Nullable
    BuiltinType getBuiltinType();

    Class<?> getJavaClass();

    @NotNull String getName();

    @NotNull Type getNonNullVariant();

    /**
     * Returns variant of type with opposite {@link #isDeclaredNonNull()} value, or else returns
     * this type if not applicable.
     */
    @NotNull Type getNullnessVariant();

    @NotNull Type getNullVariant();

    int getImmediateSize();

    boolean hasParameters();

    boolean isAny();

    boolean isBoolean();

    boolean isBoxed();

    boolean isCharacter();

    boolean isClass();

    boolean isDeclaredNonNull();

    boolean isEnum();

    boolean isFloating();

    boolean isFunction();

    boolean isInteger();

    boolean isInterface();

    boolean isNever();

    boolean isNull();

    boolean isNumeric();

    boolean isPrimitive();

    boolean isReferenceType();

    boolean isRuntimeType();

    boolean isSubtypeOf(@NotNull Type that);

    boolean isSubrepOf(@NotNull Type that);

    boolean isUnsigned();

    boolean isVoid();

}
