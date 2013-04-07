package zuzu.compiler.ir;

import zuzu.compiler.ir.branch.JumpNode;


public class CompilationState
{
    private final BasicBlock _entryBlock = new BasicBlock();
    private final BasicBlock _exitBlock = new BasicBlock();
    private BasicBlock _currentBlock;

    // private final SymbolTable _symbolTable = new SymbolTable();

    public CompilationState()
    {
        _entryBlock.appendNode(new JumpNode());
        _currentBlock = new BasicBlock(_entryBlock);
        _entryBlock.setSuccessors(_currentBlock);
    }

    public <N extends Node> N addNode(N node)
    {
        getCurrentBlock().appendNode(node);
        return node;
    }
    
    public BasicBlock getCurrentBlock()
    {
        return _currentBlock;
    }

    public void interpret(InterpreterState state)
    {
        BasicBlock block = _entryBlock;

        while (block != null)
        {
            for (Node node = block.getFirstNode(); node != null; node = node.next())
            {
                node.interpret(state);
            }

            if (block.successorsComputed())
            {
                block = block.getSuccessor(state.popInt());
            }
            else
            {
                break;
            }
        }
    }
}
