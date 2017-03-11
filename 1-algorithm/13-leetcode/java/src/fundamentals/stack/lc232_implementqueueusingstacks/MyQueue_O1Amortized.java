package fundamentals.stack.lc232_implementqueueusingstacks;

import java.util.Stack;

/**
 * Stefan's amortized O(1) time solution
 * In my previous impl, either push() or pop() was O(N)
 * But now partial elements are in input and output moved only once.
 *
 * My 2AC
 */
public class MyQueue_O1Amortized {

    private Stack<Integer> input = new Stack<>();

    private Stack<Integer> output = new Stack<>();

    public void push(int x) {
        input.push(x);
    }

    public int pop() {
        if (transfer()) {
            return output.pop();
        }
        return 0;
    }

    public int peek() {
        if (transfer()) {
            return output.peek();
        }
        return 0;
    }

    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }

    // Transfer each element only once, so pop and peek in O(1) amortized
    // Return if transfer successfully (at least one element transferred)
    private boolean transfer() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }
        return !output.isEmpty();
    }

}
