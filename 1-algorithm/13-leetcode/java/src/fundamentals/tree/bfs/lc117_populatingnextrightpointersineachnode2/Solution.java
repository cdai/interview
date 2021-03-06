package fundamentals.tree.bfs.lc117_populatingnextrightpointersineachnode2;

import fundamentals.tree.TreeLinkNode;

/**
 *
 */
public class Solution {

    public static void main(String[] args) {
        TreeLinkNode root = new TreeLinkNode(1);
        root.left = new TreeLinkNode(2);
        root.right = new TreeLinkNode(3);
        root.left.left = new TreeLinkNode(4);
//        root.left.right = new TreeLinkNode(5);
        root.right.left = new TreeLinkNode(6);
        root.right.right = new TreeLinkNode(7);

        new Solution().connect(root);

        System.out.println(root.val + " next: " + root.next);
        System.out.println(root.left.val + " next: " + root.left.next);
        System.out.println(root.right.val + " next: " + root.right.next);
        System.out.println(root.left.left.val + " next: " + root.left.left.next);
//        System.out.println(root.left.right.val + " next: " + root.left.right.next);
        System.out.println(root.right.left.val + " next: " + root.right.left.next);
        System.out.println(root.right.right.val + " next: " + root.right.right.next);
    }

    // My 2nd solution inspired from leetcode discuss.
    // Very elegant solution using dummy head!
    // 1) Don't need to check pre is not null (pre = dmy initially)
    // 2) Don't need to store first non-null child (dmy.next)
    public void connect(TreeLinkNode root) {
        TreeLinkNode dmy = new TreeLinkNode(0), par = root;
        while (par != null) {
            TreeLinkNode pre = dmy;
            for (; par != null; par = par.next) {
                if (par.left != null) pre = pre.next = par.left;
                if (par.right != null) pre = pre.next = par.right;
            }
            par = dmy.next;
            dmy.next = null;
        }
    }

    // Always do link in pair rather than alone...
    public void connect2(TreeLinkNode root) {
        TreeLinkNode cur = root;
        while (cur != null) {                       // cur is current node on high level
            TreeLinkNode head = null, prev = null;  // head/prev is first/previous node on low level
            for (; cur != null; cur = cur.next) {
                if (cur.left != null) {
                    if (prev != null) prev.next = cur.left;
                    else head = cur.left;
                    prev = cur.left;
                }
                if (cur.right != null) {
                    if (prev != null) prev.next = cur.right;
                    else head = cur.right;
                    prev = cur.right;
                }
            }
            cur = head;
            head = prev = null;
        }
    }

    public void connect1(TreeLinkNode root) {
        // Traverse each level
        TreeLinkNode next = root;
        while (next != null) {

            // Connect nodes on next level
            TreeLinkNode lastOfCurLevel = null, firstOfNextLevel = null;
            for (TreeLinkNode node = next; node != null; node = node.next) {
                if (node.left == null && node.right == null) {
                    continue;
                }

                // 1.Update link of last node of previous subtree
                if (lastOfCurLevel != null) {
                    lastOfCurLevel.next = (node.left != null) ? node.left : node.right;
                }
                lastOfCurLevel = (node.right != null) ? node.right : node.left;

                // 2.Now only link between left and right
                if (node.left != null && node.right != null) {
                    node.left.next = node.right;
                }

                // 3.Update next see if there is a node on next level
                if (firstOfNextLevel == null) {
                    firstOfNextLevel = (node.left != null) ? node.left : node.right;
                }
            }
            next = firstOfNextLevel;
        }
    }

}
