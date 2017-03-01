package hackerrank.algorithm.graph.shortestpath;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 */
public class DijkstraAlg {

    // 1        # of test case
    // 4 4      # of vertex and edges
    // 1 2 24   v1, v2, weight
    // 1 4 20
    // 3 1 3
    // 4 3 12
    // 1        source vertext
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int tests = in.nextInt();
            while (tests-- > 0) {
                List<Edge>[] edges = new List[in.nextInt() + 1];
                for(int i = in.nextInt(); i > 0; i--){
                    int v1 = in.nextInt();
                    int v2 = in.nextInt();
                    int weight = in.nextInt();
                    if (edges[v1] == null) {
                        edges[v1] = new ArrayList<>();
                    }
                    edges[v1].add(new Edge(v1, v2, weight));
                }
                int s = in.nextInt();

                int[] paths = shortestPath(s, edges);
                for (int p : paths) {
                    System.out.print(p + " ");
                }
                System.out.println();
            }
        }
    }

    private static int[] shortestPath(int s, List<Edge>[] edges) {

        return new int[] {0};
    }

    static class Edge {
        int v1, v2, weight;
        Edge(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }
    }

}
