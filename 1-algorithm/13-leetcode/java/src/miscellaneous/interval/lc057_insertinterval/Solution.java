package miscellaneous.interval.lc057_insertinterval;

import miscellaneous.interval.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 * Example 1: Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 * Example 2: Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 */
public class Solution {

    public List<Interval> insert(List<Interval> intervals, Interval newi) {
        List<Interval> result = new ArrayList<>();

        for (Interval i : intervals) {
            if (newi.end < i.start || i.end < newi.start) {
                result.add(i);
            } else {
                newi = new Interval(Math.min(i.start, newi.start),
                        Math.max(i.end, newi.end));
            }
        }

        int i = 0;      // error: must insert in order
        for ( ; i < result.size(); i++) {
            if (newi.end < result.get(i).start) {
                break;
            }
        }
        result.add(i, newi);
        return result;
    }

}
