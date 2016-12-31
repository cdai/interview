package fundamentals.stack.nested.lc341_flattennestedlistiterator;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Iterate like a real iterator one by one
 */
public class NestedIterator implements Iterator<Integer> {

    private Stack<Iterator<NestedInteger>> s = new Stack<>();

    private Integer nextInt;

    public NestedIterator(List<NestedInteger> nestedList) {
        if (!nestedList.isEmpty()) s.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        Integer ret = nextInt;
        nextInt = null;
        return ret;
    }

    @Override
    public boolean hasNext() {
        // Find next integer that is not a list
        while (!s.isEmpty() && nextInt == null) {
            if (s.peek().hasNext()) {
                NestedInteger ni = s.peek().next();
                if (ni.isInteger()) nextInt = ni.getInteger();
                else s.push(ni.getList().iterator());
            } else s.pop();
        }
        return nextInt != null;
    }

}
