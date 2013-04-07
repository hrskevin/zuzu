package zuzu.compiler.ir.node;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.Node;

public final class PopNode extends VoidNode
{
    private final Node _input;

    public PopNode(Node input)
    {
        _input = input;
    }

    @Override
    public Node input(int i)
    {
        assert (i == 0);
        return _input;
    }

    @Override
    public int nInputs()
    {
        return 1;
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pop();
    }

}
