package miscellaneous.fancy.lc380_insertdeletegetrandomo1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 */
public class RandomizedSet {

    public static void main(String[] args) {
        RandomizedSet set = new RandomizedSet();
        set.insert(3);
        set.insert(3);
        System.out.println("random: " + set.getRandom());
        System.out.println("random: " + set.getRandom());
        set.insert(1);
        set.remove(3);
        System.out.println("random: " + set.getRandom());
        System.out.println("random: " + set.getRandom());
        set.insert(0);
        set.remove(0);
    }

    private Set<Integer> set;

    private List<Integer> index;

    private java.util.Random rand;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        this.set = new HashSet<>();
        this.index = new ArrayList<>();
        this.rand = new java.util.Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (!set.contains(val)) { // error1: set don't save duplicates, but it's possible to be called with
            index.add(val);
        }
        return set.add(val);
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (set.contains(val)) {
            index.remove((Integer) val); // remove(int)/remove(Object)
        }
        return set.remove(val);
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return index.get(rand.nextInt(index.size()));
    }

}
