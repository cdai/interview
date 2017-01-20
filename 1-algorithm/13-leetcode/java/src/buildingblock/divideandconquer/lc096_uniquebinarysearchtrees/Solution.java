package buildingblock.divideandconquer.lc096_uniquebinarysearchtrees;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 * For example, Given n = 3, there are a total of 5 unique BST's.
 */
public class Solution {

    // #Sub-problem=n, dependencies on sub-problem=1+2+...n => O(N^2)
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int l = 0, r = i - 1; l < i; l++, r--)
                dp[i] += dp[l] * dp[r];
        }
        return dp[n];
    }

    // My 2nd: O(N^2) time, O(N) space
    public int numTrees2(int n) {
        if (n < 0) {
            return 1;
        }

        int[] nums = new int[n + 1];
        nums[0] = 1;

        for (int i = 1; i <= n; i++) {
            // Pick j from [1,i] as root
            for (int j = 1; j <= i; j++) {
                // #left subtree * #right subtree
                nums[i] += (nums[j - 1] * nums[i - j]);
            }
        }
        return nums[n];
    }

    // My 1st
    public int numTrees1(int n) {
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
