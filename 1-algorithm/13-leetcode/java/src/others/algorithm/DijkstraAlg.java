package others.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

class Solution {

    // 1        # of test case
    // 4 4      # of vertex and edges
    // 1 2 24   v1, v2, weight
    // 1 4 20
    // 3 1 3
    // 4 3 12
    // 1        source vertext
    public static void main(String[] args) {
        Solution sol = new Solution();
        try (Scanner in = new Scanner(System.in)) {
            int tests = in.nextInt();
            while (tests-- > 0) {
                List<Edge>[] adj = new List[in.nextInt() + 1];
                for (int i = 0; i < adj.length; i++) {
                    adj[i] = new ArrayList<>();
                }
                for (int i = in.nextInt(); i > 0; i--){
                    int v1 = in.nextInt();
                    int v2 = in.nextInt();
                    int weight = in.nextInt();
                    adj[v1].add(new Edge(v1, v2, weight));
                    adj[v2].add(new Edge(v2, v1, weight));
                }
                int s = in.nextInt();

                int[] paths = sol.shortestPath(s, adj);
                for (int p : paths) {
                    System.out.print(p + " ");
                }
                System.out.println();
            }
        }
    }

    @Test
    void test() {
        List<Edge>[] adj = new List[5];
        adj[1] = Arrays.asList(new Edge(1, 2, 24), new Edge(1, 3, 3), new Edge(1, 4, 20));
        adj[2] = Arrays.asList(new Edge(2, 1, 24));
        adj[3] = Arrays.asList(new Edge(3, 1, 3), new Edge(3, 4, 12));
        adj[4] = Arrays.asList(new Edge(4, 1, 20), new Edge(4, 3, 12));
        Assertions.assertArrayEquals(new int[]{24, 3, 15}, shortestPath(1, adj));
    }

    // http://cs.fit.edu/~ryan/java/programs/graph/Dijkstra-java.html
    public int[] shortestPath(int s, List<Edge>[] adj) {
        int n = adj.length - 1;
        int[] dist = new int[n + 1], pred = new int[n + 1];
        boolean[] vis = new boolean[n + 1];

        // Initialize single source
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        pred[s] = s;

        // Calculate shortest path for each vertex. At first, S=nil,Q=all.
        for (int i = 0; i < n; i++) {
            // Choose shortest dist (from s to v) estimate from Q in O(V)
            int v = 0;
            for (int j = 1; j <= n; j++) {
                if (!vis[j] && dist[v] > dist[j]) {
                    v = j;
                }
            }
            // Put into S and relax incidence (update all adjacent vertex dist) in O(E)
            vis[v] = true;
            for (Edge e : adj[v]) {
                if (dist[e.to] > dist[v] + e.weight) {
                    dist[e.to] = dist[v] + e.weight;
                    pred[e.to] = v;
                }
            }
        }

        int[] ret = new int[n - 1];
        for (int i = 1, j = 0; i <= n; i++) {
            if (i != s) {
                ret[j++] = dist[i];
            }
        }
        return ret;
    }

    public int[] shortestPath2(int s, List<Edge>[] adj) {
        // Initialize single source
        int n = adj.length;
        Vertex[] vert = new Vertex[n];
        for (int i = 1; i < n; i++) {
            vert[i] = new Vertex(i, Integer.MAX_VALUE, 0);
        }
        vert[s].dist = 0;

        // At first, S=nil,Q=all. Choose shortest dist (from s to it) estimate.
        // Put into S and update all adjacent vertex dist.
        Queue<Vertex> q = new PriorityQueue<>(Comparator.comparingInt(v1 -> v1.dist));
        for (int i = 1; i < n; i++) {
            q.offer(vert[i]);
        }
        Set<Vertex> vis = new TreeSet<>(Comparator.comparingInt(v1 -> v1.id));
        while (vis.size() < n) {
            Vertex v = q.poll();
            vis.add(v);
            for (Edge e : adj[v.id]) {
                relax(q, v, vert[e.to], e);
            }
        }

        // Get shortest distance of all vertices
        int[] dist = new int[n - 2];
        int i = 0;
        for (Vertex v : vis) {
            if (s != v.id) {
                dist[i++] = v.dist;
            }
        }
        return dist;
    }

    /** Java Heap doesn't support decreaseKey, insert new object as workaround */
    private void relax(Queue<Vertex> q, Vertex u, Vertex v, Edge e) {
        if (v.dist > u.dist + e.weight) {
            q.offer(new Vertex(v.id, u.dist + e.weight, u.id));
        }
    }

    static class Vertex {
        int id, dist, pre;
        Vertex(int id, int dist, int pre) {
            this.id = id;
            this.dist = dist;
            this.pre = pre;
        }
    }

    static class Edge {
        int from, to, weight;
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}

