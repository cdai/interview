package others.algorithm;

import fundamentals.tree.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 */
public class TreeDiameter {

    @Test
    void testPassRoot() {
        //     1
        //   2   4
        //  3   5 6
        max = 0;
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        Assertions.assertEquals(5, diameter(root));
//        Assertions.assertEquals(5, diameter_bfs(root));
    }

    @Test
    void testSubtree() {
        //     1
        //   2   4
        //  3   5 6
        //     7  8
        //     9  10
        max = 0;
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
        root.right.right.left = new TreeNode(8);
        root.right.left.left.left = new TreeNode(9);
        root.right.right.left.left = new TreeNode(10);
        Assertions.assertEquals(7, diameter(root));
//        Assertions.assertEquals(7, diameter_bfs(root));
    }

    private int max = 0;

    public int diameter(TreeNode root) {
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        max = Math.max(max, left + right + 1);
        return Math.max(left, right) + 1;
    }

    // Works for undirected Graph (x is connected to any node) or Tree with parent pointer
    // "If we start BFS from any node x and find a node with the longest distance from x,
    // it must be an end point of the longest path. It can be proved using contradiction."
    public int diameter_bfs(TreeNode root) {
        // Find farthest node from root by first BFS
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        TreeNode last = null;
        while (!q.isEmpty()) {
            last = q.poll();
            if (last.left != null) q.offer(last.left);
            if (last.right != null) q.offer(last.right);
        }

        // Find longest path from that node by second BFS
        if (last == null) return 0;
        int max = 0;
        q.offer(last);
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                TreeNode p = q.poll();
                //if (p.left != null) q.offer(p.left);  Need parent pointer to go up
                //if (p.right != null) q.offer(p.right);
            }
            max++;
        }
        return max;
    }

}
