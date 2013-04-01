package zuzu.compiler.ir;

public class BasicBlock
{
    private Node _firstNode = null;
    private Node _lastNode = null;
    
    private BasicBlock[] _predecessors;
    private BasicBlock[] _successors;

    public BasicBlock()
    {
    }

    public void appendNode(Node node)
    {
    }

    public void prependNode(Node node)
    {
        if (_firstNode == null)
        {
            _lastNode = node;
            _firstNode = node;
        }
        else
        {
            _firstNode = node.insertBefore(_firstNode);
        }
    }

}
