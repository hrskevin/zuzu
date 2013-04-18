package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public class InvalidType extends AbstractType
{
    public static @NotNull
    InvalidType INSTANCE = new InvalidType();
    
    private InvalidType()
    {
    }

    @Override
    public Class<?> getJavaClass()
    {
        return null;
    }

    @Override
    public @NotNull
    String getName()
    {
        return "invalid";
    }

    @Override
    public boolean isAny()
    {
        return true;
    }

    @Override
    public boolean isInvalid()
    {
        return true;
    }
}
