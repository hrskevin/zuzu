package zuzu.compiler.ir.node;

import zuzu.compiler.ir.Node;
import zuzu.compiler.ir.Node.NodeType;
import zuzu.compiler.ir.constant.ConstantFloatNode;

public abstract class FloatNode extends Node
{
    public float constantFloatValue()
    {
        return Float.NaN;
    }

    @Override
    public final NodeType type()
    {
        return NodeType.FLOAT;
    }

    protected ConstantFloatNode newConstant(float f)
    {
        return new ConstantFloatNode(f);
    }

    @Override
    public abstract ConstantFloatNode replaceWithConstant();

}
