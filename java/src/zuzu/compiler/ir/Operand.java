package zuzu.compiler.ir;

import zuzu.compiler.ir.node.DoubleNode;
import zuzu.compiler.ir.node.FloatNode;
import zuzu.compiler.ir.node.IntNode;
import zuzu.compiler.ir.node.LongNode;
import zuzu.compiler.ir.node.ReferenceNode;
import zuzu.compiler.ir.node.TypeInferenceNode;
import zuzu.lang.type.Type;
import zuzu.lang.type.TypeReference;

public class Operand
{
    /*-------
     * State
     */

    private final Type _type;

    private final Node _node;

    /*---------------
     * Construction
     */

    public Operand(TypeReference type, Node node)
    {
        _type = type.getType();
        _node = node;
    }

    public Operand(TypeReference type)
    {
        _type = type.getType();
        _node = TypeInferenceNode.INSTANCE;
    }

    /*-----------------
     * Operand methods
     */

    public final Type type()
    {
        return _type;
    }

    public final Node node()
    {
        return _node;
    }

    public final IntNode intNode()
    {
        return (IntNode) _node;
    }

    public final LongNode longNode()
    {
        return (LongNode) _node;
    }

    public final FloatNode floatNode()
    {
        return (FloatNode) _node;
    }

    public final DoubleNode doubleNode()
    {
        return (DoubleNode) _node;
    }

    public final ReferenceNode refNode()
    {
        return (ReferenceNode) _node;
    }

    public boolean isConstant()
    {
        return _node.isConstant();
    }
}
