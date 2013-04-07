package zuzu.compiler.interpreter;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import zuzu.compiler.ir.CompilationState;
import zuzu.compiler.ir.Node;
import zuzu.compiler.ir.Operand;
import zuzu.compiler.ir.constant.ConstantDoubleNode;
import zuzu.compiler.ir.constant.ConstantFloatNode;
import zuzu.compiler.ir.constant.ConstantIntNode;
import zuzu.compiler.ir.constant.ConstantLongNode;
import zuzu.compiler.ir.constant.ConstantReferenceNode;
import zuzu.compiler.parser.ZuzuParserBaseVisitor;
import zuzu.compiler.parser.ZuzuToken;
import zuzu.lang.type.BuiltinType;
import zuzu.lang.type.Type;

public class ZuzuFunctionCompiler extends ZuzuParserBaseVisitor<Operand>
{
    /*-------
     * State
     */

    private final CompilationState _state;

    /*--------------
     * Construction
     */

    public ZuzuFunctionCompiler(CompilationState state)
    {
        _state = state;
    }

    public ZuzuFunctionCompiler()
    {
        this(new CompilationState());
    }

    /*----------------------------------
     * AbstractParseTreeVisitor methods
     */

    @Override
    protected Operand aggregateResult(Operand aggregate, Operand nextResult)
    {
        return (nextResult != null) ? nextResult : aggregate;
    }

    @Override
    public Operand visitTerminal(TerminalNode node)
    {
        Operand result = defaultResult();
        ZuzuToken token = (ZuzuToken) node.getSymbol();
        if (token.isLiteral())
        {
            result = compileConstant(token.literalType(), token.literalValue());
        }
        return result;
    }

    /*--------------------------------
     * ZuzuParserBaseVisitor methods
     */

    private Operand compileConstant(Type type, Object value)
    {
        Node node = null;

        BuiltinType builtinType = type.getBuiltinType();
        if (builtinType == null)
        {
            if (type.isReferenceType())
            {
                node = new ConstantReferenceNode(value);
            }
        }
        else
        {
            switch (type.getBuiltinType())
            {
            case BOOL:
                node = new ConstantIntNode(((Boolean)value).booleanValue() ? 1 : 0);
                break;
            case BYTE:
                node = new ConstantIntNode(((Byte)value).byteValue());
                break;
            case CHAR:
                node = new ConstantIntNode(((Character) value).charValue());
                break;
            case SHORT:
                node = new ConstantIntNode(((Short) value).shortValue());
                break;
            case INT:
                node = new ConstantIntNode(((Integer) value).intValue());
                break;
            case LONG:
                node = new ConstantLongNode(((Long) value).longValue());
                break;
            case FLOAT:
                node = new ConstantFloatNode(((Float) value).floatValue());
                break;
            case DOUBLE:
                node = new ConstantDoubleNode(((Double) value).doubleValue());
                break;
            case NULL:
                node = new ConstantReferenceNode(null);
                break;
            default:
            }
        }

        if (node != null)
        {
            return new Operand(type, _state.addNode(node));
        }
        else
        {
            return defaultResult();
        }
    }

    /*-----------------
     * Private methods
     */

    private RuntimeException unimplemented(String syntax, ParserRuleContext ctx)
    {
        return new RuntimeException(String.format("Unimplemented syntax '%s'.", syntax));
    }

}
