package zuzu.compiler.interpreter;

import zuzu.compiler.parser.ZuzuParser.BooleanLiteralContext;
import zuzu.compiler.parser.ZuzuParser.ExprContext;
import zuzu.compiler.parser.ZuzuParser.ExprStmtContext;
import zuzu.compiler.parser.ZuzuParser.FloatLiteralContext;
import zuzu.compiler.parser.ZuzuParser.InferredVarDeclContext;
import zuzu.compiler.parser.ZuzuParser.IntegerLiteralContext;
import zuzu.compiler.parser.ZuzuParser.LiteralContext;
import zuzu.compiler.parser.ZuzuParser.LiteralExprContext;
import zuzu.compiler.parser.ZuzuParser.StmtContext;
import zuzu.compiler.parser.ZuzuParser.StringLiteralContext;
import zuzu.compiler.parser.ZuzuParser.VarDeclContext;
import zuzu.compiler.parser.ZuzuParserBaseVisitor;

final class ZuzuInterpreterVisitor extends ZuzuParserBaseVisitor<Object>
{
    @Override
    public Object visitBooleanLiteral(BooleanLiteralContext ctx)
    {
        return ctx.token.literalValue();
    }

    @Override
    public Object visitExprStmt(ExprStmtContext ctx)
    {
        return visitExpr(ctx.expr());
    }

    @Override
    public Object visitFloatLiteral(FloatLiteralContext ctx)
    {
        return ctx.token.literalValue();
    }

    @Override
    public Object visitIntegerLiteral(IntegerLiteralContext ctx)
    {
        return ctx.token.literalValue();
    }

    @Override
    public Object visitLiteral(LiteralContext ctxt)
    {
        switch (ctxt.altNum)
        {
        case 1: // NULL
        case 2: // CHAR
            return ctxt.token.literalValue();
        case 3: // boolean
            return visitBooleanLiteral(ctxt.booleanLiteral());
        case 4: // integer
            return visitIntegerLiteral(ctxt.integerLiteral());
        case 5: // float
            return visitFloatLiteral(ctxt.floatLiteral());
        case 6: // string
            return visitStringLiteral(ctxt.stringLiteral());
        }
        return null; // should never happen
    }

    @Override
    public Object visitLiteralExpr(LiteralExprContext ctxt)
    {
        return visitLiteral(ctxt.literal());
    }

    @Override
    public Object visitStringLiteral(StringLiteralContext ctx)
    {
        return ctx.token.literalValue();
    }

    // -------------
    // New methods
    //

    public Object visitExpr(ExprContext ctxt)
    {
        // TODO: use switch(ctxt.altNum) when order is considered stable

        if (ctxt instanceof LiteralExprContext)
        {
            return visitLiteralExpr((LiteralExprContext) ctxt);
        }
        // if (ctxt instanceof IdExprContext)
        // {
        // return visitIdExpr((IdExprContext) ctxt);
        // }

        throw new Error("Unsupported");
    }

    public Object visitStmt(StmtContext ctx)
    {
        switch (ctx.altNum)
        {
        case 1:
            return visitInferredVarDecl((InferredVarDeclContext) ctx);
        case 2:
            return visitVarDecl((VarDeclContext) ctx);
        case 3:
            return visitExprStmt((ExprStmtContext) ctx);
        }
        return null; // should never happen
    }
}
