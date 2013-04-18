package zuzu.compiler.ir;

import zuzu.lang.type.Type;

public enum ExpectedAnything implements ExpectedResult
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
        return Integer.MAX_VALUE;
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
