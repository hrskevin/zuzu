package zuzu.compiler.ir.arithmetic;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantLongNode;
import zuzu.compiler.ir.node.LongNode;
import zuzu.compiler.ir.node.UnaryLongNode;

public class NegLongNode extends UnaryLongNode<LongNode>
{

    public NegLongNode(LongNode input)
    {
        super(input);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushLong(-state.popLong());
    }

    @Override
    public ConstantLongNode replaceWithConstant()
    {
        return newConstant(-_input.constantLongValue());
    }

}
