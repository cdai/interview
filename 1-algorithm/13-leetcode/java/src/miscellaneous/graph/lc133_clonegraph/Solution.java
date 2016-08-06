package miscellaneous.graph.lc133_clonegraph;

import miscellaneous.graph.UndirectedGraphNode;

import java.util.HashMap;
import java.util.Map;

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

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {         // error1: OMG! OJ try null input at times...
            return null;
        }
        return doClone(node, new HashMap<>());
    }

    private UndirectedGraphNode doClone(UndirectedGraphNode src,
                                        Map<UndirectedGraphNode, UndirectedGraphNode> clones) {
        UndirectedGraphNode target = new UndirectedGraphNode(src.label);
        clones.put(src, target);

        for (UndirectedGraphNode n : src.neighbors) {
            if (clones.containsKey(n)) {                // error2: still add to neighbors, just don't clone, don't miss it!
                target.neighbors.add(clones.get(n));    // Could move to base case, but less recursion in this way
            } else {
                target.neighbors.add(doClone(n, clones));
            }
        }
        return target;
    }
}

