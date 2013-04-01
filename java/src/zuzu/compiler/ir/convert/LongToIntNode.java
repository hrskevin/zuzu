package zuzu.compiler.ir.convert;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantIntNode;
import zuzu.compiler.ir.node.LongNode;
import zuzu.compiler.ir.node.UnaryIntNode;

public class LongToIntNode extends UnaryIntNode<LongNode>
{
    public LongToIntNode(LongNode input)
    {
        super(input);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushInt((int) state.popLong());
    }

    @Override
    public ConstantIntNode replaceWithConstant()
    {
        return newConstant((int) _input.constantLongValue());
    }
}
