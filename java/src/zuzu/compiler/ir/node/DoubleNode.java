package zuzu.compiler.ir.node;

import zuzu.compiler.ir.Node;
import zuzu.compiler.ir.Node.NodeType;
import zuzu.compiler.ir.constant.ConstantDoubleNode;

public abstract class DoubleNode extends Node
{
    public double constantDoubleValue()
    {
        return Double.NaN;
    }

    @Override
    public final NodeType type()
    {
        return NodeType.DOUBLE;
    }

    protected ConstantDoubleNode newConstant(double d)
    {
        return new ConstantDoubleNode(d);
    }

    @Override
    public abstract ConstantDoubleNode replaceWithConstant();
}
