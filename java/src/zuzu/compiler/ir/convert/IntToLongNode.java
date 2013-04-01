package zuzu.compiler.ir.convert;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantLongNode;
import zuzu.compiler.ir.node.IntNode;
import zuzu.compiler.ir.node.UnaryLongNode;

public final class IntToLongNode extends UnaryLongNode<IntNode>
{

    IntToLongNode(IntNode input)
    {
        super(input);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushLong(state.popInt());
    }

    @Override
    public ConstantLongNode replaceWithConstant()
    {
        return newConstant(_input.constantIntValue());
    }

}
