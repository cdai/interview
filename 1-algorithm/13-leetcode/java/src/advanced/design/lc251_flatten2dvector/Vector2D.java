package advanced.design.lc251_flatten2dvector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 */
public class Vector2D implements Iterator<Integer> {

    public static void main(String[] args) {
        Vector2D vec = new Vector2D(
                Arrays.asList(
                        Arrays.asList(1, 2),
                        Arrays.asList(3),
                        Arrays.asList(),
                        Arrays.asList(4, 5, 6)
                )
        );
        while (vec.hasNext())
            System.out.println(vec.next());

        vec = new Vector2D(new ArrayList<>());
        while (vec.hasNext())
            System.out.println(vec.next());
    }

    private Iterator<List<Integer>> i;

    private Iterator<Integer> j;

    public Vector2D(List<List<Integer>> vector) {
        this.i = vector.iterator();
    }

    // Not only check, but also move j to next available position
    @Override
    public boolean hasNext() {
        while ((j == null || !j.hasNext()) && i.hasNext())
            j = i.next().iterator();
        return j != null && j.hasNext();
    }

    @Override
    public Integer next() {
        hasNext();
        return j.next();
    }

}
