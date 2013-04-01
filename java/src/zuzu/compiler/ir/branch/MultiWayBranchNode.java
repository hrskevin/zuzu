package zuzu.compiler.ir.branch;

import zuzu.compiler.ir.node.IntNode;

public abstract class MultiWayBranchNode extends BranchNode
{
    final IntNode _input;
    final int _nBranches;

    MultiWayBranchNode(IntNode input, int nBranches)
    {
        _input = input;
        _nBranches = nBranches;
    }

    @Override
    public final int nBranches()
    {
        return _nBranches;
    }

    @Override
    public final IntNode input(int i)
    {
        return _input;
    }

    @Override
    public int nInputs()
    {
        return 1;
    }
}
