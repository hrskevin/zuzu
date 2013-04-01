package zuzu.compiler.interpreter;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import zuzu.compiler.ir.ConstantOperand;
import zuzu.compiler.ir.Operand;
import zuzu.compiler.parser.ZuzuParser;
import zuzu.compiler.parser.ZuzuParser.AndOpContext;
import zuzu.compiler.parser.ZuzuParser.EqualOpContext;
import zuzu.compiler.parser.ZuzuParser.ExprContext;
import zuzu.compiler.parser.ZuzuParser.OrOpContext;
import zuzu.compiler.parser.ZuzuParser.PrefixOpContext;
import zuzu.compiler.parser.ZuzuParserBaseVisitor;
import zuzu.compiler.parser.ZuzuToken;
import zuzu.lang.type.Type;
import zuzu.lang.type.TypeReference;
import zuzu.lang.type.Types;

// TODO: instead of evaluating values directly, instead compile to BasicBlocks of Nodes
// and then interpret those. Otherwise, once we start implementing macros/compiler syntaxes,
// the interpreter would have to reparse/expand syntax on every loop iteration. This will also
// be the same or similar to what we will need for real compilation.

@SuppressWarnings("boxing")
final class ZuzuInterpreterVisitor extends ZuzuParserBaseVisitor<Operand>
{
    // TODO: add state for global environment, local symbol table and DAG of basic blocks.

    @Override
    protected Operand aggregateResult(Operand aggregate, Operand nextResult)
    {
        return (nextResult != null) ? nextResult : aggregate;
    }

    @Override
    public Operand visitAndOp(AndOpContext ctx)
    {
        Operand left = interpretExpr(Types.BOOL, ctx.left);
        if (!Boolean.class.cast(left.node()))
        {
            return left;
        }
        Operand right = interpretExpr(Types.BOOL, ctx.right);
        if (right.isConstant() == left.isConstant())
        {
            return right;
        }

        return make(Types.BOOL, right.node(), right.isConstant() && left.isConstant());
    }

    @Override
    public Operand visitEqualOp(EqualOpContext ctx)
    {
        Operand left = ctx.left.accept(this);
        Operand right = ctx.right.accept(this);
        boolean isConstant = left.isConstant() && right.isConstant();

        switch (ctx.operator.getType())
        {
        case ZuzuParser.EQUALS:
            return make(Types.BOOL, left.node().equals(right.node()), isConstant);
        case ZuzuParser.NOTEQUALS:
            return make(Types.BOOL, !left.node().equals(right.node()), isConstant);
        case ZuzuParser.SAME:
            return make(Types.BOOL, left.node() == right.node(), isConstant);
        case ZuzuParser.NOTSAME:
            return make(Types.BOOL, left.node() != right.node(), isConstant);
        default:
            throw unimplemented(ctx.operator.getText(), ctx);
        }
    }

    @Override
    public Operand visitOrOp(OrOpContext ctx)
    {
        Operand left = interpretExpr(Types.BOOL, ctx.left);
        if (Boolean.class.cast(left.node()))
        {
            return left;
        }
        Operand right = interpretExpr(Types.BOOL, ctx.right);
        if (right.isConstant() == left.isConstant())
        {
            return right;
        }

        return make(Types.BOOL, right.node(), right.isConstant() && left.isConstant());
    }

    @Override
    public Operand visitPrefixOp(PrefixOpContext ctx)
    {
        switch (ctx.operator.getType())
        {
        case ZuzuParser.NOT:
        {
            Operand operand = interpretExpr(Types.BOOL, ctx.right);
            return make(Types.BOOL, !Boolean.class.cast(operand.node()), operand.isConstant());
        }
        case ZuzuParser.MINUS:
        {
            Operand operand = ctx.right.accept(this);
        }

        case ZuzuParser.BIT_NOT:
        default:
            throw unimplemented(ctx.operator.getText(), ctx);
        }
    }

    @Override
    public Operand visitTerminal(TerminalNode node)
    {
        Operand result = defaultResult();
        ZuzuToken token = (ZuzuToken) node.getSymbol();
        if (token.isLiteral())
        {
            result = make(token.literalType(), token.literalValue(), true);
        }
        return result;
    }

    /*-----------------
     * Package methods
     */

    // Operand compileIf(Operand condition, Operand trueBranch, Operand falseBranch)
    // {
    // }

    /*------------------
     * Private methods
     */

    private Operand interpretExpr(TypeReference toTypeRef, ExprContext ctx)
    {
        return cast(toTypeRef, ctx.accept(this), ctx);
    }

    private Operand cast(TypeReference toTypeRef, Operand operand, ParserRuleContext ast)
    {
        Type toType = toTypeRef.getType();
        Type fromType = operand.type();
        
        if (fromType.equals(toType))
        {
            return operand;
        }
        
        if (fromType.isSubtypeOf(toType))
        {
            return make(toType, operand.node(), operand.isConstant());
        }

        // TODO: better error behavior if cast fails
        return make(toType, toType.getJavaClass().cast(operand.node()), operand.isConstant());
    }
    
    private Operand make(TypeReference type, Object value, boolean isConstant)
    {
        return isConstant ? new ConstantOperand(type, value) : new Operand(type, value);
    }

    private RuntimeException unimplemented(String syntax, ParserRuleContext ctx)
    {
        return new RuntimeException(String.format("Unimplemented syntax '%s'.", syntax));
    }
}
