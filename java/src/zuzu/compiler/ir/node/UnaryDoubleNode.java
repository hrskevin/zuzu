package zuzu.compiler.ir.node;

import zuzu.compiler.ir.Node;

public abstract class UnaryDoubleNode<InputNode extends Node> extends DoubleNode
{
    protected final InputNode _input;

    protected UnaryDoubleNode(InputNode input)
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
