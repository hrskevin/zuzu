package zuzu.compiler.ir.node;

import zuzu.compiler.ir.Node;

public abstract class BinaryDoubleNode<InputNode extends Node> extends DoubleNode
{
    protected final InputNode _input0;
    protected final InputNode _input1;

    public BinaryDoubleNode(InputNode input0, InputNode input1)
    {
        _input0 = input0;
        _input1 = input1;
    }

    @Override
    public boolean constantInputs()
    {
        return _input0.isConstant() && _input1.isConstant();
    }

    @Override
    public InputNode input(int i)
    {
        assert (0 <= i && i < 2);
        return i == 0 ? _input0 : _input1;
    }

    @Override
    public int nInputs()
    {
        return 2;
    }

}
