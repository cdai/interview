package buildingblock.divideandconquer.lc108_convertsortedarraytobinarysearchtree;

import fundamentals.tree.TreeNode;

/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class Solution {

    // T(N) = 2T(N/2) = 2^2T(N/2^2) = ... 2^iT(N/2^i)
    // Base case: n/2^i=1 -> i = logN
    // T(N) = 2^logNT(1) = O(N) time
    // [], [1], [1,2], [1,2,3], [1,2,3,4]
    public TreeNode sortedArrayToBST(int[] A) {
        return build(A, 0, A.length - 1);
    }

    private TreeNode build(int[] A, int l, int r) {
        if (l > r) return null;
        int m = l + (r - l) / 2;
        TreeNode root = new TreeNode(A[m]);
        root.left = build(A, l, m - 1);
        root.right = build(A, m + 1, r);
        return root;
    }

    // My 2nd: O(N) time, O(logN) space
    public TreeNode sortedArrayToBST2(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        return doConvertToBST(nums, 0, nums.length - 1);
    }

    private TreeNode doConvertToBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = doConvertToBST(nums, start, mid - 1);
        root.right = doConvertToBST(nums, mid + 1, end);
        return root;
    }

    // My 1st
    public TreeNode sortedArrayToBST1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return doSortedArrayToBST(nums, 0, nums.length-1);
    }

    private TreeNode doSortedArrayToBST(int[] nums, int low, int high) {
        if (low == high) {          // Could speed up, but not a must
            return new TreeNode(nums[low]);
        } else if (low > high)  {
            return null;
        }

        int mid = (low + high) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = doSortedArrayToBST(nums, low, mid-1);
        root.right = doSortedArrayToBST(nums, mid+1, high);
        return root;
    }

}
