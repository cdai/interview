package advanced.datastructure.graph.undirected.lc310_minimumheighttrees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.
 * Format: The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 * Example 1: Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]
 *    0
 *    |
 *    1
 *   / \
 *  2   3
 * return [1]
 * Example 2: Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
 *  0  1  2
 *   \ | /
 *     3
 *     |
 *     4
 *     |
 *     5
 * return [3, 4]
 * Hint: How many MHTs can a graph have at most?
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().findMinHeightTrees(
                4, new int[][]{{0, 1}, {1, 2}, {1, 3}}));
        System.out.println(new Solution().findMinHeightTrees(
                6, new int[][]{{0, 1}, {0, 2}, {0, 3}, {3, 4}, {4, 5}}));
        System.out.println(new Solution().findMinHeightTrees(
                6, new int[][]{{0, 3}, {1, 3}, {2, 3}, {3, 4}, {4, 5}}));
    }

    // 3AC, more like Topo-sort
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n <= 1) return Arrays.asList(0);
        Set<Integer>[] adj = new Set[n];
        for (int i = 0; i < n; i++) adj[i] = new HashSet<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++)
            if (adj[i].size() == 1) q.offer(i); // outdegree = 1 (leaf node)

        while (n > 2) {
            n -= q.size();
            for (int i = q.size(); i > 0; i--) {
                int v = q.poll();
                int nv = adj[v].iterator().next();
                adj[nv].remove(v);
                if (adj[nv].size() == 1) // Only enter once for each node, eg.0-1, 1-2, 1-3
                    q.offer(nv);
            }
        }
        return q.size() == 1 ? Arrays.asList(q.poll()) : Arrays.asList(q.poll(), q.poll());
    }

    // Thanks to dietpepsi. O(N) solution.
    // "For a path graph of n nodes, find the minimum height trees is trivial. Just designate the middle point(s) as roots.
    // Suppose we don't know n, nor do we have random access of the nodes. We have to traversal. It is very easy to get the idea of two pointers.
    // One from each end and move at the same speed. For a tree we can do some thing similar.
    // We start from every end, by end we mean vertex of degree 1 (aka leaves). We let the pointers move the same speed.
    // When two pointers meet, we keep only one of them, until the last two pointers meet or one step away we then find the roots.
    // It is easy to see that the last two pointers are from the two ends of the longest path in the graph."
    public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        if (n == 1) return Collections.singletonList(0);
        // 1.Group non-duplicate edges by vertex to form a adjacent list
        // (Array is more efficient than Map, List is enough for non-duplicate edges)
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        // 2.Collect all leaves
        List<Integer> leaves = new ArrayList<>();
        for (int v = 0; v < adj.length; v++)
            if (adj[v].size() == 1)
                leaves.add(v);

        // 3.Move inwards from every leaf until 1 or 2 left
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
