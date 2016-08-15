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

    // Recursive version without Set
    // In fact, this is exactly the same as iterative one
    public List<List<Integer>> permute(int[] nums) {
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
    // Don't use iterator, which can avoid exception and tmp list
    // O(N!) Beat 24%
    public List<List<Integer>> permute2(int[] nums) {
        Queue<List<Integer>> result = new LinkedList<>();
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
        return (List<List<Integer>>) result;
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
