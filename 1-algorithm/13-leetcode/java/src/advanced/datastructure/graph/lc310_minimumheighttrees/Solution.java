package advanced.datastructure.graph.lc310_minimumheighttrees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().findMinHeightTrees(
                6, new int[][]{{0, 1}, {0, 2}, {0, 3}, {3, 4}, {4, 5}}));
    }

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
        for (List<Integer> e : adj)
            if (e.size() == 1)
                leaves.add(e.get(0));

        // Move inwards from every leaf
        while (n > 2) { // Hint: at most two root nodes
            n -= leaves.size();
            System.out.println(leaves);
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
