package zuzu.compiler.ir.branch;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.Node;

public class JumpNode extends BranchNode
{
    @Override
    public int nBranches()
    {
        return 1;
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushInt(0);
    }

    @Override
    public Node input(int i)
    {
        return null;
    }

    @Override
    public int nInputs()
    {
        return 0;
    }

}
