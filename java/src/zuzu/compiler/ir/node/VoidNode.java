package zuzu.compiler.ir.node;

import zuzu.compiler.ir.Node;
import zuzu.compiler.ir.Node.Type;

public abstract class VoidNode extends Node
{
    @Override
    public final Type type()
    {
        return Type.VOID;
    }

    @Override
    public final VoidNode replaceWithConstant()
    {
        return this;
    }
}
