package zuzu.compiler.ir.convert;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantFloatNode;
import zuzu.compiler.ir.node.DoubleNode;
import zuzu.compiler.ir.node.UnaryFloatNode;

public final class DoubleToFloatNode extends UnaryFloatNode<DoubleNode>
{

    DoubleToFloatNode(DoubleNode input)
    {
        super(input);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushFloat((float) state.popDouble());
    }

    @Override
    public ConstantFloatNode replaceWithConstant()
    {
        return newConstant((float) _input.constantDoubleValue());
    }

}
