package advanced.scan.twohalves.lc42_trappingrainwater;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 * For example, given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 */
public class Solution {

    public int trap(int[] height) {
        int n = height.length;
        if (n <= 2) { // error: at least 3, eg. [2,0,2]
            return 0;
        }

        // 1.Find highest line
        int highest = 0;
        for (int i = 1; i < n; i++) {
            if (height[highest] < height[i]) {
                highest = i;
            }
        }

        // 2.Handle left half from left to right
        int water = 0;
        for (int i = 0, lastHigh = 0; i < highest; i++) {
            if (height[i] > lastHigh) {
                lastHigh = height[i];
            } else {
                water += (lastHigh - height[i]);
            }
        }

        // 3.Handle right half from right to left
        for (int i = n - 1, lastHigh = 0; i > highest; i--) {
            if (height[i] > lastHigh) {
                lastHigh = height[i];
            } else {
                water += (lastHigh - height[i]);
            }
        }
        return water;
    }

    // Idea is correct but too many details...
    public int trap2(int[] height) {
        if (height.length < 2) {
            return 0;
        }

        // 1.Group line by height
        TreeMap<Integer,List<Integer>> heightGroup = new TreeMap<>();
        for (int i = 1; i < height.length; i++) {
            if (height[i] <= 0) {
                continue;
            }
            // Init list for current level
            if (heightGroup.containsKey(height[i])) {
                heightGroup.get(height[i]).add(i);
            } else {
                List<Integer> group = new ArrayList<>();
                group.add(i);
                heightGroup.put(height[i], group);
            }
            // All shorter line [1,height[i]] impacted
            for (List<Integer> group : heightGroup.headMap(height[i]).values()) {   // error1
                group.add(i);
            }
            // error3: [0,90,0,10] 90 should impact following 10...
        }

        // 2.Iterate group by each height level
        int water = 0, lastHeight = 0;
        for (Map.Entry<Integer,List<Integer>> e : heightGroup.entrySet()) {
            int curHeight = e.getKey();
            List<Integer> group = e.getValue();

            int last = group.get(0);
            int fill = curHeight - lastHeight;                                      // error2
            for (int i = 1; i < group.size(); i++) {
                int cur = group.get(i);
                water += ((cur - last - 1) * fill);
                last = cur;
            }
            lastHeight = curHeight;
        }
        return water;
    }

    // Wrong! Had problem with last one water of [0,1,0,2,1,0,1,3,2,1,2,1]
    // Since you never know what happen afterwards
    public int trap3(int[] height) {
        if (height.length < 2) {
            return 0;
        }

        int last = 0, water = 0, stone = height[0];
        for (int i = 1; i < height.length; i++) {
            if (height[i] >= height[last]) {
                int total = Math.min(height[i], height[last]) * (i - last);
                water += (total - stone);
                last = i;
                stone = height[i];
            } else {
                stone += height[i];
            }
        }
        return water;
    }

}
