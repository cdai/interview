package advanced.datastructure.graph.directed.lc332_reconstructitinerary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/**
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
 * reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK.
 * Thus, the itinerary must begin with JFK.
 * Note: If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order
 * when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 * Example 1:
 *  tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 *  Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
 * Example 2:
 *  tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 *  Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
 *  Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
 */
public class Solution {

    // Stefan solution: Eulerian path/circuit + Greedy DFS, building the route backwards when retreating. O(|E|) time.
    // Note: both TreeSet removes duplicate and hard to remove elements, but PriorityQueue (Heap) won't.
    public List<String> findItinerary(String[][] tickets) {
        Map<String,Queue<String>> adj = new HashMap<>();
        for (String[] t : tickets)
            adj.computeIfAbsent(t[0], k -> new PriorityQueue<>()).add(t[1]); // Nice but slow!
        return dfs(new LinkedList<>(), adj, "JFK");
    }

    // Idea: "First keep going forward until you get stuck. That's a good main path already.
    // Remaining tickets form cycles which are found on the way back and get merged into that main path."
    private List<String> dfs(List<String> route, Map<String,Queue<String>> adj, String src) {
        Queue<String> q = adj.get(src);
        while (q != null && !q.isEmpty())
            dfs(route, adj, q.poll());
        route.add(0, src);  // this route is stuck, save it and backtrack. at last this route will be the last one
        return route;       // because "all tickets form at least one valid itinerary"
    }

    public List<String> findItinerary_wrong(String[][] tickets) {
        Map<String,Set<String>> adj = new HashMap<>();
        for (String[] t : tickets) {
            String src = t[0], dest = t[1];
            Set<String> dests = adj.get(src);
            if (dests == null) {
                dests = new TreeSet<>();
                adj.put(src, dests);
            }
            dests.add(dest);
        }

        List<String> result = new ArrayList<>();
        dfs2(result, adj, "JFK");
        return result;
    }

    private void dfs2(List<String> result, Map<String,Set<String>> adj, String src) {
        Set<String> dests = adj.get(src);
        if (dests == null || dests.isEmpty())
            return;

        result.add(src);

        Iterator<String> it = dests.iterator();
        src = it.next();
        it.remove();
        dfs2(result, adj, src);
    }

}
