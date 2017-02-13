package advanced.combinatorial.permutation.lc046_permutations;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Given a collection of distinct numbers, return all possible permutations.
 * For example, [1,2,3] have the following permutations:
 *  [
 *      [1,2,3],
 *      [1,3,2],
 *      [2,1,3],
 *      [2,3,1],
 *      [3,1,2],
 *      [3,2,1]
 *  ]
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().permute(new int[]{1, 2, 3}));
    }

    // 3AC
    // Use of LinkedHashSet:
    // "This class provides all of the optional Set operations, and permits null elements.
    // Like HashSet, it provides constant-time performance for the basic operations (add, contains and remove),
    // assuming the hash function disperses elements properly among the buckets.
    // Performance is likely to be just slightly below that of HashSet, due to the added expense of maintaining the linked list, with one exception:
    // Iteration over a LinkedHashSet requires time proportional to the size of the set, regardless of its capacity.
    // Iteration over a HashSet is likely to be more expensive, requiring time proportional to its capacity."
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        dfs(ret, new LinkedHashSet<>(), nums);
        return ret;
    }

    private void dfs(List<List<Integer>> ret, Set<Integer> path, int[] nums) {
        if (path.size() == nums.length) {
            ret.add(new ArrayList<>(path));
            return;
        }
        for (int num : nums) {
            if (path.add(num)) {
                dfs(ret, path, nums);
                path.remove(num);
            }
        }
    }

    public List<List<Integer>> permute3_iterative(int[] nums) {
        Queue<List<Integer>> ret = new LinkedList<>();
        ret.offer(new LinkedList<>());
        for (int num : nums) {
            for (int i = ret.size(); i > 0; i--) {
                List<Integer> perm = ret.poll();
                for (int j = 0; j <= perm.size(); j++) {
                    List<Integer> cpy = new LinkedList<>(perm);
                    cpy.add(j, num);
                    ret.offer(cpy);
                }
            }
        }
        return (List<List<Integer>>) ret;
    }

    // My 2nd: this is the real recursion. O(N!) time, O(N) space
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        doPermute(result, new ArrayList<>(), new boolean[nums.length], nums);
        return result;
    }

    private void doPermute(List<List<Integer>> result, List<Integer> path, boolean[] used, int[] nums) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            path.add(nums[i]);
            used[i] = true;
            doPermute(result, path, used, nums);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }

    // Recursive version without Set
    // In fact, this is exactly the same as iterative one
    // Honestly, I don't think...
    public List<List<Integer>> permute3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        doPermute(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void doPermute(List<List<Integer>> result,
                           List<Integer> path, int[] nums, int start) {
        if (path.size() == nums.length) {
            result.add(path);
            return;
        }

        // i is insert position, start is the index of num
        for (int i = 0; i <= path.size(); i++) {
            List<Integer> per = new ArrayList<>(path);
            per.add(i, nums[start]);
            doPermute(result, per, nums, start + 1);
        }
    }

    // Iterative version: inspired by leetcode discuss
    // Don't use iterator, which will arise exception and tmp list
    // O(N!) Beat 24%
    public List<List<Integer>> permute_iterative(int[] nums) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        result.add(new ArrayList<>());
        for (int num : nums) {
            int size = result.size();
            while (size-- > 0) {
                List<Integer> per = result.poll();
                for (int k = 0; k <= per.size(); k++) {
                    List<Integer> newPer = new ArrayList<>(per);
                    newPer.add(k, num);
                    result.add(newPer);
                }
            }
        }
        return result;
    }

    // My 1st: use Set to exclude picked candidates
    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> path = new LinkedHashSet<>(); // TreeSet is sorted by element order; LinkedHashSet retains insertion order.
        permute(result, path, nums, 0);
        return result;
    }

    private void permute(List<List<Integer>> result, Set<Integer> path, int[] nums, int k) {
        if (k == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (path.contains(nums[i])) {
                continue;
            }

            path.add(nums[i]);
            permute(result, path, nums, k+1);
            path.remove(nums[i]); // take care of this since we're using Set not array
        }
    }

}
