package zuzu.compiler.ir;


public class CompilationState
{
    private final BasicBlock _entryBlock = new BasicBlock();
    private final BasicBlock _exitBlock = new BasicBlock();
    private BasicBlock _currentBlock;

    // private final SymbolTable _symbolTable = new SymbolTable();

    public CompilationState()
    {
    }

}
