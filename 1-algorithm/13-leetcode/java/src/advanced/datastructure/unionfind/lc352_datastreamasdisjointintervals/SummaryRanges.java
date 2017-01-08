package advanced.datastructure.unionfind.lc352_datastreamasdisjointintervals;

import miscellaneous.interval.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.
 * Follow up: What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
 */
public class SummaryRanges {

    // Key - left or right boundary value of range
    // Value - size of range
    private Map<Integer, Integer> ranges;

    // Since middle val is removed, an extra set is required to de-duplicate
    private Set<Integer> dup;

    /** Initialize your data structure here. */
    public SummaryRanges() {
        //this.ranges = new TreeMap<>();
        this.ranges = new HashMap<>();
        this.dup = new HashSet<>();
    }

    // O(1) time?
    public void addNum(int val) {
        if (!dup.add(val)) return;
        int left = ranges.containsKey(val - 1) ? ranges.remove(val - 1) : 0;
        int right = ranges.containsKey(val + 1) ? ranges.remove(val + 1) : 0;
        int sum = left + right + 1;

        if (left > 0) ranges.put(val - left, sum);
        if (right > 0) ranges.put(val + right, sum);
        if (left == 0 || right == 0) ranges.put(val, sum); // remove middle val if it's not boundary to speed up getInt()
    }

    // O(NlogN) time
    public List<Interval> getIntervals() {
        List<Interval> ret = new ArrayList<>();
        List<Integer> keys = new ArrayList<>(ranges.keySet());
        Collections.sort(keys);

        int last = Integer.MIN_VALUE;
        for (int left : keys) {
            int size = ranges.get(left);
            if (last < left) {
                ret.add(new Interval(left, left + size - 1));
                last = left + size - 1;
            }
        }
        return ret;
    }

}
