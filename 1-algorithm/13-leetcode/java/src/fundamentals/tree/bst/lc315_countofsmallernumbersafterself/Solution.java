package fundamentals.tree.bst.lc315_countofsmallernumbersafterself;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 */
public class Solution {

    public List<Integer> countSmaller(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();

        BSTNode root = new BSTNode(nums[nums.length - 1]);
        Integer[] counter = new Integer[nums.length];
        counter[nums.length - 1] = 0;
        for (int i = nums.length - 2; i >= 0; i--)
            counter[i] = insert(root, nums[i]);
        return Arrays.asList(counter);
    }

    // It seems easy but not at all indeed.
    // 1) What does smaller mean? # of all smaller one? or just # of left children of cur node?
    // 2) How to handle duplicates?
    // 3) a little messy since Java doesn't have pointer. We have to check if child is null ahead...
    private int insert(BSTNode root, int newval) {
        if (newval < root.val) {
            root.leftsum++;
            if (root.left == null) {
                root.left = new BSTNode(newval);
                return 0;
            }
            return insert(root.left, newval);
        } else if (root.val < newval) {
            int smaller = root.leftsum + root.dup;
            if (root.right == null) {
                root.right = new BSTNode(newval);
                return smaller;
            }
            return insert(root.right, newval) + smaller;
        } else {
            root.dup++;
            return root.leftsum;
        }
    }

    private static class BSTNode {
        BSTNode left, right;
        int leftsum, dup = 1, val;
        BSTNode(int val) { this.val = val; }
    }

    // My 2AC: correct idea, but TLE since headSet() is too slow, we only the size
    // Treat duplicate as greater (existing a is smaller than new b) to avoid being counted
    public List<Integer> countSmaller2(int[] nums) {
        Integer[] counter = new Integer[nums.length];
        TreeSet<Integer> tree = new TreeSet<>(
                (a, b) -> (Integer.compare(a, b) == 0) ? -1 : Integer.compare(a, b));
        for (int i = nums.length - 1; i >= 0; i--) {
            tree.add(nums[i]);
            counter[i] = tree.headSet(nums[i]).size(); // Here is the key!!!
        }
        return Arrays.asList(counter);
    }

}
