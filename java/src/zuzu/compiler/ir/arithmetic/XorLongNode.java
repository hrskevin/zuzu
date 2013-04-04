package zuzu.compiler.ir.arithmetic;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantLongNode;
import zuzu.compiler.ir.node.BinaryLongNode;
import zuzu.compiler.ir.node.LongNode;

public final class XorLongNode extends BinaryLongNode<LongNode>
{
    public XorLongNode(LongNode input0, LongNode input1)
    {
        super(input0, input1);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushLong(state.popLong() ^ state.popLong());
    }

    @Override
    public boolean isCommutative()
    {
        return true;
    }

    @Override
    public ConstantLongNode replaceWithConstant()
    {
        return newConstant(_input0.constantLongValue() ^ _input1.constantLongValue());
    }

    // TODO: simplification
    // x ^ 0 => x
    // x ^ x => 0
}
