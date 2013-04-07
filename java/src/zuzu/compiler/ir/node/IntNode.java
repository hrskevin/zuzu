package zuzu.compiler.ir.node;

import zuzu.compiler.ir.Node;
import zuzu.compiler.ir.Node.NodeType;
import zuzu.compiler.ir.constant.ConstantIntNode;

public abstract class IntNode extends Node
{
    public int constantIntValue()
    {
        return 0;
    }

    @Override
    public final NodeType type()
    {
        return NodeType.INT;
    }

    protected ConstantIntNode newConstant(int value)
    {
        return new ConstantIntNode(value);
    }

    @Override
    public abstract ConstantIntNode replaceWithConstant();
}
