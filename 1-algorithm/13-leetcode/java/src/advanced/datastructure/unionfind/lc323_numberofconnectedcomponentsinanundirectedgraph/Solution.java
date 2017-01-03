package advanced.datastructure.unionfind.lc323_numberofconnectedcomponentsinanundirectedgraph;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to find the number of connected components in an undirected graph.
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.countComponents(5, new int[][]{{0, 1}, {1, 2}, {3, 4}})); //=2
        System.out.println(sol.countComponents(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}})); //=1
        System.out.println(sol.countComponents(5, new int[][]{{0, 1}, {1, 2}, {2, 0}, {3, 4}})); //=2 (2,0 is useless)
    }

    // Every valid union reduces one existing component
    // O(MlogN) time? M operations and N components
    public int countComponents(int n, int[][] edges) {
        int[] root = new int[n];
        for (int i = 0; i < n; i++) root[i] = i;

        int comp = n;
        for (int[] e : edges)
            if (union(root, find(root, e[0]), find(root, e[1])))
                comp--;
        return comp;
    }

    private boolean union(int[] root, int s1, int s2) {
        if (s1 == s2) return false;
        root[s1] = s2;
        return true;
    }

    private int find(int[] root, int i) {
        if (root[i] != i)
            root[i] = find(root, root[i]);
        return root[i];
    }

}
