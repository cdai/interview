package fundamentals.stack.nested.lc341_flattennestedlistiterator;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Stefan incremental 'Real' iterator
 */
public class NestedInteger_Increment implements Iterator<Integer> {

    private Stack<Iterator<NestedInteger>> stack;

    private NestedInteger nextInt;

    public NestedInteger_Increment(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextInt.getInteger(); // assume user has called hasNext() every time, so stack top must be an Integer
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            if (!stack.peek().hasNext()) { // Top iterator is complete, oop it to start on next
                stack.pop();
            } else if ((nextInt = stack.peek().next()).isInteger()) { // Find an integer, now return
                return true;
            } else {
                stack.push(nextInt.getList().iterator()); // Another list, push it and continue to find in it
            }
        }
        return false;
    }

}
