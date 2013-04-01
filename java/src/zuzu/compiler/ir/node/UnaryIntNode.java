package zuzu.compiler.ir.node;

import zuzu.compiler.ir.Node;

/**
 * An {@link IntNode} with a single input.
 */
public abstract class UnaryIntNode<InputNode extends Node> extends IntNode
{
    protected final InputNode _input;

    protected UnaryIntNode(InputNode input)
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
