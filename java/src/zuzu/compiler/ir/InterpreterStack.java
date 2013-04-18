package zuzu.compiler.ir;

import gnu.trove.stack.array.TIntArrayStack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

import zuzu.lang.type.BuiltinType;
import zuzu.lang.type.Type;

public class InterpreterStack
{
    private final TIntArrayStack _stack;
    private final Deque<Object> _referenceStack;
    private final Deque<Node.NodeType> _typeStack;

    public InterpreterStack(int initialCapacity)
    {
        _stack = new TIntArrayStack(initialCapacity);
        _referenceStack = new LinkedList<Object>();
        _typeStack = new ArrayDeque<Node.NodeType>(initialCapacity);
    }

    public InterpreterStack()
    {
        this(4);
    }

    public Object pop(Type type)
    {
        BuiltinType builtinType = type.getBuiltinType();
        if (builtinType != null)
        {
            switch (builtinType)
            {
            case BOOL:
                return new Boolean(popInt() != 0);
            case BYTE:
                return new Byte((byte) popInt());
            case CHAR:
                return new Character((char) popInt());
            case SHORT:
                return new Short((short) popInt());
            }
        }
        return pop();
    }

    public Object pop()
    {
        switch (_typeStack.peek())
        {
        case DOUBLE:
            return new Double(popDouble());
        case FLOAT:
            return new Float(popFloat());
        case INT:
            return new Integer(popInt());
        case LONG:
            return new Long(popLong());
        case REFERENCE:
            return popReference();
        default:
            return null;
        }
    }

    public void push(Object value)
    {
        Class<?> valueClass = value.getClass();
        if (valueClass == Integer.class)
        {
            pushInt(((Integer) value).intValue());
        }
        else if (valueClass == Long.class)
        {
            pushLong(((Long) value).longValue());
        }
        else if (valueClass == Double.class)
        {
            pushDouble(((Double) value).doubleValue());
        }
        else if (valueClass == Float.class)
        {
            pushFloat(((Float) value).floatValue());
        }
        else
        {
            pushReference(value);
        }
    }

    public double popDouble()
    {
        Node.NodeType type = _typeStack.pop();
        assert (type == Node.NodeType.DOUBLE);
        return Double.longBitsToDouble(((long) _stack.pop() << 32) + _stack.pop());
    }

    public void pushDouble(double value)
    {
        _typeStack.push(Node.NodeType.DOUBLE);
        long rawBits = Double.doubleToRawLongBits(value);
        _stack.push((int) rawBits);
        _stack.push((int) (rawBits >> 32));
    }

    public float popFloat()
    {
        Node.NodeType type = _typeStack.pop();
        assert (type == Node.NodeType.FLOAT);
        return Float.intBitsToFloat(_stack.pop());
    }

    public void pushFloat(float value)
    {
        _typeStack.push(Node.NodeType.FLOAT);
        _stack.push(Float.floatToRawIntBits(value));
    }

    public int popInt()
    {
        Node.NodeType type = _typeStack.pop();
        assert (type == Node.NodeType.INT);
        return _stack.pop();
    }

    public void pushInt(int value)
    {
        _typeStack.push(Node.NodeType.INT);
        _stack.push(value);
    }

    public long popLong()
    {
        Node.NodeType type = _typeStack.pop();
        assert (type == Node.NodeType.LONG);
        return ((long) _stack.pop() << 32) + _stack.pop();
    }

    public void pushLong(long value)
    {
        _typeStack.push(Node.NodeType.LONG);
        _stack.push((int) value);
        _stack.push((int) (value >> 32));
    }

    public Object popReference()
    {
        Node.NodeType type = _typeStack.pop();
        assert (type == Node.NodeType.REFERENCE);
        int i = _stack.pop();
        assert (i + 1 == _referenceStack.size());
        return _referenceStack.pop();
    }

    public void pushReference(Object value)
    {
        _typeStack.push(Node.NodeType.REFERENCE);
        _stack.push(_referenceStack.size());
        _referenceStack.push(value);
    }
}
