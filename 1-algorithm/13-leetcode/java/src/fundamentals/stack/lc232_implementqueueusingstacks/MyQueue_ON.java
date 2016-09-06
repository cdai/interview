package fundamentals.stack.lc232_implementqueueusingstacks;

import java.util.Stack;

/**
 * Implement the following operations of a queue using stacks.
 * You must use only standard operations of a stack --
 * which means only push to top, peek/pop from top, size, and is empty operations are valid.
 * You may assume that all operations are valid (for example, no pop or peek operations will be
 * called on an empty queue).
 *
 * My 1AC
 */
public class MyQueue_ON {

    public static void main(String[] args) {
        MyQueue_ON queue = new MyQueue_ON();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.peek());
        queue.pop();
        System.out.println(queue.peek());
    }

    private Stack<Integer> first = new Stack<>();

    private Stack<Integer> second = new Stack<>();

    // Push element x to the back of queue.
    public void push(int x) {
        // Keep first stack in queue order
        while (!first.isEmpty())
            second.push(first.pop());
        second.push(x);
        while (!second.isEmpty())
            first.push(second.pop());
    }

    // Removes the element from in front of queue.
    public void pop() {
        if (!first.isEmpty())
            first.pop();
    }

    // Get the front element.
    public int peek() {
        if (first.isEmpty()) return 0;
        return first.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return first.isEmpty();
    }

}
