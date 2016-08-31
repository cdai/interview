package advanced.datastructure.graph.directed.lc207_courseschedule;

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

    // Topological sort
    public boolean canFinish(int numCourses, int[][] prerequisites) {
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
