package zuzu.compiler.ir.node;

import zuzu.compiler.ir.Node;
import zuzu.compiler.ir.Node.NodeType;
import zuzu.compiler.ir.constant.ConstantReferenceNode;

public abstract class ReferenceNode extends Node
{
    @Override
    public final NodeType type()
    {
        return NodeType.REFERENCE;
    }

    ConstantReferenceNode makeConstant(Object value)
    {
        return new ConstantReferenceNode(value);
    }

    @Override
    public abstract ConstantReferenceNode replaceWithConstant();
}
