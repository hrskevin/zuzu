package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public class ParameterDescriptor
{
    private final @NotNull Type _type;

    ParameterDescriptor(@NotNull Type type)
    {
        _type = type;
    }

    public @NotNull
    Type getType()
    {
        return _type;
    }

    public Object getDefaultValue()
    {
        return null;
    }

    public String getName()
    {
        return null;
    }

    public boolean hasDefaultValue()
    {
        return false;
    }

    public boolean isReturnType()
    {
        return false;
    }
}
