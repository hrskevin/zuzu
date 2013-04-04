package zuzu.compiler.ir.branch;

import zuzu.compiler.ir.node.VoidNode;

public abstract class BranchNode extends VoidNode
{

    @Override
    public final boolean equals(Object that)
    {
        return this == that;
    }

    @Override
    public final boolean isBranch()
    {
        return true;
    }

    @Override
    public abstract int nBranches();
}
