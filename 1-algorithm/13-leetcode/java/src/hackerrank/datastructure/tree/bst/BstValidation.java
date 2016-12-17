package hackerrank.datastructure.tree.bst;

/**
 * Validate if it's valid BST
 */
public class BstValidation {

    public boolean checkBST(Node root) {
        return doCheckBST(root, 0, Integer.MAX_VALUE);
    }

    private boolean doCheckBST(Node root, int low, int high) {
        if (root == null) return true;
        return (low < root.data && root.data < high) &&
                doCheckBST(root.left, low, root.data) &&
                doCheckBST(root.right, root.data, high);
    }

    // Wrong!
    boolean checkBST2(Node root) {
        if (root == null) return true;
        return (root.left == null || root.left.data < root.data) &&
                (root.right == null || root.data < root.right.data) &&
                checkBST(root.left) && checkBST(root.right);
    }

}
