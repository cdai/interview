package hackerrank.algorithm.graph.shortestreach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 */
public class Solution {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int nQry = Integer.parseInt(in.nextLine());
            while (nQry-- > 0) {
                int nVert = in.nextInt();
                int nEdge = in.nextInt();
                in.nextLine(); // Consume remaining char (\r\n) in this line

                int[][] edges = new int[nEdge][2];
                for (int i = 0; i < nEdge; i++, in.nextLine())
                    edges[i] = new int[] { in.nextInt(), in.nextInt() };

                int s = Integer.parseInt(in.nextLine().trim());
                int[] dist = shortestReachByBfs(nVert, edges, s);
                for (int i = 1; i < dist.length; i++)
                    if (i != s) System.out.print(dist[i] + " ");
                System.out.println();
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static int[] shortestReachByBfs(int nVert, int[][] edges, int s) {
        List<Integer>[] adj = new List[nVert + 1]; // Vertex 0 is no use
        for (int i = 1; i <= nVert; i++)
            adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        int[] dist = new int[nVert + 1];
        Arrays.fill(dist, -1);

        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        for (int i = 6; !q.isEmpty(); i += 6) { // Each edge is of length 6
            int size = q.size();
            while (size-- > 0) {
                int v = q.poll();
                for (int neighbor : adj[v]) {
                    if (neighbor != s && dist[neighbor] == -1) { // Check if visited reusing dist[] to spare visited[]
                        dist[neighbor] = i;
                        q.offer(neighbor);
                    }
                }
            }
        }
        return dist;
    }

}
