package zuzu.compiler.namespace;

import java.util.ArrayDeque;
import java.util.HashMap;

import zuzu.compiler.parser.ZuzuIdentifier;
import zuzu.lang.annotation.NotNull;

public class SymbolTable implements OpenNamespace
{
    /*--------
     * State
     */

    private static class Entry
    {
        private final @NotNull Binding _binding;
        private final Entry _outerEntry;
        private final Entry _nextInScope;

        private Entry(@NotNull Binding binding, Entry outerEntry, Entry nextInScope)
        {
            _binding = binding;
            _outerEntry = outerEntry;
            _nextInScope = nextInScope;
        }
    }

    private final HashMap<ZuzuIdentifier, Entry> _table;

    private final @NotNull Namespace _parentNamespace;

    private final ArrayDeque<Entry> _scopeStack = new ArrayDeque<Entry>();

    /*
     * Construction
     */

    SymbolTable(@NotNull Namespace parent)
    {
        _parentNamespace = parent;
        _table = new HashMap<ZuzuIdentifier, Entry>();
    }

    /*
     * Namespace methods
     */

    @Override public Binding get(@NotNull ZuzuIdentifier id)
    {
        Binding binding = null;
        Entry entry = _table.get(id);
        if (entry != null)
        {
            binding = entry._binding;
        }
        return binding;
    }

    @Override public boolean isClass()
    {
        return false;
    }

    @Override public boolean isFunction()
    {
        return true;
    }

    @Override public boolean isHygienic()
    {
        return true;
    }

    @Override public boolean isTopLevel()
    {
        return false;
    }

    @Override public Namespace parentNamespace()
    {
        return _parentNamespace;
    }

    @Override public @NotNull Namespace topNamespace()
    {
        return _parentNamespace.topNamespace();
    }

    @Override public void add(@NotNull Binding binding)
    {
        ZuzuIdentifier id = binding.name();
        Entry outerEntry = _table.get(id);
        Entry nextEntry = _scopeStack.poll();
        Entry entry = new Entry(binding, outerEntry, nextEntry);
        _scopeStack.push(entry);
        _table.put(id, entry);
    }

    @Override public boolean isOpen()
    {
        return true;
    }

    /*----------------------
     * Symbol table methods
     */

    void popScope()
    {
        Entry entry = _scopeStack.poll();
        while (entry != null)
        {
            Entry outerEntry = entry._outerEntry;
            ZuzuIdentifier id = entry._binding.name();
            if (outerEntry != null)
            {
                _table.put(id, outerEntry);
            }
            else
            {
                _table.remove(id);
            }
            entry = entry._nextInScope;
        }
    }

    void pushScope()
    {
        _scopeStack.push(null);
    }
}
