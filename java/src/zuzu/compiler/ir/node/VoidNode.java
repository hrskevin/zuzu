package zuzu.compiler.ir.node;

import zuzu.compiler.ir.Node;
import zuzu.compiler.ir.Node.NodeType;

public abstract class VoidNode extends Node
{
    @Override
    public final NodeType type()
    {
        return NodeType.VOID;
    }

    @Override
    public final VoidNode replaceWithConstant()
    {
        return this;
    }
}
