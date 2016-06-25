package fundamentals.tree.validate.lc098_validatebinarysearchtree;

import fundamentals.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * Assume a BST is defined as follows:
 *  The left subtree of a node contains only nodes with keys less than the node's key.
 *  The right subtree of a node contains only nodes with keys greater than the node's key.
 *  Both the left and right subtrees must also be binary search trees.
 */
public class Solution {

    public boolean isValidBST(TreeNode root) {
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
