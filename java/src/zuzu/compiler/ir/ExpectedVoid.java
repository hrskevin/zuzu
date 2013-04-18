package zuzu.compiler.ir;

import zuzu.lang.type.Type;
import zuzu.lang.type.VoidType;

public enum ExpectedVoid implements ExpectedResult
{
    INSTANCE;

    @Override
    public boolean isInferringTypes()
    {
        return false;
    }

    @Override
    public int minArity()
    {
        return 0;
    }

    @Override
    public int maxArity()
    {
        return 0;
    }

    @Override
    public Type expectedType(int i)
    {
        return null;
    }

    @Override
    public Type expectedType()
    {
        return VoidType.INSTANCE;
    }
}
