package zuzu.compiler.ir.convert;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantLongNode;
import zuzu.compiler.ir.node.DoubleNode;
import zuzu.compiler.ir.node.UnaryLongNode;

public class DoubleToLongNode extends UnaryLongNode<DoubleNode>
{
    DoubleToLongNode(DoubleNode input)
    {
        super(input);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushLong((long) state.popDouble());
    }

    @Override
    public ConstantLongNode replaceWithConstant()
    {
        return newConstant((long) _input.constantDoubleValue());
    }
}
