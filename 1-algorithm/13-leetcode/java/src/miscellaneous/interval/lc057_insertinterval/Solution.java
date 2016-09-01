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

    public List<Interval> insert_inplace(List<Interval> intervals, Interval newInterval) {
        int i = 0;
        for (; i < intervals.size() && intervals.get(i).end < newInterval.start; i++);
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) { // DON'T move i
            newInterval = new Interval(Math.min(intervals.get(i).start, newInterval.start),
                    Math.max(intervals.get(i).end, newInterval.end));
            intervals.remove(i);
        }
        intervals.add(i, newInterval); // Insert ith
        return intervals;
    }

    // My 2AC: solution from leetcode discuss. O(N)
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        int i = 0;
        for (; i < intervals.size() && intervals.get(i).end < newInterval.start; i++)
            result.add(intervals.get(i));

        for (; i < intervals.size() && intervals.get(i).start <= newInterval.end; i++)
            newInterval = new Interval(Math.min(intervals.get(i).start, newInterval.start),
                    Math.max(intervals.get(i).end, newInterval.end));

        result.add(newInterval);
        result.addAll(intervals.subList(i, intervals.size()));
        return result;
    }

    // Very hard to locate newInterval position and then merge in one pass...
    public List<Interval> insert_unclean(List<Interval> intervals, Interval newInterval) {
        int startIdx = -1, endIdx = -1;
        for (int i = 0; i < intervals.size(); i++) {
            if (isIn(intervals.get(i), newInterval.start)) startIdx = i;
            if (isIn(intervals.get(i), newInterval.end)) endIdx = i;
        }

        List<Interval> result = new ArrayList<>();
        if (startIdx == -1 && endIdx == -1);
        else if (startIdx != -1 && endIdx == -1);
        else if (startIdx == -1 && endIdx != -1);
        else;
        return result;
    }

    private boolean isIn(Interval interval, int val) {
        return interval.start <= val && val <= interval.end;
    }

    // My 1AC
    public List<Interval> insert1(List<Interval> intervals, Interval newi) {
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
