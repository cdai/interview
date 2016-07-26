package fundamentals.stack.lc155_minstack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
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
public class MinStack {

    private Stack<Integer> stack;

    private List<Integer> minVals;

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.top());
        System.out.println(stack.getMin());
    }

    /** initialize your data structure here. */
    public MinStack() {
        this.stack = new Stack<>();
        this.minVals = new ArrayList<>();
    }

    public void push(int x) {
        stack.push(x);
        minVals.add(Math.min(getMin(), x));
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
            minVals.remove(minVals.size() - 1);
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        if (minVals.isEmpty()) {            // error: cause add() error
            return Integer.MAX_VALUE;
        }
        return minVals.get(minVals.size() - 1);
    }

}
