package advanced.datastructure.unionfind.lc352_datastreamasdisjointintervals;

import miscellaneous.interval.Interval;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 */
public class SummaryRanges1 {

    private TreeMap<Integer, Integer> ranges;

    /** Initialize your data structure here. */
    public SummaryRanges1() {
        this.ranges = new TreeMap<>();
    }

    // O(logN) time.
    public void addNum(int val) {
        if (ranges.containsKey(val)) return;
        int left = ranges.getOrDefault(val - 1, 0);
        int right = ranges.getOrDefault(val + 1, 0);
        int sum = left + right + 1;

        if (left > 0) ranges.put(val - left, sum);
        if (right > 0) ranges.put(val + right, sum);
        ranges.put(val, sum);
    }

    // O(logN) time as well but remove unused middle val.
    private Set<Integer> dup = new HashSet<>();
    public void addNum2(int val) {
        if (!dup.add(val)) return;
        int left = ranges.containsKey(val - 1) ? ranges.remove(val - 1) : 0;
        int right = ranges.containsKey(val + 1) ? ranges.remove(val + 1) : 0;
        int sum = left + right + 1;

        if (left > 0) ranges.put(val - left, sum);
        if (right > 0) ranges.put(val + right, sum);
        if (left == 0 || right == 0) ranges.put(val, sum); // remove middle val if it's not boundary to speed up getInt()
    }

    // O(N) time
    public List<Interval> getIntervals() {
        List<Interval> ret = new ArrayList<>();
        int last = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> e : ranges.entrySet()) {
            int left = e.getKey(), right = e.getValue();
            if (last < e.getKey()) {
                ret.add(new Interval(left, left + right - 1));
                last = left + right - 1;
            }
        }
        return ret;
    }

}
