package zuzu.compiler.ir.arithmetic;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.constant.ConstantIntNode;
import zuzu.compiler.ir.node.BinaryIntNode;
import zuzu.compiler.ir.node.IntNode;

/**
 * Unsigned right-shift
 */
public class URShiftIntNode extends BinaryIntNode<IntNode>
{
    public URShiftIntNode(IntNode input0, IntNode input1)
    {
        super(input0, input1);
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushInt(state.popInt() >>> state.popInt());
    }

    @Override
    public ConstantIntNode replaceWithConstant()
    {
        return newConstant(_input0.constantIntValue() >>> _input1.constantIntValue());
    }

    // TODO: simplification
    // x >>> 0 => x
    // x >>> n(>=32) => 0
    // 0 >>> x => 0
}
