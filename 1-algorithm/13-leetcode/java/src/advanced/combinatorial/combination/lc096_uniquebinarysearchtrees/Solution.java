package advanced.combinatorial.combination.lc096_uniquebinarysearchtrees;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 * For example, Given n = 3, there are a total of 5 unique BST's.
 */
public class Solution {

    public int numTrees(int n) {
        int[] nums = new int[n + 2];
        nums[0] = nums[n + 1] = 1;

        for (int i = 1; i <= n; i++) {
            // Pick j as root, num of BST = (num of left subtree) * (num of right subtree)
            // When j = 1 or n, there's only one side, it's ok since nums[0]=nums[n+1]=1
            for (int j = 1; j <= i; j++) {
                nums[i] += (nums[j - 1] * nums[i - j]);
            }
        }
        return nums[n];
    }

}
