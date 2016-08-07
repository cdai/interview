package advanced.combinatorial.combination.divideandconquer.lc095_uniquebinarysearchtrees2;

import fundamentals.tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.
 */
public class Solution {

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return doGenerate(1, n);
    }

    // Generate n nodes with numbers from [num,num+1,...,num+n]
    private List<TreeNode> doGenerate(int start, int end) {
        if (start > end) {
            return Arrays.asList(new TreeNode[] {null});
        }
        if (start == end) {
            return Arrays.asList(new TreeNode(start));
        }

        List<TreeNode> result = new ArrayList<>();

        // Pick i as root from [num,num+1,...,num+n]
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftSubtree = doGenerate(start, i - 1);
            List<TreeNode> rightSubtree = doGenerate(i + 1, end);

            // Left subtrees combine with right subtrees
            for (TreeNode left : leftSubtree) {
                for (TreeNode right : rightSubtree) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        return result;
    }

    // tree[i-1] cannot be used to build tree[i], since duplicate values
    // n = 3 -> [[1,null,1,null,1],[1,null,2,1],[2,1,1],[3,1,null,null,1],[3,2,null,1]]
    public List<TreeNode> generateTrees2(int n) {
        List[] trees = new List[n + 2];
        trees[0] = trees[n + 1] = Arrays.asList(new TreeNode[] {null});

        for (int i = 1; i <= n; i++) {
            trees[i] = new ArrayList<TreeNode>();

            // Pick j as root, generate trees for i nodes
            for (int j = 1; j <= i; j++) {

                // Left trees combine with right trees
                for (int k = 0; k < trees[j - 1].size(); k++) {
                    for (int m = 0; m < trees[i - j].size(); m++) {
                        TreeNode root = new TreeNode(j);
                        root.left = (TreeNode) trees[j - 1].get(k);
                        root.right = (TreeNode) trees[i - j].get(m);
                        trees[i].add(root);
                    }
                }
            }
        }
        return trees[n];
    }

}
