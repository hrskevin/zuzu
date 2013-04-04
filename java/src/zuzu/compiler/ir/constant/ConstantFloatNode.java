package zuzu.compiler.ir.constant;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.Node;
import zuzu.compiler.ir.node.FloatNode;

public class ConstantFloatNode extends FloatNode
{
    private final float _value;

    public ConstantFloatNode(float value)
    {
        _value = value;
    }

    /*----------------
     * Object methods
     */

    @Override
    public boolean equals(Object node)
    {
        return (node instanceof ConstantFloatNode) &&
            Float.floatToIntBits(_value) == Float.floatToIntBits(((ConstantFloatNode) node)._value);
    }

    @Override
    public int hashCode()
    {
        return Float.floatToIntBits(_value);
    }

    /*--------------
     * Node methods
     */

    @Override
    public float constantFloatValue()
    {
        return _value;
    }

    @Override
    public Object constantValue()
    {
        return new Float(_value);
    }

    @Override
    public Node input(int i)
    {
        return null;
    }

    @Override
    public void interpret(InterpreterState state)
    {
        state.pushFloat(_value);
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
    public ConstantFloatNode replaceWithConstant()
    {
        return this;
    }
}
