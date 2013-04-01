package zuzu.compiler.ir.node;

import zuzu.compiler.ir.Node;

public abstract class UnaryLongNode<InputNode extends Node> extends LongNode
{
    protected final InputNode _input;

    protected UnaryLongNode(InputNode input)
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
    public final int nInputs()
    {
        return 1;
    }

}
