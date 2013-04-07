package zuzu.compiler.ir;

import zuzu.lang.type.Type;
import zuzu.lang.type.TypeReference;

public class Operand
{
    /*-------
     * State
     */

    private final Type _type;

    // TODO: replace with Node type
    private final Node _node;

    /*---------------
     * Construction
     */

    public Operand(TypeReference type, Node node)
    {
        _type = type.getType();
        _node = node;
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

    public boolean isConstant()
    {
        return _node.isConstant();
    }
}
