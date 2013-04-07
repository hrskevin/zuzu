package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public enum BuiltinType implements TypeReference
{
        ANY(AnyType.INSTANCE),
        VOID(VoidType.INSTANCE),
        NEVER(NeverType.INSTANCE),
        NULL(NullType.INSTANCE),
        BOOL(BooleanType.INSTANCE),
        BYTE(ByteType.INSTANCE),
        CHAR(CharType.INSTANCE),
        SHORT(ShortType.INSTANCE),
        INT(IntegerType.INSTANCE),
        LONG(LongType.INSTANCE),
        FLOAT(FloatType.INSTANCE),
        DOUBLE(DoubleType.INSTANCE);
    
    private final Type _type;
    
    private BuiltinType(Type type)
    {
        _type = type;
    }
    
    @Override
    public @NotNull
    Type getType()
    {
        return _type;
    }
}
