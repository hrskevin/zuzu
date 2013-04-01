package zuzu.compiler.ir.convert;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantFloatNode;
import zuzu.compiler.ir.node.LongNode;
import zuzu.compiler.ir.node.UnaryFloatNode;

public final class LongToFloatNode extends UnaryFloatNode<LongNode>
{

    public LongToFloatNode(LongNode input)
    {
        super(input);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushFloat(state.popLong());
    }

    @Override
    public ConstantFloatNode replaceWithConstant()
    {
        return newConstant(_input.constantLongValue());
    }

}
