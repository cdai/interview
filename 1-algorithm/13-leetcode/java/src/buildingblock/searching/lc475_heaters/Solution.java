package buildingblock.searching.lc475_heaters;

import java.util.Arrays;

/**
 */
public class Solution {

    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int rad = 0, n = heaters.length;
        for (int h : houses) {
            int i = Arrays.binarySearch(heaters, h);
            if (i < 0) {
                i = ~i; //i = -(i + 1);
                int r1 = (0 < i) ? h - heaters[i - 1] : Integer.MAX_VALUE;
                int r2 = (i < n) ? heaters[i] - h : Integer.MAX_VALUE;
                rad = Math.max(rad, Math.min(r1, r2));
            } /* ignore house on heater for which radius=0 */
        }
        return rad;
    }

}
