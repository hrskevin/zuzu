package zuzu.compiler.ir;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class InterpreterStack
{
    private double[] _doubleStack = new double[4];
    private int _doubleStackTop = 0;
    private float[] _floatStack = new float[4];
    private int _floatStackTop = 0;
    private int[] _intStack = new int[4];
    private int _intStackTop = 0;
    private long[] _longStack = new long[4];
    private int _longStackTop = 0;
    private final Deque<Object> _referenceStack = new ArrayDeque<Object>();
    private final Deque<Node.NodeType> _stack = new ArrayDeque<Node.NodeType>();

    public InterpreterStack()
    {
    }

    public Object pop()
    {
        switch (_stack.peek())
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
        Node.NodeType type = _stack.pop();
        assert (type == Node.NodeType.DOUBLE);
        return _doubleStack[--_doubleStackTop];
    }

    public void pushDouble(double value)
    {
        _stack.push(Node.NodeType.DOUBLE);
        if (_doubleStackTop == _doubleStack.length)
        {
            _doubleStack = Arrays.copyOf(_doubleStack, _doubleStack.length * 2);
        }
        _doubleStack[_doubleStackTop++] = value;
    }

    public float popFloat()
    {
        Node.NodeType type = _stack.pop();
        assert (type == Node.NodeType.FLOAT);
        return _floatStack[--_floatStackTop];
    }

    public void pushFloat(float value)
    {
        _stack.push(Node.NodeType.FLOAT);
        if (_floatStackTop == _floatStack.length)
        {
            _floatStack = Arrays.copyOf(_floatStack, _floatStack.length * 2);
        }
        _floatStack[_floatStackTop++] = value;
    }

    public int popInt()
    {
        Node.NodeType type = _stack.pop();
        assert (type == Node.NodeType.INT);
        return _intStack[--_intStackTop];
    }

    public void pushInt(int value)
    {
        _stack.push(Node.NodeType.INT);
        if (_intStackTop == _intStack.length)
        {
            _intStack = Arrays.copyOf(_intStack, _intStack.length * 2);
        }
        _intStack[_intStackTop++] = value;
    }

    public long popLong()
    {
        Node.NodeType type = _stack.pop();
        assert (type == Node.NodeType.LONG);
        return _longStack[--_longStackTop];
    }

    public void pushLong(long value)
    {
        _stack.push(Node.NodeType.LONG);
        if (_longStackTop == _longStack.length)
        {
            _longStack = Arrays.copyOf(_longStack, _longStack.length * 2);
        }
        _longStack[_longStackTop++] = value;
    }

    public Object popReference()
    {
        Node.NodeType type = _stack.pop();
        assert (type == Node.NodeType.REFERENCE);
        return _referenceStack.pop();
    }

    public void pushReference(Object value)
    {
        _stack.push(Node.NodeType.REFERENCE);
        _referenceStack.push(value);
    }
}
