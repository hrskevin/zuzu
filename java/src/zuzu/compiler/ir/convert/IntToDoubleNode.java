package zuzu.compiler.ir.convert;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantDoubleNode;
import zuzu.compiler.ir.node.IntNode;
import zuzu.compiler.ir.node.UnaryDoubleNode;

public final class IntToDoubleNode extends UnaryDoubleNode<IntNode>
{

    public IntToDoubleNode(IntNode input)
    {
        super(input);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushDouble(state.popInt());
    }

    @Override
    public ConstantDoubleNode replaceWithConstant()
    {
        return newConstant(_input.constantIntValue());
    }

}
