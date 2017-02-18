package advanced.datastructure.unionfind.lc305_numberofislands2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 */
public class Solution {

    @Test
    void test() {
        Assertions.assertEquals(Arrays.asList(1,1,1,2,2),
                numIslands2(3, 3, new int[][]{{0,0}, {0,1}, {1,0}, {2,2}, {2,1}}));
    }

    public List<Integer> numIslands2(int m, int n, int[][] ops) {
        List<Integer> ret = new ArrayList<>();
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int[] islands = new int[m * n];
        Arrays.fill(islands, -1);

        int cnt = 0;
        for (int[] op : ops) {
            int root1 = op[0] * m + op[1];
            islands[root1] = root1;
            cnt++;
            for (int[] d : dirs) {
                int x = op[0] + d[0], y = op[1] + d[1], id2 = x * m + y;
                if (x < 0 || x >= m || y < 0 || y >= n || islands[id2] == -1) continue;
                int root2 = find(islands, id2);
                if (root1 != root2) {
                    islands[root2] = root1; // or optimize: islands[root1]=root2; root1=root2;
                    cnt--;
                }
            }
            ret.add(cnt);
        }
        return ret;
    }

    private int find(int[] islands, int id) {
        if (islands[id] != id) islands[id] = find(islands, islands[id]);
        return islands[id];
    }

}
