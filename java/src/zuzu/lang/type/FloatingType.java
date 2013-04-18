package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;


public abstract class FloatingType extends AbstractType
{

    FloatingType()
    {
    }

    @Override
    public abstract int getPrecision();

    @Override
    public final boolean isFloating()
    {
        return true;
    }

    @Override
    public final boolean isPrimitive()
    {
        return true;
    }

    @Override
    public final boolean isNumeric()
    {
        return true;
    }

    @Override
    public final boolean isSubtypeOf(@NotNull Type that)
    {
        if (that.isAny())
        {
            return true;
        }

        if (!that.isFloating())
        {
            return false;
        }

        return this.getPrecision() <= that.getPrecision();
    }
}
