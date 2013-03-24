package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public class KeywordParameterDescriptor extends NamedParameterDescriptor
{
    private final Object _defaultValue;

    KeywordParameterDescriptor(@NotNull String name, @NotNull Type type, Object defaultValue)
    {
        super(name, type);
        _defaultValue = defaultValue;
    }

    @Override public Object getDefaultValue()
    {
        return _defaultValue;
    }

    @Override public boolean hasDefaultValue()
    {
        return true;
    }
}
