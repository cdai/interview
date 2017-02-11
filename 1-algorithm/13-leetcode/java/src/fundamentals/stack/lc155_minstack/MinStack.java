package fundamentals.stack.lc155_minstack;

import java.util.Stack;

/**
 */
public class MinStack {

    private Stack<Long> dif = new Stack<>();
    private long min;

    public void push(int num) {
        if (dif.isEmpty()) {
            dif.push(0L);
            min = num;
        } else {
            dif.push(num - min);
            min = Math.min(min, num);
        }
    }

    // 2 Cases:
    // dif < 0: given num==min, dif(num-lastmin) => last min
    // dif > 0: given lastmin, dif(num-lastmin) => num
    public int pop() {
        long num;
        if (dif.peek() < 0) {
            num = min;
            min = num - dif.pop();
        } else {
            num = min + dif.pop();
        }
        return (int) num;
    }

    public int min() {
        return (int) min;
    }

}
