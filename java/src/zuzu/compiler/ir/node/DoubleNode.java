package zuzu.compiler.ir.node;

import zuzu.compiler.ir.Node;
import zuzu.compiler.ir.Node.Type;
import zuzu.compiler.ir.constant.ConstantDoubleNode;

public abstract class DoubleNode extends Node
{
    public double constantDoubleValue()
    {
        return Double.NaN;
    }

    @Override
    public final Type type()
    {
        return Type.DOUBLE;
    }

    protected ConstantDoubleNode newConstant(double d)
    {
        return new ConstantDoubleNode(d);
    }

    @Override
    public abstract ConstantDoubleNode replaceWithConstant();
}
