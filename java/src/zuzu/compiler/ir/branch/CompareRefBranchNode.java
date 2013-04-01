package zuzu.compiler.ir.branch;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.node.ReferenceNode;

public final class CompareRefBranchNode extends TwoWayBranchNode
{
    private final ReferenceNode _input0;
    private final ReferenceNode _input1;

    public CompareRefBranchNode(ConditionType condition, ReferenceNode input0, ReferenceNode input1)
    {
        super(condition);
        _input0 = input0;
        _input1 = input1;
        assert (condition == ConditionType.EQ || condition == ConditionType.NE);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        boolean isEqual = (state.popReference() == state.popReference());
        state.pushInt(isEqual ^ (condition() != ConditionType.EQ) ? 0 : 1);
    }

    @Override
    public ReferenceNode input(int i)
    {
        assert (i == 0 || i == 1);
        return i == 0 ? _input0 : _input1;
    }

    @Override
    public int nInputs()
    {
        return 2;
    }


}
