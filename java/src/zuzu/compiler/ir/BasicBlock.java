package zuzu.compiler.ir;

import java.util.List;

import com.google.common.collect.Lists;

public class BasicBlock
{
    /*-------
     * State
     */

    private Node _firstNode = null;
    private Node _lastNode = null;
    
    private List<BasicBlock> _predecessors;
    private BasicBlock[] _successors;

    private int _mask;

    private static enum MaskBit
    {
            FILLED,
            SEALED;

        private int bit()
        {
            return 1 << ordinal();
        }
    }

    /*
     * Construction
     */

    public BasicBlock(BasicBlock... predecessors)
    {
        if (predecessors.length > 0)
        {
            setPredecessors(predecessors);
        }
    }

    /*--------------
     * Node methods
     */

    public void appendNode(Node node)
    {
        assert (!isFilled());
        if (_firstNode == null)
        {
            _lastNode = _firstNode = node;
        }
        else
        {
            _lastNode = node.insertAfter(_lastNode);
        }
    }

    public Node getFirstNode()
    {
        return _firstNode;
    }

    /**
     * True if all nodes have been added to the block. not including those arising from optimization
     * passes.
     */
    final boolean isFilled()
    {
        return isSet(MaskBit.FILLED);
    }

    public void prependNode(Node node)
    {
        if (_firstNode == null)
        {
            _lastNode = _firstNode = node;
        }
        else
        {
            _firstNode = node.insertBefore(_firstNode);
        }
    }

    /*---------------------
     * Predecessor methods
     */

    public void addPredecessor(BasicBlock block)
    {
        assert (!isSealed());
        if (_predecessors != null)
        {
            _predecessors.add(block);
        }
        else
        {
            _predecessors = Lists.newArrayList(block);
        }
    }

    public int nPredecessors()
    {
        return _predecessors == null ? 0 : _predecessors.size();
    }

    public BasicBlock getPredecessor(int i)
    {
        return _predecessors.get(i);
    }

    /**
     * True if block is sealed to adding predecessors.
     */
    final boolean isSealed()
    {
        return isSet(MaskBit.SEALED);
    }

    public void setPredecessors(BasicBlock...blocks)
    {
        _predecessors = Lists.newArrayList(blocks);
        set(MaskBit.SEALED);
    }

    /*------------------
     * Sucessor methods
     */

    public BasicBlock getSuccessor(int i)
    {
        return _successors[i];
    }

    public int nSuccessors()
    {
        return _successors.length;
    }
    
    public void setSuccessors(BasicBlock... blocks)
    {
        _successors = blocks;
    }

    public boolean successorsComputed()
    {
        return _successors != null;
    }
    
    /*-----------------
     * Private methods
     */
    
    private boolean isSet(MaskBit bit)
    {
        return (_mask & bit.bit()) != 0;
    }

    private void set(MaskBit bit)
    {
        _mask |= bit.bit();
    }
}
