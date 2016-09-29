package advanced.datastructure.graph.directed.lc210_courseschedule2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]. Given the total number of courses and a list of prerequisite pairs,
 * return the ordering of courses you should take to finish all courses.
 * There may be multiple correct orders, you just need to return one of them.
 * If it is impossible to finish all courses, return an empty array.
 * For example:
 *  2, [[1,0]]. There are a total of 2 courses to take. To take course 1 you should have finished course 0.
 *      So the correct course order is [0,1]
 *  4, [[1,0],[2,0],[3,1],[3,2]]. There are a total of 4 courses to take.
 *      To take course 3 you should have finished both courses 1 and 2.
 *      Both courses 1 and 2 should be taken after you finished course 0.
 *      So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().findOrder(2, new int[][]{{1, 0}}));
    }

    public int[] findOrder(int num, int[][] prereq) {
        List<Integer>[] adj = new List[num];
        int[] indegree = new int[num];
        for (int i = 0; i < num; i++) adj[i] = new ArrayList<>();
        for (int[] req : prereq) {
            adj[req[1]].add(req[0]);
            indegree[req[0]]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < num; i++)
            if (indegree[i] == 0) q.offer(i);

        int cnt = 0;
        int[] order = new int[num];
        for (; !q.isEmpty(); cnt++) {
            order[cnt] = q.poll();
            for (int crs : adj[order[cnt]])
                if (--indegree[crs] == 0) q.offer(crs);
        }
        return (cnt == num) ? order : new int[0]; // must check if can finish
    }

    // O(V + E) time, O(V) space
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new List[numCourses];
        for (int v = 0; v < numCourses; v++)
            adj[v] = new ArrayList<>();
        int[] indegree = new int[numCourses];
        for (int[] e : prerequisites) {
            int cur = e[0], pre = e[1];
            indegree[cur]++;
            adj[pre].add(cur);      // pre -> cur will get the right order
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            if (indegree[i] == 0)
                queue.offer(i);

        int[] order = new int[numCourses];
        int visited = 0;
        while (!queue.isEmpty()) {
            int pre = queue.poll();
            order[visited++] = pre;
            for (int cur : adj[pre])
                if (--indegree[cur] == 0)
                    queue.offer(cur);
        }
        return visited == numCourses ? order : new int[0];
    }

}
