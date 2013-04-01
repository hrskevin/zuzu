package zuzu.compiler.ir;

public abstract class Node
{
    /**
     * The next node in the BasicBlock.
     */
    private Node _next;

    public static enum Type
    {
        INT,
        LONG,
        FLOAT,
        DOUBLE,
        REFERENCE,
        VOID;
    }

    public boolean constantInputs()
    {
        for (int i = 0, end = nInputs(); i < end; ++i)
        {
            if (!input(i).isConstant())
            {
                return false;
            }
        }
        return true;
    }

    public Object constantValue()
    {
        return null;
    }

    public abstract Node input(int i);

    public boolean isBranch()
    {
        return false;
    }

    public boolean isConstant()
    {
        return false;
    }

    /**
     * If {@link #isBranch} is true, returns the number of branches, i.e. the number of successors
     * to the {@link BasicBlock} that ends with this node.
     */
    public int nBranches()
    {
        return 0;
    }

    public abstract int nInputs();

    public abstract Type type();

    public abstract Node replaceWithConstant();

    /*--------------
     * Interpreter
     */

    public abstract void interpret(InterpreterState state);

    /*------------------------
     * Linked list operations
     */

    public void insertAfter(Node before)
    {
        _next = before._next;
        before._next = this;
    }

    public Node insertBefore(Node head)
    {
        this._next = head;
        return this;
    }

    public Node next()
    {
        return _next;
    }

    /**
     * Removes the next node after this one and returns it.
     */
    public Node removeNext()
    {
        Node next = _next;
        _next = next == null ? null : next._next;
        return next;
    }
}
