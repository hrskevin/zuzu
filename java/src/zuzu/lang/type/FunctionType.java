package zuzu.lang.type;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import zuzu.lang.annotation.NotNull;

public class FunctionType extends AbstractType
{
    private final MethodType _methodType;

    private FunctionType(MethodType methodType)
    {
        _methodType = methodType;
    }

    @Override public Class<?> getJavaClass()
    {
        return MethodHandle.class;
    }

    @Override public @NotNull String getName()
    {
        // TODO: produce name in Zuzu source format
        return _methodType.toString();
    }

    public @NotNull MethodType getMethodType()
    {
        return _methodType;
    }
}
