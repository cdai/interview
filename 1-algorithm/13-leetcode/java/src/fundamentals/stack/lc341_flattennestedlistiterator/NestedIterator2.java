package fundamentals.stack.lc341_flattennestedlistiterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Hold only iterator
 */
public class NestedIterator2 implements Iterator<Integer> {

    private Iterator<Integer> it;

    public NestedIterator2(List<NestedInteger> nestedList) {
        List<Integer> flat = new ArrayList<>();
        doFlat(flat, nestedList);
        it = flat.iterator();
    }

    // Preorder DFS traversal
    private void doFlat(List<Integer> flat, List<NestedInteger> nested) {
        for (NestedInteger n : nested) {
            if (n.isInteger()) {
                flat.add(n.getInteger());
            } else {
                doFlat(flat, n.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return it.next();
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }

}
