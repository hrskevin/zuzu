package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public class ReturnDescriptor extends ParameterDescriptor
{
    ReturnDescriptor(@NotNull Type type)
    {
        super(type);
    }

    @Override public boolean isReturnType()
    {
        return true;
    }
}
