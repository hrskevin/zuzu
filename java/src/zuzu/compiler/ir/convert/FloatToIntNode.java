package zuzu.compiler.ir.convert;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantIntNode;
import zuzu.compiler.ir.node.FloatNode;
import zuzu.compiler.ir.node.UnaryIntNode;

public class FloatToIntNode extends UnaryIntNode<FloatNode>
{
    public FloatToIntNode(FloatNode input)
    {
        super(input);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushInt(state.popInt());
    }

    @Override
    public ConstantIntNode replaceWithConstant()
    {
        return newConstant((int) _input.constantFloatValue());
    }
}
