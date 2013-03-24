package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public enum NumericTypes implements TypeReference
{
        BYTE(ByteType.INSTANCE),
        CHAR(CharType.INSTANCE),
        SHORT(ShortType.INSTANCE),
        INT(IntegerType.INSTANCE),
        LONG(LongType.INSTANCE),
        FLOAT(FloatType.INSTANCE),
        DOUBLE(DoubleType.INSTANCE);
    // TODO: add BigInteger/BigDecimal

    private final Type _type;

    private NumericTypes(Type type)
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
