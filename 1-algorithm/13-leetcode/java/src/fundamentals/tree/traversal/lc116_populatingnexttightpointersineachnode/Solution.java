package fundamentals.tree.traversal.lc116_populatingnexttightpointersineachnode;

import fundamentals.tree.TreeLinkNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 * Note: You may only use constant extra space. You may assume that it is a perfect binary tree
 * (ie, all leaves are at the same level, and every parent has two children).
 */
public class Solution {

    public static void main(String[] args) {
        TreeLinkNode root = new TreeLinkNode(1);
        root.left = new TreeLinkNode(2);
        root.right = new TreeLinkNode(3);
        root.left.left = new TreeLinkNode(4);
        root.left.right = new TreeLinkNode(5);
        root.right.left = new TreeLinkNode(6);
        root.right.right = new TreeLinkNode(7);

        new Solution().connect(root);

        System.out.println(root.val + " next: " + root.next);
        System.out.println(root.left.val + " next: " + root.left.next);
        System.out.println(root.right.val + " next: " + root.right.next);
        System.out.println(root.left.left.val + " next: " + root.left.left.next);
        System.out.println(root.left.right.val + " next: " + root.left.right.next);
        System.out.println(root.right.left.val + " next: " + root.right.left.next);
        System.out.println(root.right.right.val + " next: " + root.right.right.next);
    }

    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeLinkNode last = null;
            int sizeOfLevel = queue.size();
            while (sizeOfLevel-- > 0) {
                TreeLinkNode node = queue.poll();
                // Put children
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                // Link to sibling
                if (last == null) {
                    last = node;
                } else {
                    last.next = node;
                    last = node;
                }
            }
        }
    }

}
