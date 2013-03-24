package zuzu.compiler.namespace;

import zuzu.compiler.parser.ZuzuIdentifier;
import zuzu.lang.annotation.NotNull;
import zuzu.lang.type.Type;

public interface Binding
{
    @NotNull
    ZuzuIdentifier name();

    boolean isConstant();

    @NotNull
    Type type();

    Object value();
}
