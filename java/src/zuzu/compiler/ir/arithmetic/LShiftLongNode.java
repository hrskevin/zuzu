package zuzu.compiler.ir.arithmetic;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.Node;
import zuzu.compiler.ir.constant.ConstantLongNode;
import zuzu.compiler.ir.node.IntNode;
import zuzu.compiler.ir.node.LongNode;

public final class LShiftLongNode extends LongNode
{
    private final LongNode _input0;
    private final IntNode _input1;

    public LShiftLongNode(LongNode input0, IntNode input1)
    {
        _input0 = input0;
        _input1 = input1;
    }

    @Override
    public ConstantLongNode replaceWithConstant()
    {
        return newConstant(_input0.constantLongValue() << _input1.constantIntValue());
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

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushLong(state.popLong() << state.popInt());
    }

    // TODO: simplification
    // 0 << x => 0
    // x << n (>=64) => 0
    // x << 0 => x
}
