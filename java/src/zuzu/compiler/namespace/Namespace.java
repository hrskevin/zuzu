package zuzu.compiler.namespace;

import zuzu.compiler.parser.ZuzuIdentifier;
import zuzu.lang.annotation.NotNull;

public interface Namespace
{
    Binding get(@NotNull ZuzuIdentifier id);

    boolean isClass();

    boolean isFunction();

    boolean isHygienic();

    boolean isOpen();

    boolean isTopLevel();

    Namespace parentNamespace();

    @NotNull Namespace topNamespace();
}
