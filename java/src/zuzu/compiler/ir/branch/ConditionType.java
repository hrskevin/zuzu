package zuzu.compiler.ir.branch;

/**
 * Enumerates conditions used for 'if' branching.
 * <dl>
 * <dt>EQ</dt>
 * <dd>equality (===)</dd>
 * <dt>NE</dt>
 * <dd>inequality (!==)</dd>
 * <dt>LT</dt>
 * <dd>less than (<)</dd>
 * <dt>GT</dt>
 * <dd>greater than (>)</dd>
 * <dt>LE</dt>
 * <dd>less than or equal (<=)</dd>
 * <dt>GE</dt>
 * <dd>greater than or equal (>=)</dd>
 * <dt>NULL</dt>
 * <dd>value is null</dd>
 * <dt>NONNULL</dt>
 * <dd>value is not null</dd>
 * </dl>
 */
public enum ConditionType
{
        EQ,
        NE,
        LT,
        GT,
        LE,
        GE,
        NULL,
        NONNULL;
}
