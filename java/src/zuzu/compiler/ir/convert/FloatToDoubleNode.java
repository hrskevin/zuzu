package zuzu.compiler.ir.convert;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantDoubleNode;
import zuzu.compiler.ir.node.FloatNode;
import zuzu.compiler.ir.node.UnaryDoubleNode;

public class FloatToDoubleNode extends UnaryDoubleNode<FloatNode>
{

    public FloatToDoubleNode(FloatNode input)
    {
        super(input);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushDouble(state.popFloat());
    }

    @Override
    public ConstantDoubleNode replaceWithConstant()
    {
        return newConstant(_input.constantFloatValue());
    }

}
