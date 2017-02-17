package advanced.datastructure.random.lc380_insertdeletegetrandomo1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 */
public class RandomizedSet {

    private Random rand = new Random();
    private List<Integer> list = new ArrayList<>();
    private Map<Integer,Integer> map = new HashMap<>();

    /** Initialize your data structure here. */
    public RandomizedSet() {}

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (!map.containsKey(val)) {
            map.put(val, list.size());
            list.add(val);
            return true;
        }
        return false;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (map.containsKey(val)) {
            int idx = map.remove(val);
            if (idx < list.size() - 1) { // move last element to deleted position
                int lastval = list.get(list.size() - 1);
                list.set(idx, lastval);
                map.put(lastval, idx);
            }
            list.remove(list.size() - 1);
            return true;
        }
        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }

}
