package zuzu.compiler.ir.branch;


public abstract class TwoWayBranchNode extends BranchNode
{
    private final ConditionType _condition;

    TwoWayBranchNode(ConditionType condition)
    {
        _condition = condition;
    }

    public final ConditionType condition()
    {
        return _condition;
    }

    @Override
    public int nBranches()
    {
        return 2;
    }
}
