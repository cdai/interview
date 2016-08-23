package fundamentals.stack.lc225_implementstackusingqueues;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement the following operations of a stack using queues.
 * You must use only standard operations of a queue --
 * which means only push to back, peek/pop from front, size, and is empty operations are valid.
 *
 * My 1AC
 */
public class MyStack_ON {

    public static void main(String[] args) {
        MyStack_ON stack = new MyStack_ON();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.pop();
        System.out.println(stack.top());
        System.out.println(stack.empty());
        stack.pop();
        System.out.println(stack.top());
    }

    private Queue<Integer> queue = new LinkedList<>();

    // Push element x onto stack.
    public void push(int x) {
        queue.offer(x);
    }

    // Removes the element on top of the stack.
    public void pop() {
        if (empty()) {
            return;
        }

        Queue<Integer> tmp = new LinkedList<>();
        int size = queue.size();
        while (size-- > 1) {
            tmp.offer(queue.poll());
        }
        queue = tmp;
    }

    // Get the top element.
    public int top() {
        Queue<Integer> tmp = new LinkedList<>();
        int size = queue.size();
        while (size-- > 1) {
            tmp.offer(queue.poll());
        }
        int top = queue.poll();
        tmp.offer(top);
        queue = tmp;
        return top;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return queue.isEmpty();
    }

}
