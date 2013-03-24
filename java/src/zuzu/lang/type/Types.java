package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public enum Types implements TypeReference
{
        VOID(VoidType.INSTANCE),
        NEVER(NeverType.INSTANCE),
        BOOL(BooleanType.INSTANCE),
        BYTE(ByteType.INSTANCE),
        CHAR(CharType.INSTANCE),
        SHORT(ShortType.INSTANCE),
        INT(IntegerType.INSTANCE),
        LONG(LongType.INSTANCE),
        FLOAT(FloatType.INSTANCE),
        DOUBLE(DoubleType.INSTANCE),
        STRING(typeFromJavaClass(String.class));

    private final Type _type;

    private Types(Type type)
    {
        _type = type;
    }
    
    @Override
    public @NotNull
    Type getType()
    {
        return _type;
    }

    private static class TypeForJavaClass extends ClassValue<Type>
    {
        @Override protected Type computeValue(Class<?> javaType)
        {
            if (javaType.getPackage().getName() == "java.lang")
            {
                if (javaType.isPrimitive())
                {
                    if (javaType == Integer.TYPE)
                    {
                        return IntegerType.INSTANCE;
                    }
                    else if (javaType == Boolean.TYPE)
                    {
                        return BooleanType.INSTANCE;
                    }
                    else if (javaType == Long.TYPE)
                    {
                        return LongType.INSTANCE;
                    }
                    else if (javaType == Short.TYPE)
                    {
                        return ShortType.INSTANCE;
                    }
                    else if (javaType == Byte.TYPE)
                    {
                        return ByteType.INSTANCE;
                    }
                    else if (javaType == Character.TYPE)
                    {
                        return CharType.INSTANCE;
                    }
                    else if (javaType == Double.TYPE)
                    {
                        return DoubleType.INSTANCE;
                    }
                    else if (javaType == Float.TYPE)
                    {
                        return FloatType.INSTANCE;
                    }
                }
                else if (javaType == Void.TYPE)
                {
                    return VoidType.INSTANCE;
                }
                else if (javaType == Integer.class)
                {
                    return new BoxedType(javaType, IntegerType.INSTANCE);
                }
                else if (javaType == Short.class)
                {
                    return new BoxedType(javaType, ShortType.INSTANCE);
                }
                else if (javaType == Long.class)
                {
                    return new BoxedType(javaType, LongType.INSTANCE);
                }
                else if (javaType == Byte.class)
                {
                    return new BoxedType(javaType, ByteType.INSTANCE);
                }
                else if (javaType == Character.class)
                {
                    return new BoxedType(javaType, CharType.INSTANCE);
                }
                else if (javaType == Boolean.class)
                {
                    return new BoxedType(javaType, BooleanType.INSTANCE);
                }
                else if (javaType == Float.class)
                {
                    return new BoxedType(javaType, FloatType.INSTANCE);
                }
                else if (javaType == Double.class)
                {
                    return new BoxedType(javaType, DoubleType.INSTANCE);
                }
            }

            if (javaType.isArray())
            {
                // TODO array types
            }
            else if (javaType.isInterface())
            {
                return new CompiledInterfaceType(javaType);
            }
            else if (javaType.isEnum())
            {
                return new CompiledEnumType(javaType);
            }

            return new CompiledClassType(javaType);
        }
    }

    private static TypeForJavaClass _typeFromJava = null;

    public static @NotNull Type typeFromJavaClass(Class<?> javaClass)
    {
        if (_typeFromJava == null)
        {
            _typeFromJava = new TypeForJavaClass();
        }
        return _typeFromJava.get(javaClass);
    }
}
