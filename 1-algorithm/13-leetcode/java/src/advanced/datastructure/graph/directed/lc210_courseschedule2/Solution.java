package advanced.datastructure.graph.directed.lc210_courseschedule2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().findOrder(2, new int[][]{{1, 0}}));
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new List[numCourses];
        for (int v = 0; v < numCourses; v++)
            adj[v] = new ArrayList<>();
        int[] indegree = new int[numCourses];
        for (int[] e : prerequisites) {
            int cur = e[0], pre = e[1];
            indegree[pre]++;
            adj[cur].add(pre);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int v : indegree)
            queue.offer(v);

        int[] order = new int[numCourses];
        int visited = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            order[visited++] = cur;
            for (int pre : adj[cur])
                if (--indegree[pre] == 0)
                    queue.offer(pre);
        }
        return visited == numCourses ? order : new int[0];
    }

}
