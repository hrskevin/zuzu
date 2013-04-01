package zuzu.compiler.ir.arithmetic;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantIntNode;
import zuzu.compiler.ir.node.BinaryIntNode;
import zuzu.compiler.ir.node.LongNode;

public class CompareLongNode extends BinaryIntNode<LongNode>
{
    public CompareLongNode(LongNode input0, LongNode input1)
    {
        super(input0, input1);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushLong(Long.compare(state.popLong(), state.popLong()));
    }

    @Override
    public ConstantIntNode replaceWithConstant()
    {
        return newConstant(Long.compare(_input0.constantLongValue(), _input1.constantLongValue()));
    }
}
