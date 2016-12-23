package fundamentals.stack.lc155_minstack;

import java.util.Stack;

/**
 * Store the gap and try to restore it upon pop()
 */
public class MinStack {

    private Stack<Integer> stack;

    private int min;

    public MinStack() {
        this.stack = new Stack<>();
        this.min = Integer.MAX_VALUE;
    }

    public void push(int num) {
        if (stack.isEmpty()) {
            stack.push(0);
            min = num;
        } else {
            // Case-1: num is smallest => stack.push(neg-gap), (new-)min = num
            // Case-2: num is NOT => stack.push(pos-gap), (new-)min = min
            // neg/pos-gap = num - (old-)min
            stack.push(num - min);
            min = Math.min(min, num);
        }
    }

    public int pop() {
        int gap = stack.pop();
        if (gap > 0)
            return min + gap;

        int num = min;
        min -= gap;
        return num;
    }

    public int min() {
        return min;
    }
}
