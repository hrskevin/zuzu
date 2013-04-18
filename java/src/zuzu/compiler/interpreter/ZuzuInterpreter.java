package zuzu.compiler.interpreter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import zuzu.compiler.ir.CompilationState;
import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.Operand;
import zuzu.compiler.parser.ZuzuLexer;
import zuzu.compiler.parser.ZuzuParser;

public class ZuzuInterpreter
{
    public static void main(String[] args)
    {
        String prompt = "zuzu> ";
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(System.in));
        ZuzuLexer lexer = new ZuzuLexer(null);
        ZuzuParser parser = new ZuzuParser(null);
        
        try
        {
            while (true)
            {
                System.out.print(prompt);
                String str = reader.readLine();

                if (str.equals("{exit}"))
                {
                    break;
                }

                if (str.isEmpty())
                {
                    continue;
                }

                // TODO: support multi-line statements
                lexer.setInputStream(new ANTLRInputStream(str));
                lexer.setLine(reader.getLineNumber());

                CommonTokenStream tokenStream = new CommonTokenStream(lexer);
                parser.setTokenStream(tokenStream);

                try
                {
                    ZuzuParser.StmtContext stmt = parser.stmt();
                    CompilationState state = new CompilationState();
                    ZuzuFunctionCompiler compiler = new ZuzuFunctionCompiler(state);
                    Operand operand = stmt.accept(compiler);
                    InterpreterState interpreterState = new InterpreterState();
                    state.interpret(interpreterState);
                    if (!operand.type().isVoid())
                    {
                        // FIXME: Don't pop raw node value. Instead wrap it with an Any with the
                        // appropriate type.
                        System.out.format("%s\n", interpreterState.pop(operand.type()));
                    }
                }
                catch (Exception ex)
                {
                    System.out.format("Caught %s\n", ex);
                }
            }
        }
        catch (IOException ex)
        {
        }
    }
}
