package advanced.design.lc284_peekingiterator;

import java.util.Iterator;

/**
 * Given an Iterator class interface with methods: next() and hasNext(),
 * design and implement a PeekingIterator that support the peek() operation --
 * it essentially peek() at the element that will be returned by the next call to next().
 */
public class PeekingIterator implements Iterator<Integer> {

    private Iterator<Integer> it;

    private Integer next;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        it = iterator;
        next();
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer ret = next;
        next = it.hasNext() ? it.next() : null;
        return ret;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

}
