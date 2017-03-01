package buildingblock.table.hashing.lc170_twosum3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a TwoSum class.
 * It should support the following operations: add and find.
 */
public class TwoSum {

    @Test
    void test() {
        add(1);
        Assertions.assertFalse(find(4));
        add(3);
        add(5);
        Assertions.assertTrue(find(4));
        Assertions.assertFalse(find(7));
        Assertions.assertFalse(find(10));
        add(5);
        Assertions.assertTrue(find(10));
    }

    private Map<Integer, Integer> map = new HashMap<>();

    public void add(int num) {
        map.put(num, map.getOrDefault(num, 0) + 1);
    }

    // O(N) time
    public boolean find(int sum) {
        for (int num : map.keySet()) {
            if (map.containsKey(sum - num)) {
                if (sum != num + num || map.get(num) > 1) {
                    return true;
                }
            }
        }
        return false;
    }

}
