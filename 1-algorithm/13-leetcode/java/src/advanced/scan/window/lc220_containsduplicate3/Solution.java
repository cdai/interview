package advanced.scan.window.lc220_containsduplicate3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().containsNearbyAlmostDuplicate(new int[]{1, 3, 6, 2}, 1, 2));
    }

    // Totally wrong: [1,3,6,2] k=1, t=2
    // Arrays in Java don't provide hashCode() and equals(Object) methods, so they aren't appropriate as map keys.
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        Set<List<Integer>> sorted = new TreeSet<>(new Comparator<List<Integer>>() {
            public int compare(List<Integer> list1, List<Integer> list2) {
                int ret1 = list1.get(0).compareTo(list2.get(0));
                if (ret1 != 0) {
                    return ret1;
                }
                return list1.get(1).compareTo(list2.get(1));
            }
        });

        // O(NlogN)
        for (int i = 0; i < nums.length; i++) {
            sorted.add(Arrays.asList(nums[i], i));
        }

        List<Integer> last = null;
        for (List<Integer> pair : sorted) {
            System.out.println(pair);
            if (last != null
                    && pair.get(0) - last.get(0) <= t
                    && Math.abs(pair.get(1) - last.get(1)) <= k) {
                return true;
            }
            last = pair;
        }
        return false;
    }

    // TreeMap cannot save exact same elements
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        Map<Integer,Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        Map.Entry<Integer,Integer> last = null;
        for (Map.Entry<Integer,Integer> e : map.entrySet()) {
            if (last != null
                    && e.getKey() - last.getKey() <= t
                    && Math.abs(e.getValue() - last.getValue()) <= k) {
                return true;
            }
            last = e;
        }
        return false;
    }

}
