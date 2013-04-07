package zuzu.compiler.ir;

import zuzu.lang.type.Type;
import zuzu.lang.type.TypeReference;

public abstract class Node
{
    /**
     * The next node in the BasicBlock.
     */
    private Node _next;

    public static enum NodeType
    {
        INT,
        LONG,
        FLOAT,
        DOUBLE,
        REFERENCE,
        VOID;

        public static NodeType fromType(Type type)
        {
            if (type.isReferenceType())
            {
                return REFERENCE;
            }
            else if (type.isVoid())
            {
                return VOID;
            }
            else if (type.isFloating())
            {
                return type.getImmediateSize() == 32 ? FLOAT : DOUBLE;
            }
            else
            {
                return type.getImmediateSize() == 64 ? LONG : INT;
            }
        }

        public static NodeType fromType(TypeReference ref)
        {
            return fromType(ref.getType());
        }
    }

    /*----------------
     * Object methods
     */

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Node)
        {
            Node that = (Node) obj;
            int nInputs = nInputs();
            if (nInputs == that.nInputs() && this.operationEquals(that))
            {
                if (isCommutative())
                {
                    assert (nInputs == 2);
                    return this.input(0).equals(that.input(0)) && this.input(1).equals(that.input(1))
                        || this.input(0).equals(that.input(1)) && this.input(1).equals(that.input(0));
                }
                else
                {
                    for (int i = 0; i < nInputs; ++i)
                    {
                        if (!this.input(i).equals(that.input(i)))
                        {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        int code = 0;
        if (isCommutative())
        {
            assert (nInputs() == 2);
            code = input(0).hashCode() + input(1).hashCode();
        }
        else
        {
            for (int i = nInputs(); --i >= 0;)
            {
                code = (code * 17) + input(i).hashCode();
            }
        }
        return code * 13 + operationHashCode();
    }

    protected boolean operationEquals(Node that)
    {
        return this.getClass() == that.getClass();
    }

    protected int operationHashCode()
    {
        return getClass().hashCode();
    }

    /*--------------
     * Node methods
     */

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

    public boolean isCommutative()
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

    public abstract NodeType type();

    public abstract Node replaceWithConstant();

    /*--------------
     * Interpreter
     */

    public abstract void interpret(InterpreterState state);

    /*------------------------
     * Linked list operations
     */

    public Node insertAfter(Node before)
    {
        _next = before._next;
        before._next = this;
        return this;
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
