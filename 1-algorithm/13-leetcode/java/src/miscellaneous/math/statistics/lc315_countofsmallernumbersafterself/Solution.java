package miscellaneous.math.statistics.lc315_countofsmallernumbersafterself;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 */
public class Solution {

    // Order-statistic tree with size field to calculate rank of value.
    public List<Integer> countSmaller(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();
        int n = nums.length;
        Integer[] ret = new Integer[n];
        ret[n - 1] = 0;

        Node root = new Node(nums[n - 1]);
        for (int i = n - 2; i >= 0; i--) {
            // Find insert position meanwhile computing rank
            int rank = 0;
            Node par = root;
            for (Node cur = root; cur != null; ) {
                par = cur;
                par.size++;
                if (nums[i] < cur.val) { // left subtree contains only smaller num, it's safe to add to rank!
                    cur = cur.left;
                } else {
                    if (cur.left != null) rank += cur.left.size; // size of left subtree
                    if (cur.val < nums[i]) rank++; // check duplicate to decide if count parent as smaller
                    cur = cur.right;
                }
            }
            // Insert new node and save rank
            if (nums[i] < par.val) par.left = new Node(nums[i]);
            else par.right = new Node(nums[i]);
            ret[i] = rank;
        }
        return Arrays.asList(ret);
    }

    class Node {
        int val, size = 1; // itself
        Node left, right;
        Node(int val) {
            this.val = val;
        }
    }

    public List<Integer> countSmaller2(int[] nums) {
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
    public List<Integer> countSmaller21(int[] nums) {
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
