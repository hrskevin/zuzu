package zuzu.compiler.ir;

import zuzu.lang.type.TypeReference;

public class ConstantOperand extends Operand
{
    /*
     * Construction
     */

    public ConstantOperand(TypeReference type, Object node)
    {
        super(type, node);
    }

    /*-----------------
     * Operand methods
     */

    @Override
    public final boolean isConstant()
    {
        return true;
    }
}
