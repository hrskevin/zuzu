package zuzu.compiler.ir.arithmetic;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantFloatNode;
import zuzu.compiler.ir.node.FloatNode;
import zuzu.compiler.ir.node.UnaryFloatNode;

public final class NegFloatNode extends UnaryFloatNode<FloatNode>
{

    public NegFloatNode(FloatNode input)
    {
        super(input);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushFloat(-state.popFloat());
    }

    @Override
    public ConstantFloatNode replaceWithConstant()
    {
        return newConstant(-_input.constantFloatValue());
    }

}
