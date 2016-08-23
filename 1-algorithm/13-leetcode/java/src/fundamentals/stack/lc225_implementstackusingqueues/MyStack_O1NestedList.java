package fundamentals.stack.lc225_implementstackusingqueues;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Stefan nested list O(1) solution
 */
public class MyStack_O1NestedList {

    // No need to create a queue first
    private Queue<Object> queue = null;

    // Push element x onto stack.
    public void push(int x) {
        queue = new LinkedList<>(Arrays.asList(x, queue));
    }

    // Removes the element on top of the stack.
    public void pop() {
        if (!empty()) {
            queue.poll();
            queue = (Queue<Object>) queue.poll();
        }
    }

    // Get the top element.
    public int top() {
        return empty() ? 0 : (int) queue.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return queue == null;
    }

}
