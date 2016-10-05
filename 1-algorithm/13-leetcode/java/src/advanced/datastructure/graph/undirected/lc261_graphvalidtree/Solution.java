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

    public static void main(String[] args) {
        System.out.println(new Solution().validTree(2, new int[][]{{0, 1}}));
        System.out.println(new Solution().validTree(2, new int[][]{{0, 1}, {1, 0}}));
        System.out.println(new Solution().validTree(3, new int[][]{{0, 1}, {2, 0}}));
        System.out.println(new Solution().validTree(5, new int[][]{{0, 1}, {2, 0}, {3,4}}));
    }

    public boolean validTree(int n, int[][] edges) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        boolean[] visit = new boolean[n];
        if (hasCycle(adj, visit, 0, -1)) return false;

        for (boolean vis : visit)
            if (!vis) return false;
        return true;
    }

    private boolean hasCycle(List<Integer>[] adj, boolean[] visit, int v, int par) {
        if (visit[v]) return true;
        visit[v] = true;
        for (int nb : adj[v])
            if (nb != par && hasCycle(adj, visit, nb, v)) return true;
        return false;
    }

    // My old-school solution. O(E + V) time.
    // A tree is an acyclic connected graph.
    public boolean validTree2(int n, int[][] edges) {
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
