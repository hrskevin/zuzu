package zuzu.compiler.ir.convert;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantIntNode;
import zuzu.compiler.ir.node.DoubleNode;
import zuzu.compiler.ir.node.UnaryIntNode;

public class DoubleToIntNode extends UnaryIntNode<DoubleNode>
{
    public DoubleToIntNode(DoubleNode input)
    {
        super(input);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushInt((int) state.popDouble());
    }

    @Override
    public ConstantIntNode replaceWithConstant()
    {
        return newConstant((int) _input.constantDoubleValue());
    }
}
