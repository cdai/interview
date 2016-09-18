package advanced.datastructure.graph.undirected.lc261_graphvalidtree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to check whether these edges make up a valid tree.
 * Notice: You can assume that no duplicate edges will appear in edges. Since all edges are undirected,
 * [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */
public class Solution {

    // My old-school solution. O(N) time.
    // A tree is an acyclic connected graph.
    public boolean validTree(int n, int[][] edges) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        // DFS determine if acyclic
        boolean[] visit = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        while (!q.isEmpty()) {
            int v = q.poll(), par = 0;
            for (int nb : adj[v]) {
                if (visit[nb]) {
                    if (++par > 1) return false; // should only have one parent, otherwise found cycle!
                } else q.offer(nb);
            }
            visit[v] = true;
        }

        // Check if disjoint vertex
        for (boolean vis : visit)
            if (!vis) return false;
        return true;
    }

}
