package zuzu.compiler.ir.arithmetic;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantIntNode;
import zuzu.compiler.ir.node.BinaryIntNode;
import zuzu.compiler.ir.node.DoubleNode;

public class CompareDoubleNode extends BinaryIntNode<DoubleNode>
{
    public CompareDoubleNode(DoubleNode input0, DoubleNode input1)
    {
        super(input0, input1);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushDouble(Double.compare(state.popDouble(), state.popDouble()));
    }

    @Override
    public ConstantIntNode replaceWithConstant()
    {
        return newConstant(Double.compare(_input0.constantDoubleValue(), _input1.constantDoubleValue()));
    }

}
