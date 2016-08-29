package advanced.datastructure.graph.lc310_minimumheighttrees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().findMinHeightTrees(
                4, new int[][]{{0, 1}, {1, 2}, {1, 3}}));
        System.out.println(new Solution().findMinHeightTrees(
                6, new int[][]{{0, 1}, {0, 2}, {0, 3}, {3, 4}, {4, 5}}));
    }

    // Thanks to dietpepsi.
    // "For a path graph of n nodes, find the minimum height trees is trivial. Just designate the middle point(s) as roots.
    // Suppose we don't know n, nor do we have random access of the nodes. We have to traversal. It is very easy to get the idea of two pointers.
    // One from each end and move at the same speed. For a tree we can do some thing similar.
    // We start from every end, by end we mean vertex of degree 1 (aka leaves). We let the pointers move the same speed.
    // When two pointers meet, we keep only one of them, until the last two pointers meet or one step away we then find the roots.
    // It is easy to see that the last two pointers are from the two ends of the longest path in the graph."
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return Collections.singletonList(0);
        // Group non-duplicate edges by vertex
        // (Array is more efficient than Map, List is enough for non-duplicate edges)
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        // Collect outmost leaves
        List<Integer> leaves = new ArrayList<>();
        for (int v = 0; v < adj.length; v++)
            if (adj[v].size() == 1)
                leaves.add(v);

        // Move inwards from every leaf
        while (n > 2) { // Hint: at most two root nodes
            n -= leaves.size(); // Very careful! Must place here! n-=size>2 or for(;n>2;n-=size) both wrong!
            List<Integer> newLeaves = new ArrayList<>();
            for (int leaf : leaves) {
                int inner = adj[leaf].get(0);
                adj[inner].remove((Integer) leaf);
                if (adj[inner].size() == 1)
                    newLeaves.add(inner);
            }
            leaves = newLeaves;
        }
        return leaves;
    }

}
