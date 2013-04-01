package zuzu.compiler.ir.constant;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.Node;
import zuzu.compiler.ir.node.DoubleNode;

public class ConstantDoubleNode extends DoubleNode
{
    private final double _value;

    public ConstantDoubleNode(double value)
    {
        _value = value;
    }

    @Override
    public double constantDoubleValue()
    {
        return _value;
    }

    @Override
    public Object constantValue()
    {
        return new Double(_value);
    }

    @Override
    public Node input(int i)
    {
        return null;
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushDouble(_value);
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
    public ConstantDoubleNode replaceWithConstant()
    {
        return this;
    }
}
