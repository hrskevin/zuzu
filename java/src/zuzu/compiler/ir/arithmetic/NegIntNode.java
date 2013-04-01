package zuzu.compiler.ir.arithmetic;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantIntNode;
import zuzu.compiler.ir.node.IntNode;
import zuzu.compiler.ir.node.UnaryIntNode;

public class NegIntNode extends UnaryIntNode<IntNode>
{
    public NegIntNode(IntNode input)
    {
        super(input);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushInt(-state.popInt());
    }

    @Override
    public ConstantIntNode replaceWithConstant()
    {
        return newConstant(-_input.constantIntValue());
    }
}
