package zuzu.compiler.ir.branch;

import java.util.Arrays;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.node.IntNode;

public final class SparseSwitchNode extends MultiWayBranchNode
{
    private final int[] _values;

    public SparseSwitchNode(IntNode input, int... values)
    {
        super(input, values.length + 1);
        _values = values;
    }

    @Override
    public void interpret(InterpreterState state)
    {
        int i = Arrays.binarySearch(_values, state.popInt());
        state.pushInt(i < 0 ? 0 : i + 1);
    }

}
