package zuzu.compiler.ir.convert;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantIntNode;
import zuzu.compiler.ir.node.IntNode;
import zuzu.compiler.ir.node.UnaryIntNode;

public class IntToCharNode extends UnaryIntNode<IntNode>
{
    public IntToCharNode(IntNode input)
    {
        super(input);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushInt((char) state.popInt());
    }

    @Override
    public ConstantIntNode replaceWithConstant()
    {
        return newConstant((char) _input.constantIntValue());
    }
}
