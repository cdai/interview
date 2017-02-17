package fundamentals.graph.dfs.lc133_clonegraph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 */
public class Solution {

    public static void main(String[] args) {
        UndirectedGraphNode n1 = new UndirectedGraphNode(1);
        UndirectedGraphNode n2 = new UndirectedGraphNode(2);
        UndirectedGraphNode n3 = new UndirectedGraphNode(3);
        UndirectedGraphNode n4 = new UndirectedGraphNode(4);
        UndirectedGraphNode n5 = new UndirectedGraphNode(5);
        UndirectedGraphNode n6 = new UndirectedGraphNode(6);
        n1.neighbors.add(n2);
        n1.neighbors.add(n3);
        n1.neighbors.add(n6);
        n2.neighbors.add(n4);
        n3.neighbors.add(n4);
        n3.neighbors.add(n5);

        UndirectedGraphNode clone = new Solution().cloneGraph(n1);
        System.out.println(clone);
    }

    // 4AC.
    // Invariant: copy children and put into copies cache. push child to queue if not visited yet.
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        Map<UndirectedGraphNode,UndirectedGraphNode> vis = new HashMap<>();
        Queue<UndirectedGraphNode> q = new LinkedList<>();
        q.offer(node);
        vis.put(node, new UndirectedGraphNode(node.label));
        while (!q.isEmpty()) {
            UndirectedGraphNode par = q.poll();
            for (UndirectedGraphNode nb : par.neighbors) {
                if (!vis.containsKey(nb)) {
                    vis.put(nb, new UndirectedGraphNode(nb.label));
                    q.offer(nb);
                }
                vis.get(par).neighbors.add(vis.get(nb));
            }
        }
        return vis.get(node);
    }

    // My 3AC. BFS solution
    public UndirectedGraphNode cloneGraph3(UndirectedGraphNode node) {
        if (node == null) return null;
        Queue<UndirectedGraphNode> q = new LinkedList<>();
        Map<Integer,UndirectedGraphNode> visit = new HashMap<>();
        q.offer(node);
        visit.put(node.label, new UndirectedGraphNode(node.label));
        while (!q.isEmpty()) {
            UndirectedGraphNode org = q.poll();
            for (UndirectedGraphNode ng : org.neighbors) { // Must handle neighbor in this round!
                UndirectedGraphNode clone = visit.get(ng.label);
                if (clone == null) { // Note: If not visited, create, visit and add to queue
                    clone = new UndirectedGraphNode(ng.label);
                    visit.put(clone.label, clone);
                    q.offer(ng);
                }
                visit.get(org.label).neighbors.add(clone);
            }
        }
        return visit.get(node.label);
    }

    // 4AC.
    // O(N) time
    public UndirectedGraphNode cloneGraph_dfs(UndirectedGraphNode node) {
        if (node == null) return null;
        return dfs(node, new HashMap<>());
    }

    private UndirectedGraphNode dfs(UndirectedGraphNode node, Map<Integer,UndirectedGraphNode> vis) {
        if (vis.containsKey(node.label)) return vis.get(node.label);
        UndirectedGraphNode cpy = new UndirectedGraphNode(node.label);
        vis.put(cpy.label, cpy);
        for (UndirectedGraphNode nb : node.neighbors) {
            cpy.neighbors.add(dfs(nb, vis));
        }
        return cpy;
    }

    // My 1AC
    public UndirectedGraphNode cloneGraph1(UndirectedGraphNode node) {
        if (node == null) {         // error1: OMG! OJ try null input at times...
            return null;
        }
        return dfs(node, new HashMap<>());
    }

    private UndirectedGraphNode doClone1(UndirectedGraphNode src,
                                        Map<UndirectedGraphNode, UndirectedGraphNode> clones) {
        UndirectedGraphNode target = new UndirectedGraphNode(src.label);
        clones.put(src, target);

        for (UndirectedGraphNode n : src.neighbors) {
            if (clones.containsKey(n)) {                // error2: still add to neighbors, just don't clone, don't miss it!
                target.neighbors.add(clones.get(n));    // Could move to base case, but less recursion in this way
            } else {
                target.neighbors.add(doClone1(n, clones));
            }
        }
        return target;
    }
}

