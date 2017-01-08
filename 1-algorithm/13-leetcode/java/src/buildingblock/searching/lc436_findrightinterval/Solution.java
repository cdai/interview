package buildingblock.searching.lc436_findrightinterval;

import miscellaneous.interval.Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 */
public class Solution {

    // 1.Sort: key is sorted by start "automatically"
    // 2.Index: Meanwhile value is used to save original index as satellite data
    // 3.Out-of-box successor API: ceilingEntry() return successor, particularly the least key >= the given key or null if there is no such key.
    public int[] findRightInterval(Interval[] ints) {
        if (ints.length == 0) return new int[0];
        int n = ints.length;
        TreeMap<Integer, Integer> idx = new TreeMap<>();
        for (int i = 0; i < n; i++) idx.put(ints[i].start, i);

        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            Map.Entry<Integer, Integer> e = idx.ceilingEntry(ints[i].end);
            ret[i] = (e != null) ? e.getValue() : -1;
        }
        return ret;
    }

    // O(NlogN) sort, O(NlogN) search time.
    public int[] findRightInterval2(Interval[] ints) {
        if (ints.length == 0) return new int[0];

        // Sort intervals in start point and save original index
        int n = ints.length;
        Interval[] nints = Arrays.copyOf(ints, n);
        Arrays.sort(nints, Comparator.comparingInt(i -> i.start));
        Map<Integer, Integer> idx = new HashMap<>();
        for (int i = 0; i < ints.length; i++) idx.put(ints[i].start, i);

        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            int l = 0, r = n - 1;
            while (l < r) { // Essentially, find closest start point to ints[i].end
                int m = l + (r - l) / 2;
                if (ints[i].end > nints[m].start) l = m + 1; // m is too small
                else r = m; // m is larger or equal, so keep m (it's OK: l=r-1 -> m=l -> l=r)
            }
            ret[i] = (ints[i].end <= nints[l].start) ? idx.get(nints[l].start) : -1; /* l==r */
        }
        return ret;
    }

}
