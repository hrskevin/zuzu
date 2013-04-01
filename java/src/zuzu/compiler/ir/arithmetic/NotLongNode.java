package zuzu.compiler.ir.arithmetic;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantLongNode;
import zuzu.compiler.ir.node.LongNode;
import zuzu.compiler.ir.node.UnaryLongNode;

public final class NotLongNode extends UnaryLongNode<LongNode>
{

    public NotLongNode(LongNode input)
    {
        super(input);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushLong(~state.popLong());
    }

    @Override
    public ConstantLongNode replaceWithConstant()
    {
        return newConstant(~_input.constantLongValue());
    }

}
