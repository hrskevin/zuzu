package zuzu.compiler.ir.convert;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantIntNode;
import zuzu.compiler.ir.node.IntNode;
import zuzu.compiler.ir.node.UnaryIntNode;

public class IntToByteNode extends UnaryIntNode<IntNode>
{
    public IntToByteNode(IntNode input)
    {
        super(input);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushInt((byte) state.popInt());
    }

    @Override
    public ConstantIntNode replaceWithConstant()
    {
        return newConstant((byte) _input.constantIntValue());
    }
}
