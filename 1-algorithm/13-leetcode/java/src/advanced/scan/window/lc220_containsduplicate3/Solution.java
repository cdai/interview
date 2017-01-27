package advanced.scan.window.lc220_containsduplicate3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Given an array of integers, find out whether there are two distinct indices i and j in the array
 * such that the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
 */
public class Solution {

    public static void main(String[] args) {
        //System.out.println(new Solution().containsNearbyAlmostDuplicate(new int[]{1, 3, 6, 2}, 1, 2));
        //System.out.println(new Solution().containsNearbyAlmostDuplicate(new int[]{9,3,15,31,25,15}, 2, 5));
        //System.out.println(new Solution().containsNearbyAlmostDuplicate(new int[]{-1,-1}, 1, -1));
        System.out.println(new Solution().containsNearbyAlmostDuplicate(new int[]{0, Integer.MAX_VALUE}, 1, Integer.MAX_VALUE));
    }

    // O(N) Bucket sort solution
    // Almost duplicates can only exist in bucket or +/-1.
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums.length == 0 || k <= 0 || t < 0) return false;
        Map<Long, Long> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long buc = getBucket(nums[i], t);
            if (seen.containsKey(buc)) return true;
            if (seen.containsKey(buc + 1) && seen.get(buc + 1) - nums[i] <= t) return true;
            if (seen.containsKey(buc - 1) && nums[i] - seen.get(buc - 1) <= t) return true;
            seen.put(buc, (long) nums[i]);
            if (seen.size() > k) seen.remove(getBucket(nums[i - k], t));
        }
        return false;
    }

    // Note in Java -3 / 5 = 0 not -1, so we force num to be positive
    // We get bucket no.: [0,k], [k+1,2k+1]...
    private long getBucket(int num, int t) {
        return ((long) num - Integer.MIN_VALUE) / (t + 1L);
    }

    // O(NlogK) BST (RBTree) solution.
    public boolean containsNearbyAlmostDuplicate3(int[] nums, int k, int t) {
        if (nums.length == 0 || k <= 0 || t < 0) return false;
        TreeSet<Long> seen = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long pre = seen.floor((long) nums[i]);  // greatest number <= nums[i]
            Long suc = seen.ceiling((long) nums[i]);// least number >= nums[i]
            if ((pre != null && nums[i] - pre <= t) ||
                    (suc != null && suc - nums[i] <= t)) return true;
            seen.add((long) nums[i]);
            if (seen.size() > k) seen.remove((long) nums[i - k]);
        }
        return false;
    }

    // My 2AC: O(NlogK) time and O(logK) space.
    // Compare nums[i] with previous K elements in Set ahead, so the duplicate case is handled implicitly
    public boolean containsNearbyAlmostDuplicate21(int[] nums, int k, int t) {
        if (nums.length == 0 || k <= 0 || t < 0) return false;
        TreeSet<Long> tree = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long ceil = tree.ceiling((long) nums[i] - t);   // least num that >= num-t
            Long floor = tree.floor((long) nums[i] + t);    // greatest num that <= num+t
            if ((floor != null && floor >= nums[i]) || (ceil != null && ceil <= nums[i]))
                return true;

            if (tree.add((long) nums[i]) && i >= k)         // tree has K+1, remove one to keep K
                tree.remove((long) nums[i - k]);
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate_subset(int[] nums, int k, int t) {
        if (nums.length == 0 || k <= 0 || t < 0) return false;  // error1: t < 0
        TreeSet<Integer> tree = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (tree.size() >= k + 1)                           // error2: at most k+1 nums
                tree.remove(nums[i - k - 1]);
            if (!tree.add(nums[i])) // Find duplicate in +/-k range
                return true;
            // error4: must use boolean API, since MAX+1 is impossible for exclusive API
            int low = (nums[i] > Integer.MIN_VALUE + t) ? nums[i] - t : Integer.MIN_VALUE;
            int high = (nums[i] < Integer.MAX_VALUE - t) ? nums[i] + t : Integer.MAX_VALUE;
            if (tree.subSet(low, true, high, true).size() > 1)  // error3: include itself
                return true;
        }
        return false;
    }

    // My 1AC
    // Totally wrong: [1,3,6,2] k=1, t=2
    // Arrays in Java don't provide hashCode() and equals(Object) methods, so they aren't appropriate as map keys.
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
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
