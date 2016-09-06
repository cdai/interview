package fundamentals.tree.dfs.inorder.lc098_validatebinarysearchtree;

import fundamentals.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * Assume a BST is defined as follows:
 *  The left subtree of a node contains only nodes with keys less than the node's key.
 *  The right subtree of a node contains only nodes with keys greater than the node's key.
 *  Both the left and right subtrees must also be binary search trees.
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        System.out.println(new Solution().isValidBST(root));
    }

    // Correct recursive version from leetcode discuss
    public boolean isValidBST(TreeNode root) {
        return isValidBST(Long.MIN_VALUE, root, Long.MAX_VALUE);
    }

    private boolean isValidBST(long low, TreeNode root, long high) {
        if (root == null) return true;
        return (low < root.val && root.val < high)
                && isValidBST(low, root.left, root.val)
                && isValidBST(root.val, root.right, high);
    }

    // My 2nd: use stack to traverse inorder iteratively.
    public boolean isValidBST_iterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode prev = null;                               // error2: TreeNode nor int set to INT_MIN, since eg.[-2147483648]

        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();

                // Inorder visit
                if (prev != null && prev.val >= cur.val) {  // error1: must >=, eg.[1,1] is invalid tree too
                    return false;
                }
                prev = cur;

                cur = cur.right;
            }
        }
        return true;
    }

    // This is wrong, you must use a range
    public boolean isValidBST_recursion2(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.left.val >= root.val) {
            return false;
        }
        if (root.right != null && root.right.val <= root.val) {
            return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }

    // My 1st: slow because the whole inorder result is generated
    public boolean isValidBST1(TreeNode root) {
        List<Integer> vals = new ArrayList<>();

        doValidate(vals, root);

        for (int i = 1; i < vals.size(); i++) {
            if (vals.get(i-1) >= vals.get(i)) { // error1: any equals also means wrong!
                return false;
            }
        }
        return true;
    }

    private void doValidate(List<Integer> vals, TreeNode node) {
        if (node == null) {
            return;
        }
        doValidate(vals, node.left);
        vals.add(node.val);
        doValidate(vals, node.right);
    }


    // Wrong! Only validate current node, left and right
    // e.g. [10,5,15,null,null,6,20]. 6 is wrong even though 6<15<20, because 6<10(root)
    public boolean isValidBST_wrongunderstanding(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean isValid = true;
        if (root.left != null) {
            isValid = isValid && (root.left.val < root.val);
        }
        if (root.right != null) {
            isValid = isValid && (root.val < root.right.val);
        }

        isValid = isValid && isValidBST(root.left);
        isValid = isValid && isValidBST(root.right);

        return isValid;
    }

}
