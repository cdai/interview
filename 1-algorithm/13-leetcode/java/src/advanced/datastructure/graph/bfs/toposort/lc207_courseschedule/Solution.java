package advanced.datastructure.graph.bfs.toposort.lc207_courseschedule;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]. Given the total number of courses and a list of prerequisite pairs,
 * is it possible for you to finish all courses?
 * For example:
 *  2, [[1,0]] : There are a total of 2 courses to take.
 *      To take course 1 you should have finished course 0. So it is possible.
 *  2, [[1,0],[0,1]]: There are a total of 2 courses to take.
 *      To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1.
 *      So it is impossible.
 */
public class Solution {

    // 4AC. Standard DFS approach from CLRS.
    public boolean canFinish(int n, int[][] prereq) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : prereq) adj[e[1]].add(e[0]);

        int[] vis = new int[n];     // reuse so that each node visited once
        for (int i = 0; i < n; i++) // must check every node. eg.[1,0],[0,1]
            if (dfs(adj, i, vis)) return false;
        return true;
    }

    // Check if back edge (directed cycle) exists. If not => DAG => able to topo sort
    private boolean dfs(List<Integer>[] adj, int v, int[] vis) {
        vis[v] = 1;
        for (int nb : adj[v]) {
            if (vis[nb] == 1) return true; // visited and nb is v's ancestor => back edge
            if (vis[nb] == 0 && dfs(adj, nb, vis)) return true; // nb is not visited => tree edge
            // else vis[nb]==2, nb is visited but not ancestor => forward or cross edge
        }
        vis[v] = 2;
        return false;
    }

    // 4AC.
    // My 3AC. Use adjacent list. O(V + E).
    public boolean canFinish4(int num, int[][] prereq) {
        if (prereq.length == 0 || prereq[0].length == 0) return true;

        List<Integer>[] adj = new List[num]; // Don't use Set. Input may contain duplicate edges
        int[] indegree = new int[num];
        for (int i = 0; i < num; i++) adj[i] = new ArrayList<>();
        for (int[] req : prereq) {
            adj[req[1]].add(req[0]); // prereq -> crs
            indegree[req[0]]++;      // save in-degree, otherwise it's unknown
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < num; i++)
            if (indegree[i] == 0) q.offer(i);

        int cnt = 0;
        for (; !q.isEmpty(); cnt++)
            for (int crs : adj[q.poll()])
                if (--indegree[crs] == 0) q.offer(crs);
        return cnt == num;
    }

    // Topological sort
    public boolean canFinish2_adjmatrix(int numCourses, int[][] prerequisites) {
        // Adjacent matrix is convenient for exist check, but waste a lot
        int[] indegree = new int[numCourses];
        boolean[][] adj = new boolean[numCourses][numCourses];
        for (int[] e : prerequisites) {
            int cur = e[1], pre = e[0];
            if (!adj[cur][pre])     // error: input could contains duplicate edge, why...
                indegree[pre]++;
            adj[cur][pre] = true;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int v = 0; v < numCourses; v++)
            if (indegree[v] == 0)
                queue.offer(v);

        int count = 0;
        for (; !queue.isEmpty(); count++) {
            int cur = queue.poll();
            for (int pre = 0; pre < numCourses; pre++)
                if (adj[cur][pre] && --indegree[pre] == 0)
                    queue.offer(pre);
        }
        return count == numCourses;
    }

    // TLE since DFS does many redudent work. Eg.2->1->0, it checks 2 then 1 then 0...
    public boolean canFinish_dfs(int numCourses, int[][] prerequisites) {
        // 1.Create adjacent list
        List<Integer>[] adj = new List[numCourses];
        for (int[] pre : prerequisites) {
            if (adj[pre[1]] == null)        // Save memory since only some have prerequisite
                adj[pre[1]] = new ArrayList<>();
            adj[pre[1]].add(pre[0]);        // From cur -> prerequisite
        }

        // 2.DFS check each "island" if cycle exist
        boolean[] visited = new boolean[numCourses];
        for (int v = 0; v < numCourses; v++)
            if (ifCycleExist(v, adj, visited))
                return false;
        return true;
    }

    private boolean ifCycleExist(int vertex, List<Integer>[] adj, boolean[] visited) {
        if (visited[vertex]) return true;
        if (adj[vertex] == null) return false;

        visited[vertex] = true;
        for (int neighbor : adj[vertex])
            if (ifCycleExist(neighbor, adj, visited))
                return true;
        visited[vertex] = false; // Here is the key point! No cycle at this or lower level
        return false;
    }

}
