package advanced.datastructure.random.lc381_insertdeletegetrandomo1duplicatesallowed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Design a data structure that supports all following operations in average O(1) time.
 * Note: Duplicate elements are allowed.
 */
public class RandomizedCollection {
    private Random rand = new Random();
    private List<Integer> list = new ArrayList<>();
    private Map<Integer,Set<Integer>> bag = new HashMap<>();

    /** Initialize your data structure here. */
    public RandomizedCollection() {}

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean isNew = false;
        if (!bag.containsKey(val)) {
            bag.put(val, new HashSet<>());
            isNew = true;
        }
        bag.get(val).add(list.size());
        list.add(val);
        return isNew;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (bag.containsKey(val)) {
            int idx = bag.get(val).iterator().next();
            bag.get(val).remove(idx);
            // Copy last one to deleted position
            if (idx < list.size() - 1) {
                int lastval = list.get(list.size() - 1);
                bag.get(lastval).remove(list.size() - 1);
                bag.get(lastval).add(idx);
                list.set(idx, lastval);
            }
            // Delete last one
            list.remove(list.size() - 1);
            if (bag.get(val).isEmpty()) {
                bag.remove(val);
            }
            return true;
        }
        return false;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
