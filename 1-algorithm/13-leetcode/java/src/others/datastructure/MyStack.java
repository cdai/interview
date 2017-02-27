package others.datastructure;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 */
public class MyStack<T> extends ArrayList<T> {

    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        Assertions.assertEquals(2, stack.peek().intValue());
        stack.push(3);
        Assertions.assertEquals(3, stack.pop().intValue());
        Assertions.assertEquals(2, stack.pop().intValue());
        Assertions.assertEquals(1, stack.pop().intValue());
    }

    public void push(T num) {
        add(num);
    }

    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        return remove(size() - 1);
    }

    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return get(size() - 1);
    }
}
