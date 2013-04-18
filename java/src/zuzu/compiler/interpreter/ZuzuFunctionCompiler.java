package zuzu.compiler.interpreter;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import zuzu.compiler.ir.CompilationState;
import zuzu.compiler.ir.ExpectedResult;
import zuzu.compiler.ir.ExpectedType;
import zuzu.compiler.ir.Node;
import zuzu.compiler.ir.Operand;
import zuzu.compiler.ir.arithmetic.NotIntNode;
import zuzu.compiler.ir.constant.ConstantDoubleNode;
import zuzu.compiler.ir.constant.ConstantFloatNode;
import zuzu.compiler.ir.constant.ConstantIntNode;
import zuzu.compiler.ir.constant.ConstantLongNode;
import zuzu.compiler.ir.constant.ConstantReferenceNode;
import zuzu.compiler.parser.ZuzuParser;
import zuzu.compiler.parser.ZuzuParser.ExprContext;
import zuzu.compiler.parser.ZuzuParser.PrefixOpContext;
import zuzu.compiler.parser.ZuzuParserBaseVisitor;
import zuzu.compiler.parser.ZuzuToken;
import zuzu.lang.type.BooleanType;
import zuzu.lang.type.BuiltinType;
import zuzu.lang.type.Type;

public class ZuzuFunctionCompiler extends ZuzuParserBaseVisitor<Operand>
{
    /*-------
     * State
     */

    private CompilationState _state;

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

    // @Override
    // public Operand visitCastOp(CastOpContext ctx)
    // {
    // if (typeOnly())
    // {
    // }
    // }

    @Override
    public Operand visitPrefixOp(PrefixOpContext ctx)
    {
        switch (ctx.operator.getType())
        {
        case ZuzuParser.MINUS:
            break;

        case ZuzuParser.BIT_NOT:
            break;

        case ZuzuParser.NOT:
            return compileNot(ctx);

        default:
            break;
        }
        throw unimplemented(ctx.operator.getText(), ctx);
    }

    @Override
    public Operand visitTerminal(TerminalNode node)
    {
        Operand result = defaultResult();
        ZuzuToken token = (ZuzuToken) node.getSymbol();
        if (token.isLiteral())
        {
            result = compileLiteral(token.literalType(), token.literalValue());
        }
        return result;
    }

    /*--------------------------------
     * ZuzuParserBaseVisitor methods
     */

    private Operand compileLiteral(Type type, Object value)
    {
        if (typeOnly())
        {
            return makeTypeOperand(type);
        }

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
            return makeOperand(type, _state.addNode(node));
        }
        else
        {
            // FIXME: should be an error
            return defaultResult();
        }
    }

    private Operand compileExpr(ExpectedResult expected, ExprContext ctx)
    {
        ExpectedResult prevExpected = _state.getExpectedResult();
        _state.setExpectedResult(expected);
        try
        {
            return ctx.accept(this);
        }
        finally
        {
            _state.setExpectedResult(prevExpected);
        }
    }

    private Operand compileExpr(Type expectedType, ExprContext ctx)
    {
        return compileExpr(ExpectedType.make(expectedType), ctx);
    }

    private Operand compileNot(PrefixOpContext ctx)
    {
        Type type = BooleanType.INSTANCE;

        if (typeOnly())
        {
            return makeTypeOperand(type);
        }

        Operand right = compileExpr(type, ctx.right);
        Operand result = addOperand(type, new NotIntNode(right.intNode()));

        return castToExpected(result);
    }

    /*------------------------------
     * ZuzuFunctionCompiler methods
     */

    public CompilationState finish()
    {
        CompilationState state = _state;
        _state = new CompilationState();
        return state;
    }

    /*-----------------
     * Package methods
     */


    /*-----------------
     * Private methods
     */

    private Operand addOperand(Type type, Node node)
    {
        return makeOperand(type, _state.addNode(node));
    }

    private Operand makeOperand(Type type, Node node)
    {
        return new Operand(type, node);
    }

    private Operand makeTypeOperand(Type type)
    {
        return new Operand(type);
    }

    private Operand castToExpected(Operand operand)
    {
        ExpectedResult expected = _state.getExpectedResult();

        if (expected == null)
        {
            return operand;
        }

        // Should already have been handled
        assert (!expected.isInferringTypes());

        // FIXME: check against expected and cast/convert if necessary

        return operand;
    }

    private boolean typeOnly()
    {
        return _state.getExpectedResult().isInferringTypes();
    }

    private RuntimeException unimplemented(String syntax, ParserRuleContext ctx)
    {
        return new RuntimeException(String.format("Unimplemented syntax '%s'.", syntax));
    }

}
