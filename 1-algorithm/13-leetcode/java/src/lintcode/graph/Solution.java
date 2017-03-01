package lintcode.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Created by cdai on 3/1/17.
 */
public class Solution {

    // 176-Route Between Two Nodes in Graph. Both DFS and BFS in O(V+E) time.
    public boolean hasRoute(ArrayList<DirectedGraphNode> graph,
                            DirectedGraphNode s, DirectedGraphNode t) {
        return dfs(graph, s, t, new HashSet<DirectedGraphNode>());
    }

    private boolean dfs(List<DirectedGraphNode> graph,
                        DirectedGraphNode s, DirectedGraphNode t,
                        Set<DirectedGraphNode> vis) {
        if (s == t) return true; // for case s=t at very first
        if (s == null || t == null) return false; // in case null edge?

        vis.add(s);
        for (DirectedGraphNode nb : s.neighbors) {
            if (vis.contains(nb)) continue; // back edge (cycle)
            if (dfs(graph, nb, t, vis)) return true;
        }
        return false;
    }

    public boolean hasRoute_bfs(ArrayList<DirectedGraphNode> graph,
                            DirectedGraphNode s, DirectedGraphNode t) {
        Set<DirectedGraphNode> vis = new HashSet<>();
        Queue<DirectedGraphNode> q = new LinkedList<>();
        if (s != null) q.offer(s);
        while (!q.isEmpty()) {
            DirectedGraphNode v = q.poll();
            if (v == t) return true;
            if (vis.contains(v)) continue;
            vis.add(v);

            for (DirectedGraphNode nb : v.neighbors) {
                if (nb != null) q.offer(nb);
            }
        }
        return false;
    }

}
