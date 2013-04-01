package zuzu.compiler.ir.convert;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantIntNode;
import zuzu.compiler.ir.node.IntNode;
import zuzu.compiler.ir.node.UnaryIntNode;

public class IntToShortNode extends UnaryIntNode<IntNode>
{
    public IntToShortNode(IntNode input)
    {
        super(input);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushInt((short) state.popInt());
    }

    @Override
    public ConstantIntNode replaceWithConstant()
    {
        return newConstant((short) _input.constantIntValue());
    }
}
