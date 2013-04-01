package zuzu.compiler.ir.branch;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.node.IntNode;

public final class DenseSwitchNode extends MultiWayBranchNode
{
    private final int _lowValue;
    private final int _highValue;

    public DenseSwitchNode(IntNode input, int lowValue, int highValue)
    {
        super(input, 2 + highValue - lowValue);
        _lowValue = lowValue;
        _highValue = highValue;
    }

    @Override
    public void interpret(InterpreterState state)
    {
        int branch = 0;
        int value = state.popInt();
        if (_lowValue <= value && value <= _highValue)
        {
            branch = 1 + value - _lowValue;
        }
        state.pushInt(branch);
    }
}
