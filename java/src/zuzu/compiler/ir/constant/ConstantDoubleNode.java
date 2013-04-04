package zuzu.compiler.ir.constant;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.Node;
import zuzu.compiler.ir.node.DoubleNode;

public class ConstantDoubleNode extends DoubleNode
{
    private final double _value;

    public ConstantDoubleNode(double value)
    {
        _value = value;
    }

    /*----------------
     * Object methods
     */

    @Override
    public boolean equals(Object node)
    {
        return (node instanceof ConstantDoubleNode) &&
            Double.doubleToLongBits(_value) == Double.doubleToLongBits(((ConstantDoubleNode) node)._value);
    }
    
    @Override
    public int hashCode()
    {
        long v = Double.doubleToLongBits(_value);
        return (int) (v ^ (v >>> 32));
    }

    /*--------------
     * Node methods
     */

    @Override
    public double constantDoubleValue()
    {
        return _value;
    }

    @Override
    public Object constantValue()
    {
        return new Double(_value);
    }

    @Override
    public Node input(int i)
    {
        return null;
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushDouble(_value);
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
    public ConstantDoubleNode replaceWithConstant()
    {
        return this;
    }
}
