package zuzu.compiler.ir.arithmetic;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantDoubleNode;
import zuzu.compiler.ir.node.DoubleNode;
import zuzu.compiler.ir.node.UnaryDoubleNode;

public final class NegDoubleNode extends UnaryDoubleNode<DoubleNode>
{

    public NegDoubleNode(DoubleNode input)
    {
        super(input);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushDouble(-state.popDouble());
    }

    @Override
    public ConstantDoubleNode replaceWithConstant()
    {
        return newConstant(-_input.constantDoubleValue());
    }

}
