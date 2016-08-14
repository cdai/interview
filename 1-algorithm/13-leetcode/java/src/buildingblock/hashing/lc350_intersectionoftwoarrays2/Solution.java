package buildingblock.hashing.lc350_intersectionoftwoarrays2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given two arrays, write a function to compute their intersection.
 * Example: Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 * Note: Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 * Follow up:
 *  What if the given array is already sorted? How would you optimize your algorithm?
 *  What if nums1's size is small compared to nums2's size? Which algorithm is better?
 *  What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */
public class Solution {

    // My 2nd
    // Solution1: extremely fast, maybe due to constant space used
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> list = new ArrayList<>();
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    // Solution2: faster
    public int[] intersect2(int[] nums1, int[] nums2) {
        Map<Integer,Integer> map2 = toMap(nums2);

        // Compute retailAll() manually
        List<Integer> list = new ArrayList<>();
        for (int n : nums1) {
            Integer count = map2.get(n);
            if (count == null) {
                continue;
            }
            if (count == 1) {
                map2.remove(n);
            } else {
                map2.put(n, count - 1);
            }
            list.add(n);
        }

        // Conver to primitive array
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }


    // Solution3: Correct but a little slow, since one Map is enough!
    public int[] intersect3(int[] nums1, int[] nums2) {
        Map<Integer,Integer> map1 = toMap(nums1);
        Map<Integer,Integer> map2 = toMap(nums2);
        //map1.retainAll(map2); // unaccessible in Map interface
        return toArray(map1, map2);
    }

    private Map<Integer,Integer> toMap(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer cnt = map.get(nums[i]);
            if (cnt == null) {
                cnt = 0;
            }
            map.put(nums[i], cnt + 1);
        }
        return map;
    }

    private int[] toArray(Map<Integer,Integer> map1, Map<Integer,Integer> map2) {
        // Compute retailAll() manually
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer,Integer> e : map1.entrySet()) {
            if (!map2.containsKey(e.getKey())) {
                continue;
            }
            //int count = e.getValue() + map2.get(e.getKey());
            int count = Math.min(e.getValue(), map2.get(e.getKey())); // "as it shows in both arrays" doesn't mean total
            while (count-- > 0) {
                list.add(e.getKey());
            }
        }

        // Conver to primitive array
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

}
