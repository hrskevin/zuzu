package zuzu.compiler.ir.constant;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.Node;
import zuzu.compiler.ir.node.ReferenceNode;

public class ConstantReferenceNode extends ReferenceNode
{
    private final Object _value;

    public ConstantReferenceNode(Object value)
    {
        _value = value;
    }

    @Override
    public Object constantValue()
    {
        return _value;
    }

    @Override
    public Node input(int i)
    {
        return null;
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushReference(_value);
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
    public ConstantReferenceNode replaceWithConstant()
    {
        return this;
    }
}
