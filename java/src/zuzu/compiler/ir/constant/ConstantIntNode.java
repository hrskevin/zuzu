package zuzu.compiler.ir.constant;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.Node;
import zuzu.compiler.ir.node.IntNode;

public final class ConstantIntNode extends IntNode
{
    private final int _value;

    public ConstantIntNode(int value)
    {
        _value = value;
    }

    @Override
    public int constantIntValue()
    {
        return _value;
    }

    @Override
    public Object constantValue()
    {
        return new Integer(_value);
    }

    @Override
    public Node input(int i)
    {
        return null;
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushInt(_value);
    }

    @Override
    public boolean isConstant()
    {
        return true;
    }

    @Override
    public int nInputs()
    {
        return 0;
    }

    @Override
    public ConstantIntNode replaceWithConstant()
    {
        return this;
    }
}
