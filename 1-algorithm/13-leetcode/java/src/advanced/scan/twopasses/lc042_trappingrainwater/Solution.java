package advanced.scan.twopasses.lc042_trappingrainwater;

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

    // first search the maximal bar in the heights and then do two traverse -
    // one from the leftmost bar to the highest bar and another one from the rightmost to the highest bar.
    // This solution combine these three steps in a very clever way.
    public int trap(int[] height) {
        int ltall = 0, rtall = 0, water = 0;
        for (int l = 0, r = height.length - 1; l < r; ) {
            if (height[l] < height[r]) {
                if (ltall < height[l]) ltall = height[l];
                else water += ltall - height[l++];
            } else {
                if (rtall < height[r]) rtall = height[r];
                else water += rtall - height[r--];
            }
        }
        return water;
    }

    // O(N) time in two passes.
    public int trap_twopasses(int[] height) {
        if (height.length < 3) return 0;
        int tallest = 0;
        for (int i = 1; i < height.length; i++)
            if (height[tallest] < height[i]) tallest = i;

        int water = 0;
        for (int i = 0, tall = 0; i < tallest; i++) {
            if (tall < height[i]) tall = height[i];
            else water += tall - height[i];
        }
        for (int i = height.length - 1, tall = 0; i > tallest; i--) {
            if (tall < height[i]) tall = height[i];
            else water += tall - height[i];
        }
        return water;
    }

    public int trap1(int[] height) {
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
