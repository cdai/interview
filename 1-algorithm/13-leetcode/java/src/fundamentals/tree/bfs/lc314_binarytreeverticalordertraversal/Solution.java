package fundamentals.tree.bfs.lc314_binarytreeverticalordertraversal;

import fundamentals.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Given a binary tree, return the vertical order traversal of its nodes’ values.
 * (ie, from top to bottom, column by column).
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);
        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(11);
        root.right.left.left = new TreeNode(12);
        root.right.left.right = new TreeNode(13);
        root.right.right.left = new TreeNode(14);
        root.right.right.right = new TreeNode(15);
        System.out.println(new Solution().verticalOrder(root));
    }

    // O(N) time.
    public List<List<Integer>> verticalOrder(TreeNode root) {
        Map<Integer,List<Integer>> idxNode = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        if (root != null) q.offer(new Pair(0, root));
        while (!q.isEmpty()) {
            Pair p = q.poll();
            if (!idxNode.containsKey(p.idx))
                idxNode.put(p.idx, new ArrayList<>());
            idxNode.get(p.idx).add(p.node.val);

            if (p.node.left != null) q.offer(new Pair(p.idx - 1, p.node.left));
            if (p.node.right != null) q.offer(new Pair(p.idx + 1, p.node.right));
        }
        return new ArrayList<>(idxNode.values());
    }

    class Pair {
        int idx;
        TreeNode node;
        public Pair(int idx, TreeNode node) {
            this.idx = idx;
            this.node = node;
        }
    }

}
