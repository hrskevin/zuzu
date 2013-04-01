package zuzu.compiler.ir.node;

import zuzu.compiler.ir.Node;


public abstract class UnaryFloatNode<InputNode extends Node> extends FloatNode
{
    protected final InputNode _input;

    protected UnaryFloatNode(InputNode input)
    {
        _input = input;
    }

    @Override
    public boolean constantInputs()
    {
        return _input.isConstant();
    }

    @Override
    public InputNode input(int i)
    {
        assert (i == 0);
        return _input;
    }

    @Override
    public int nInputs()
    {
        return 1;
    }
}
