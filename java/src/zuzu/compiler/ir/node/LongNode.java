package zuzu.compiler.ir.node;

import zuzu.compiler.ir.Node;
import zuzu.compiler.ir.Node.NodeType;
import zuzu.compiler.ir.constant.ConstantLongNode;

public abstract class LongNode extends Node
{
    public long constantLongValue()
    {
        return 0L;
    }

    @Override
    public final NodeType type()
    {
        return NodeType.LONG;
    }

    protected ConstantLongNode newConstant(long l)
    {
        return new ConstantLongNode(l);
    }

    @Override
    public abstract ConstantLongNode replaceWithConstant();
}
