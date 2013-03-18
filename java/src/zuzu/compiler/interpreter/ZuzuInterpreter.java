package zuzu.compiler.interpreter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import zuzu.compiler.parser.ZuzuLexer;
import zuzu.compiler.parser.ZuzuParser;
import zuzu.compiler.parser.ZuzuParser.StmtContext;

public class ZuzuInterpreter
{
    private final ZuzuInterpreterVisitor _visitor;

    public ZuzuInterpreter()
    {
        _visitor = new ZuzuInterpreterVisitor();
    }

    public Object interpretStmt(StmtContext ctxt)
    {
        return _visitor.visitStmt(ctxt);
    }

    public static void main(String[] args)
    {
        String prompt = "zuzu> ";
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(System.in));
        ZuzuLexer lexer = new ZuzuLexer(null);
        ZuzuParser parser = new ZuzuParser(null);
        ZuzuInterpreter interpreter = new ZuzuInterpreter();
        
        try
        {
            while (true)
            {
                System.out.print(prompt);
                String str = reader.readLine();

                lexer.setInputStream(new ANTLRInputStream(str));
                lexer.setLine(reader.getLineNumber());

                CommonTokenStream tokenStream = new CommonTokenStream(lexer);
                parser.setTokenStream(tokenStream);

                ZuzuParser.StmtContext stmt = parser.stmt();
                Object result = interpreter.interpretStmt(stmt);
                System.out.format("%s\n", result);
            }
        }
        catch (IOException ex)
        {
        }
    }
}
