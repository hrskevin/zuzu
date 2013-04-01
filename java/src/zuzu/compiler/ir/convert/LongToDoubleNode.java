package zuzu.compiler.ir.convert;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantDoubleNode;
import zuzu.compiler.ir.node.LongNode;
import zuzu.compiler.ir.node.UnaryDoubleNode;

public final class LongToDoubleNode extends UnaryDoubleNode<LongNode>
{

    public LongToDoubleNode(LongNode input)
    {
        super(input);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushDouble(state.popLong());
    }

    @Override
    public ConstantDoubleNode replaceWithConstant()
    {
        return newConstant(_input.constantLongValue());
    }

}
