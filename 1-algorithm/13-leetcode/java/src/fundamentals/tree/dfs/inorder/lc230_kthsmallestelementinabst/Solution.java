package fundamentals.tree.dfs.inorder.lc230_kthsmallestelementinabst;

import fundamentals.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * Note: You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * Follow up: What if the BST is modified (insert/delete operations) often and
 * you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
 * Hint: Try to utilize the property of a BST. What if you could modify the BST node's structure?
 *  The optimal runtime complexity is O(height of BST).
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(9);
        root.left = new TreeNode(3);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(15);
        System.out.println(new Solution().kthSmallest(root, 4));

        System.out.println(new Solution().secondLargest(root));
    }

    // O(logN) time. Second largest node is largest node's parent or rightmost of its left subtree
    public int secondLargest(TreeNode root) {
        TreeNode p = root, prev = null;
        for (; p.right != null; p = p.right) prev = p;

        if (prev == null) return 0; // Only one node in tree
        if (p.left == null) return prev.val;

        p = p.left;
        for (; p.right != null; p = p.right);
        return p.val;
    }

    // My 3AC
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode p = root;
        while (!s.isEmpty() || p != null) {
            if (p != null) {
                s.push(p);
                p = p.left;
            } else {
                p = s.pop();
                if (k-- == 1) return p.val;     // error: kth starts from 1
                p = p.right;
            }
        }
        return 0;
    }

    // 2nd
    public int kthSmallest2(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else { // p=null but stack is NOT empty
                p = stack.pop();

                // In-order visit
                if (--k == 0) {     // kth starts from 1
                    return p.val;
                }

                p = p.right;
            }
        }
        return 0;
    }

    public int kthSmallest22(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        for (TreeNode node = root; node != null; node = node.left) {
            stack.push(node);
        }
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (--k == 0) {
                return cur.val;
            }
            for (TreeNode node = cur.right; node != null; node = node.left) {
                stack.push(node);
            }
        }
        return 0;
    }

    public int kthSmallest3(TreeNode root, int k) {
        List<Integer> vals = new ArrayList<>();
        findKthSmallest(root, k, vals);
        return vals.size() < k ? 0 : vals.get(k - 1);
    }

    public void findKthSmallest(TreeNode root, int k, List<Integer> vals) { // Actually I don't need all k vals, but it's hard to decrement k as input argument...
        if (root == null) {
            return;
        }

        findKthSmallest(root.left, k, vals);

        // In-order visit
        vals.add(root.val);
        if (vals.size() == k) {
            return;
        }

        findKthSmallest(root.right, k, vals);
    }

}
