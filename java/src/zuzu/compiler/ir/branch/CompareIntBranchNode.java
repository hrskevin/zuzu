package zuzu.compiler.ir.branch;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.Node;
import zuzu.compiler.ir.node.IntNode;

public final class CompareIntBranchNode extends TwoWayBranchNode
{
    private final IntNode _input0;
    private final IntNode _input1;

    public CompareIntBranchNode(ConditionType condition, IntNode input0, IntNode input1)
    {
        super(condition);
        _input0 = input0;
        _input1 = input1;
        assert (condition != ConditionType.NULL && condition != ConditionType.NONNULL);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        int diff = Integer.compare(state.popInt(), state.popInt());
        switch (condition())
        {
        case EQ:
            state.pushInt(diff & 1);
            break;
        case NE:
            state.pushInt((diff & 1) ^ 1);
            break;
        case LT:
            state.pushInt(((diff >> 1) & 1) ^ 1);
            break;
        case GT:
            state.pushInt(((diff - 1) >> 1) & 1);
            break;
        case LE:
            state.pushInt((diff + 1) >> 1);
            break;
        case GE:
            state.pushInt((diff >> 1) & 1);
            break;
        }
    }

    @Override
    public Node input(int i)
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
