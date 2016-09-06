package fundamentals.tree.bfs.lc116_populatingnextrightpointersineachnode;

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

        root.left.left.left = new TreeLinkNode(8);
        root.left.left.right = new TreeLinkNode(9);
        root.left.right.left = new TreeLinkNode(10);
        root.left.right.right = new TreeLinkNode(11);
        root.right.left.left = new TreeLinkNode(12);
        root.right.left.right = new TreeLinkNode(13);
        root.right.right.left = new TreeLinkNode(14);
        root.right.right.right = new TreeLinkNode(15);

        new Solution().connect(root);

        System.out.println(root.val + " next: " + root.next);
        System.out.println(root.left.val + " next: " + root.left.next);
        System.out.println(root.right.val + " next: " + root.right.next);
        System.out.println(root.left.left.val + " next: " + root.left.left.next);
        System.out.println(root.left.right.val + " next: " + root.left.right.next);
        System.out.println(root.right.left.val + " next: " + root.right.left.next);
        System.out.println(root.right.right.val + " next: " + root.right.right.next);
    }

    // O(N) time, O(1) space: key is to use "next" too!
    public void connect_bfs(TreeLinkNode root) {
        if (root == null) return;
        while (root.left != null) {
            TreeLinkNode first = root;              // error1: don't forget save first node, since we always move root
            while (root.next != null) {             // error2: if you stop here, don't forget the children of last node
                root.left.next = root.right;
                root.right.next = root.next.left;
                root = root.next;                   // error3: don't forget move pointer in inner of nested loop
            }
            root.left.next = root.right;
            root = first.left;
        }
    }

    // DFS: key is to use "next". but DFS needs O(h) extra space.
    public void connect(TreeLinkNode root) {
        if (root == null) return;

        // 1.Connect children for lower level use
        if (root.left != null)    // Assume perfect binary tree, but left is null for leaves
            root.left.next = root.right;

        // 2.Higher level must be handled already, so connect to sibling
        if (root.right != null && root.next != null)
            root.right.next = root.next.left;

        connect(root.left);
        connect(root.right);
    }

    // My 1st
    public void connect1(TreeLinkNode root) {
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
