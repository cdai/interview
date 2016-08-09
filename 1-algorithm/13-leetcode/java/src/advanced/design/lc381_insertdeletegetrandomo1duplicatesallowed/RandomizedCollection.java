package advanced.design.lc381_insertdeletegetrandomo1duplicatesallowed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Design a data structure that supports all following operations in average O(1) time.
 * Note: Duplicate elements are allowed.
 */
public class RandomizedCollection {

    private Map<Integer,Integer> data;

    private List<Integer> index;

    private java.util.Random rand;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        this.data = new HashMap<>();
        this.index = new ArrayList<>();
        this.rand = new java.util.Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        Integer cnt = data.get(val);
        if (cnt == null) {
            cnt = 0;
        }
        data.put(val, cnt + 1);
        index.add(val);
        return cnt == 0;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        Integer cnt = data.get(val);
        if (cnt == null) {
            return false;
        }
        if (cnt == 1) {
            data.remove(val);
        } else {
            data.put(val, cnt - 1);
        }
        index.remove((Integer) val);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return index.get(rand.nextInt(index.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
