package zuzu.compiler.ir;

import zuzu.lang.annotation.Nullable;
import zuzu.lang.type.Type;

public interface ExpectedResult
{
    /**
     * Indicates compiling for type inference only.
     */
    public abstract boolean isInferringTypes();

    /**
     * Maximum number of values expected
     */
    public abstract int maxArity();

    /**
     * Minimum number of values expected
     */
    public abstract int minArity();

    /**
     * Expected type of ith return value where {@code i} is less than {@link #maxArity} and no less
     * than {@link #minArity}.
     */
    public abstract Type expectedType(int i);
    
    public @Nullable
    Type expectedType();
}
