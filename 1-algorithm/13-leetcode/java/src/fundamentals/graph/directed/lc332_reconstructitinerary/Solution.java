package fundamentals.graph.directed.lc332_reconstructitinerary;

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

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.findItinerary(new String[][]{
                {"JFK", "SFO"}, {"JFK", "ATL"}, {"SFO", "ATL"}, {"ATL", "JFK"}, {"ATL", "SFO"}}));
        System.out.println(sol.findItinerary(new String[][]{
                {"EZE", "AXA"}, {"TIA", "ANU"}, {"ANU", "JFK"}, {"JFK", "ANU"}, {"ANU", "EZE"}, {"TIA", "ANU"},
                {"AXA", "TIA"}, {"TIA", "JFK"}, {"ANU", "TIA"}, {"JFK", "TIA"}}));
    }

    // Find Eulerian path in O(E) time.
    public List<String> findItinerary(String[][] tickets) {
        Map<String, Queue<String>> adj = new HashMap<>();
        for (String[] t : tickets) {
            if (!adj.containsKey(t[0])) adj.put(t[0], new PriorityQueue<>());
            adj.get(t[0]).offer(t[1]);
        }
        return dfs(adj, new LinkedList<>(), "JFK");
    }

    private List<String> dfs(Map<String, Queue<String>> adj, LinkedList<String> path, String airport) {
        Queue<String> q = adj.get(airport);
        while (q != null && !q.isEmpty())
            dfs(adj, path, q.poll());
        path.addFirst(airport); // add node or merge blocked path to main path
        return path;
    }

    // NOTE: duplicate edges incident from node
    public List<String> findItinerary_slowdfs(String[][] tickets) {
        Map<String,List<String>> adj = new HashMap<>();
        for (String[] t : tickets) {
            if (!adj.containsKey(t[0])) adj.put(t[0], new ArrayList<>());
            adj.get(t[0]).add(t[1]);
        }
        List<String> path = new ArrayList<>();
        path.add("JFK");
        return dfs(adj, path, "JFK", tickets.length + 1);
    }

    private List<String> dfs(Map<String, List<String>> adj, List<String> path, String city, int length) {
        if (path.size() == length) return path;
        if (!adj.containsKey(city)) return null;
        for (String dest : new TreeSet<>(adj.get(city))) {
            adj.get(city).remove(dest); // Slow...
            path.add(dest);
            List<String> ret = dfs(adj, path, dest, length);
            if (ret != null) return ret;
            path.remove(path.size() - 1);
            adj.get(city).add(dest);
        }
        return null;
    }

    // Stefan solution: Eulerian path/circuit + Greedy DFS, building the route backwards when retreating. O(|E|) time.
    // Note: both TreeSet removes duplicate and hard to remove elements, but PriorityQueue (Heap) won't.
    public List<String> findItinerary2(String[][] tickets) {
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
