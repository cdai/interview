package advanced.design.lc281_zigzagiterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 */
public class ZigzagIterator implements Iterator<Integer> {

    public static void main(String[] args) {
        ZigzagIterator_my it = new ZigzagIterator_my(
                Arrays.asList(
                        Arrays.asList(1, 2),
                        Arrays.asList(3, 4, 5, 6),
                        Arrays.asList(8, 9, 10)
                )
        );
        while (it.hasNext())
            System.out.println(it.next());
    }

    private Queue<Iterator<Integer>> q = new LinkedList<>();

    public ZigzagIterator(List<List<Integer>> vectors) {
        for (List<Integer> vec : vectors)
            if (!vec.isEmpty())
                q.offer(vec.iterator());
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();
    }

    @Override
    public Integer next() {
        Iterator<Integer> it = q.poll();
        int val = it.next();
        if (it.hasNext()) q.offer(it);
        return val;
    }

}
