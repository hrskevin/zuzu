package zuzu.compiler.ir.node;

import zuzu.compiler.ir.InterpreterState;
import zuzu.compiler.ir.Node;

public class TypeInferenceNode extends Node
{
    public static final TypeInferenceNode INSTANCE = new TypeInferenceNode();

    private TypeInferenceNode()
    {
    }

    @Override
    public Node input(int i)
    {
        return null;
    }

    @Override
    public int nInputs()
    {
        return 0;
    }

    @Override
    public NodeType type()
    {
        return null;
    }

    @Override
    public Node replaceWithConstant()
    {
        return this;
    }

    @Override
    public void interpret(InterpreterState state)
    {
    }

    @Override
    public boolean isValid()
    {
        return false;
    }
}
