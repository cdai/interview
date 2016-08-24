package fundamentals.stack.lc341_flattennestedlistiterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Given a nested list of integers, implement an iterator to flatten it.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * Example 1: Given the list [[1,1],2,[1,1]], By calling next repeatedly until hasNext returns false,
 *  the order of elements returned by next should be: [1,1,2,1,1].
 * Example 2: Given the list [1,[4,[6]]], By calling next repeatedly until hasNext returns false,
 *  the order of elements returned by next should be: [1,4,6].
 */
public class NestedIterator1 implements Iterator<Integer> {

    private List<Integer> flatInts = new ArrayList<>();

    private int cur = 0;

    public NestedIterator1(List<NestedInteger> nestedList) {
        convert(nestedList);
    }

    // DFS traverse
    private void convert(List<NestedInteger> nestedList) {
        for (NestedInteger i : nestedList) {
            if (i.isInteger()) {
                flatInts.add(i.getInteger());
            } else {
                convert(i.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return flatInts.get(cur++);
    }

    @Override
    public boolean hasNext() {
        return cur < flatInts.size();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
