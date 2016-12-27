package advanced.design.lc251_flatten2dvector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Implement an iterator to flatten a 2d vector.
 * For example, Given 2d vector =
 * [
 *  [1,2],
 *  [3],
 *  [4,5,6]
 * ]
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].
 */
public class Vector2D_my implements Iterator<Integer> {

    public static void main(String[] args) {
        Vector2D_my it = new Vector2D_my(
            Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3),
                Arrays.asList(4, 5, 6)
            )
        );
        while (it.hasNext())
            System.out.println(it.next());
    }

    //private List<List<Integer>> vector;
    private List<Iterator<Integer>> its = new ArrayList<>();

    private int cur = 0;

    public Vector2D_my(List<List<Integer>> vector) {
        //this.vector = vector;
        for (List<Integer> list : vector)
            if (!list.isEmpty())
                its.add(list.iterator());
    }

    @Override
    public boolean hasNext() {
        // Not last iterator or is last but has next element
        return cur < its.size() - 1 || its.get(cur).hasNext();
    }

    @Override
    public Integer next() {
        if (!its.get(cur).hasNext())
            cur++;
        return its.get(cur).next();
    }
}
