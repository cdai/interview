package advanced.datastructure.graph.dfs.lc133_clonegraph;

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

    // Invariant: copy children and put into copies cache. push child to queue if not visited yet.
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        Queue<UndirectedGraphNode> q = new LinkedList<>();
        q.offer(node);
        Map<Integer, UndirectedGraphNode> copies = new HashMap<>();
        copies.put(node.label, new UndirectedGraphNode(node.label));
        while (!q.isEmpty()) {
            UndirectedGraphNode old = q.poll(), cpy = copies.get(old.label);
            for (UndirectedGraphNode n : old.neighbors) {
                if (!copies.containsKey(n.label)) {
                    copies.put(n.label, new UndirectedGraphNode(n.label)); // must clone child here
                    q.offer(n);
                }
                cpy.neighbors.add(copies.get(n.label));
            }
        }
        return copies.get(node.label);
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

    // O(N) time
    public UndirectedGraphNode cloneGraph_dfs(UndirectedGraphNode node) {
        if (node == null) return null;
        return dfs(node, new HashMap<>());
    }

    private UndirectedGraphNode dfs(UndirectedGraphNode node,
                                    Map<Integer,UndirectedGraphNode> copies) {
        if (copies.containsKey(node.label)) return copies.get(node.label);

        UndirectedGraphNode cpy = new UndirectedGraphNode(node.label);
        copies.put(cpy.label, cpy);

        for (UndirectedGraphNode n : node.neighbors)
            cpy.neighbors.add(dfs(n, copies));
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

