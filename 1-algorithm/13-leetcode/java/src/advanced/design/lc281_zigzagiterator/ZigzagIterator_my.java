package advanced.design.lc281_zigzagiterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 */
public class ZigzagIterator_my implements Iterator<Integer> {

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

    private List<Iterator<Integer>> its = new LinkedList<>();

    private int cur = 0;

    public ZigzagIterator_my(List<List<Integer>> vectors) {
        for (List<Integer> vec : vectors)
            if (!vec.isEmpty())
                its.add(vec.iterator());
    }

    @Override
    public boolean hasNext() {
        while (!its.isEmpty() && !its.get(cur).hasNext()) {
            its.remove(cur);
            if (cur >= its.size())
                cur = 0;
        }
        return !its.isEmpty();
    }

    @Override
    public Integer next() {
        int val = its.get(cur).next();
        cur = (cur + 1) % its.size();
        return val;
    }

}
