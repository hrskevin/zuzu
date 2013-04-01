package zuzu.compiler.ir.branch;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.node.ReferenceNode;

public final class NullnessBranchNode extends TwoWayBranchNode
{
    private final ReferenceNode _input;

    public NullnessBranchNode(ConditionType condition, ReferenceNode input)
    {
        super(condition);
        _input = input;
        assert (condition == ConditionType.NULL || condition == ConditionType.NONNULL);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        boolean isNull = state.popReference() == null;
        state.pushInt(isNull ^ (condition() != ConditionType.NULL) ? 0 : 1);
    }

    @Override
    public ReferenceNode input(int i)
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
