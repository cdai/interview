package fundamentals.stack.lc232_implementqueueusingstacks;

import java.util.Stack;

/**
 * Stefan's amortized O(1) time solution
 * In my previous impl, either push() or pop() was O(N)
 * But now partial elements are in input and output moved only once.
 */
public class MyQueue_O1Amortized {

    private Stack<Integer> input = new Stack<>();

    private Stack<Integer> output = new Stack<>();

    // Push element x to the back of queue.
    public void push(int x) {
        input.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        peek();
        if (!output.isEmpty()) {
            output.pop();
        }
    }

    // Get the front element.
    public int peek() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }
        if (output.isEmpty()) {
            return 0;
        }
        return output.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }

}
