package zuzu.compiler.ir.constant;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.Node;
import zuzu.compiler.ir.node.LongNode;

public final class ConstantLongNode extends LongNode
{
    private final long _value;

    public ConstantLongNode(long value)
    {
        _value = value;
    }

    /*----------------
     * Object methods
     */

    @Override
    public boolean equals(Object node)
    {
        return (node instanceof ConstantLongNode) && _value == ((ConstantLongNode) node)._value;
    }

    @Override
    public int hashCode()
    {
        return (int) (_value ^ (_value >>> 32));
    }

    /*--------------
     * Node methods
     */

    @Override
    public long constantLongValue()
    {
        return _value;
    }

    @Override
    public Object constantValue()
    {
        return new Long(_value);
    }

    @Override
    public Node input(int i)
    {
        return null;
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushLong(_value);
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
    public ConstantLongNode replaceWithConstant()
    {
        return this;
    }
}
