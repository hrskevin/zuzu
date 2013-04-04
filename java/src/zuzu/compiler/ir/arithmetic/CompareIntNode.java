package zuzu.compiler.ir.arithmetic;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantIntNode;
import zuzu.compiler.ir.node.BinaryIntNode;
import zuzu.compiler.ir.node.IntNode;

public class CompareIntNode extends BinaryIntNode<IntNode>
{
    public CompareIntNode(IntNode input0, IntNode input1)
    {
        super(input0, input1);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushInt(Integer.compare(state.popInt(), state.popInt()));
    }

    @Override
    public ConstantIntNode replaceWithConstant()
    {
        return newConstant(Integer.compare(_input0.constantIntValue(), _input1.constantIntValue()));
    }

    // TODO: simplification
    // compare(x,x) => 0
}
