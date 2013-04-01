package zuzu.compiler.ir.convert;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantLongNode;
import zuzu.compiler.ir.node.FloatNode;
import zuzu.compiler.ir.node.UnaryLongNode;

public final class FloatToLongNode extends UnaryLongNode<FloatNode>
{
    FloatToLongNode(FloatNode input)
    {
        super(input);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushLong((long) state.popFloat());
    }

    @Override
    public ConstantLongNode replaceWithConstant()
    {
        return newConstant((long) _input.constantFloatValue());
    }
}
