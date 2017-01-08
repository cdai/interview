package advanced.datastructure.unionfind.lc352_datastreamasdisjointintervals;

import miscellaneous.interval.Interval;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
