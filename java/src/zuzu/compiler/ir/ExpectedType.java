package zuzu.compiler.ir;

import zuzu.lang.type.Type;

public class ExpectedType implements ExpectedResult
{
    private final Type _type;

    private ExpectedType(Type type)
    {
        _type = type;
    }

    static public ExpectedType make(Type type)
    {
        return new ExpectedType(type);
    }

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
        return i == 0 ? _type : null;
    }

    @Override
    public Type expectedType()
    {
        return _type;
    }
}
