package zuzu.compiler.ir.arithmetic;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantDoubleNode;
import zuzu.compiler.ir.node.BinaryDoubleNode;
import zuzu.compiler.ir.node.DoubleNode;

public final class DivDoubleNode extends BinaryDoubleNode<DoubleNode>
{
    public DivDoubleNode(DoubleNode input0, DoubleNode input1)
    {
        super(input0, input1);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushInt(state.popInt() / state.popInt());
    }

    @Override
    public ConstantDoubleNode replaceWithConstant()
    {
        return newConstant(_input0.constantDoubleValue() / _input1.constantDoubleValue());
    }

}
