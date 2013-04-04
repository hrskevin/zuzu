package zuzu.compiler.ir.arithmetic;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantFloatNode;
import zuzu.compiler.ir.node.BinaryFloatNode;
import zuzu.compiler.ir.node.FloatNode;

public class DivFloatNode extends BinaryFloatNode<FloatNode>
{
    public DivFloatNode(FloatNode input0, FloatNode input1)
    {
        super(input0, input1);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushInt(state.popInt() / state.popInt());
    }

    @Override
    public ConstantFloatNode replaceWithConstant()
    {
        return newConstant(_input0.constantFloatValue() / _input1.constantFloatValue());
    }

}
