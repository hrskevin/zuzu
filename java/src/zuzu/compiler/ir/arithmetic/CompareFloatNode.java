package zuzu.compiler.ir.arithmetic;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantIntNode;
import zuzu.compiler.ir.node.BinaryIntNode;
import zuzu.compiler.ir.node.FloatNode;

public class CompareFloatNode extends BinaryIntNode<FloatNode>
{
    public CompareFloatNode(FloatNode input0, FloatNode input1)
    {
        super(input0, input1);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushFloat(Float.compare(state.popFloat(), state.popFloat()));
    }

    @Override
    public ConstantIntNode replaceWithConstant()
    {
        return newConstant(Float.compare(_input0.constantFloatValue(), _input1.constantFloatValue()));
    }
}
