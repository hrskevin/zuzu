package zuzu.lang.type;

public class Any implements ZuzuObject
{
    private volatile Object _value;

    public Any()
    {
        _value = null;
    }

    public Any(Object value)
    {
        _value = value;
    }

    /*
     * ZuzuObject methods
     */

    @Override public Type getType()
    {
        return null;
    }

    /*
     * Any methods
     */

    public Any call(Any... args)
    {
        return null;
    }

    public Any invoke(String methodName, Any... args)
    {
        return null;
    }

    public Object toJavaValue()
    {
        return _value;
    }
}
