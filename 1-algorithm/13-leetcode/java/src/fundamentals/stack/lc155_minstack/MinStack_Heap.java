package fundamentals.stack.lc155_minstack;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *  push(x) -- Push element x onto stack.
 *  pop() -- Removes the element on top of the stack.
 *  top() -- Get the top element.
 *  getMin() -- Retrieve the minimum element in the stack.
 * Example:
 *  MinStack minStack = new MinStack();
 *  minStack.push(-2);
 *  minStack.push(0);
 *  minStack.push(-3);
 *  minStack.getMin();   --> Returns -3.
 *  minStack.pop();
 *  minStack.top();      --> Returns 0.
 *  minStack.getMin();   --> Returns -2.
 */
public class MinStack_Heap {

    private Stack<Integer> stack;

    private Queue<Integer> heap;

    /** initialize your data structure here. */
    public MinStack_Heap() {
        this.stack = new Stack<>();
        this.heap = new PriorityQueue<>();
    }

    public void push(int x) {
        stack.push(x);
        heap.offer(x);
    }

    public void pop() {
        if (!stack.isEmpty()) {
            heap.remove(stack.pop());
        }
    }

    public int top() {
        return stack.peek();
    }

    /* getMin() is O(1), but extractMin() is O(logn) */
    public int getMin() {
        return heap.peek();
    }

}
