package zuzu.compiler.ir.constant;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.Node;
import zuzu.compiler.ir.node.ReferenceNode;

public class ConstantReferenceNode extends ReferenceNode
{
    private final Object _value;

    public ConstantReferenceNode(Object value)
    {
        _value = value;
    }

    /*----------------
     * Object methods
     */

    @Override
    public boolean equals(Object node)
    {
        return (node instanceof ConstantReferenceNode) && _value.equals(((Node) node).constantValue());
    }

    @Override
    public int hashCode()
    {
        return _value == null ? 0 : _value.hashCode();
    }

    /*--------------
     * Node methods
     */

    @Override
    public Object constantValue()
    {
        return _value;
    }

    @Override
    public Node input(int i)
    {
        return null;
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushReference(_value);
    }

    @Override
    public boolean isConstant()
    {
        return true;
    }

    @Override
    public int nInputs()
    {
        return 0;
    }

    @Override
    public ConstantReferenceNode replaceWithConstant()
    {
        return this;
    }
}
