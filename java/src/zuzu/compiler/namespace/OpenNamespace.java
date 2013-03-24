package zuzu.compiler.namespace;

import zuzu.lang.annotation.NotNull;

public interface OpenNamespace extends Namespace
{
    void add(@NotNull Binding binding);

    @Override
    boolean isOpen();
}
