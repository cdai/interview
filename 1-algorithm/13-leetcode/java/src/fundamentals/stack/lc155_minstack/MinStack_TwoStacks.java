package fundamentals.stack.lc155_minstack;

import java.util.Stack;

/**
 */
public class MinStack_TwoStacks {

    private Stack<Integer> vals = new Stack<>();
    private Stack<Integer> mins = new Stack<>();

    public MinStack_TwoStacks() {
        mins.push(Integer.MAX_VALUE); // Sentinel
    }

    public void push(int x) {
        vals.push(x);
        mins.push(Math.min(mins.peek(), x));
    }

    public void pop() {
        if (!vals.isEmpty()) {
            vals.pop();
            mins.pop();
        }
    }

    public int top() {
        if (!vals.isEmpty()) {
            return vals.peek();
        }
        return 0;
    }

    public int getMin() {
        if (!vals.isEmpty()) {
            return mins.peek();
        }
        return 0;
    }

}
