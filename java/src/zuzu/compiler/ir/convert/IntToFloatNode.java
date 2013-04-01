package zuzu.compiler.ir.convert;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantFloatNode;
import zuzu.compiler.ir.node.IntNode;
import zuzu.compiler.ir.node.UnaryFloatNode;

public final class IntToFloatNode extends UnaryFloatNode<IntNode>
{

    IntToFloatNode(IntNode input)
    {
        super(input);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushFloat(state.popInt());
    }

    @Override
    public ConstantFloatNode replaceWithConstant()
    {
        return newConstant(_input.constantIntValue());
    }

}
