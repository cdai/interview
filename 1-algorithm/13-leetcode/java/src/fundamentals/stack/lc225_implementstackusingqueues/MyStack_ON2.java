package fundamentals.stack.lc225_implementstackusingqueues;

import java.util.LinkedList;
import java.util.Queue;

/**
 * My 2AC: use one or two queues, and much clean code.
 */
public class MyStack_ON2 {

    private Queue<Integer> queue = new LinkedList<>();

    private Queue<Integer> temp = new LinkedList<>();

    // Push element x onto stack.
    public void push(int x) {
        queue.offer(x);
        for (int i = 1; i < queue.size(); i++) { // Rotate N-1 times
            queue.offer(queue.poll());
        }
    }

    public void push2(int x) {
        while (!queue.isEmpty()) {
            temp.offer(queue.poll());
        }
        queue.offer(x);
        while (!temp.isEmpty()) {
            queue.offer(temp.poll());
        }
    }

    // Removes the element on top of the stack.
    public void pop() {
        if (!queue.isEmpty()) {
            queue.poll();
        }
    }

    // Get the top element.
    public int top() {
        if (queue.isEmpty()) {
            return 0;
        }
        return queue.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return queue.isEmpty();
    }

}
