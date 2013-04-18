package zuzu.lang.type;

import zuzu.lang.annotation.NotNull;

public abstract class IntegralType extends AbstractType
{
    IntegralType()
    {
    }
    
    @Override
    public abstract int getPrecision();

    @Override
    public final boolean isExplicitlyCastableTo(@NotNull Type that)
    {
        return that.isAny() || that.isNumeric();
    }

    @Override
    public final boolean isInteger()
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

        if (!that.isNumeric())
        {
            return false;
        }
        
        switch (Integer.signum(Integer.compare(this.getPrecision(), that.getPrecision())))
        {
        case -1: // smaller than that
            return this.isUnsigned() || !that.isUnsigned();
        case 0: // same precision
            return this.equals(that);
        case 1: // larger than that
        default:
            return false;
        }
    }

    @Override
    public final boolean isSubrepOf(@NotNull Type that)
    {
        if (!that.isInteger())
        {
            return false;
        }

        int thatPrecision = that.getPrecision();
        switch (Integer.signum(Integer.compare(this.getPrecision(), thatPrecision)))
        {
        case -1: // smaller than that
            return thatPrecision <= 32;
        case 0: // same precision
            return this.equals(that);
        case 1: // larger than that
        default:
            return false;
        }
    }
}
