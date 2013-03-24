package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public class NamedParameterDescriptor extends ParameterDescriptor
{
    private final @NotNull String _name;

    NamedParameterDescriptor(@NotNull String name, @NotNull Type type)
    {
        super(type);
        _name = name;
    }

    @Override public @NotNull String getName()
    {
        return _name;
    }
}
