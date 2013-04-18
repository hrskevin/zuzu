package zuzu.compiler.ir;

import zuzu.lang.type.Type;

public enum ExpectedOneValue implements ExpectedResult
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
        return 1;
    }

    @Override
    public int maxArity()
    {
        return 1;
    }

    @Override
    public Type expectedType(int i)
    {
        return null;
    }

    @Override
    public Type expectedType()
    {
        return null;
    }
}
