package buildingblock.table.hashing.lc554_brickwall;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There is a brick wall in front of you. The wall is rectangular and has several rows of bricks.
 * The bricks have the same height but different width.
 * You want to draw a vertical line from the top to the bottom and cross the least bricks.
 */
public class Solution {

    public int leastBricks(List<List<Integer>> wall) {
        // Count how many edges in each position
        Map<Integer, Integer> map = new HashMap<>();
        for (List<Integer> row : wall) {
            int edge = 0;
            for (int i = 0; i < row.size() - 1; i++) {
                edge += row.get(i);
                map.put(edge, map.getOrDefault(edge, 0) + 1);
            }
        }

        // Max edges means min brick to cross
        int max = 0;
        for (int edge : map.values()) {
            max = Math.max(max, edge);
        }
        return wall.size() - max;
    }

}
