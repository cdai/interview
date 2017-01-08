package miscellaneous.interval.lc057_insertinterval;

import miscellaneous.interval.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 * Example 1: Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 * Example 2: Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<Interval> merge = sol.insert(Arrays.asList(
                new Interval(1, 2), new Interval(3, 5), new Interval(6, 7),
                new Interval(8, 10), new Interval(12, 16)
        ), new Interval(4, 9));
        for (Interval in : merge)
            System.out.printf("[%d, %d]\n", in.start, in.end);
    }

    // 1) Shallow vs Deep: Don't do deep clone unnecessarily. Copy new interval only when interval in ints are going to be changed
    // 2) Condition: no affect = (i.end < new.start), merge = (i.start <= m.end), no affect = (m.end < i.start)
    public List<Interval> insert(List<Interval> ints, Interval newin) {
        List<Interval> ret = new ArrayList<>();
        int i, n = ints.size();
        for (i = 0; i < n && ints.get(i).end < newin.start; i++) // newin definitely has no affect on end before its start
            ret.add(ints.get(i));

        Interval merge = new Interval(newin.start, newin.end); /* first interval end >= newin.start */
        for (; i < n && ints.get(i).start <= merge.end; i++) { // merge.start <= ints.get(i).end
            merge.start = Math.min(merge.start, ints.get(i).start);
            merge.end = Math.max(merge.end, ints.get(i).end);
        }
        ret.add(merge);
        ret.addAll(ints.subList(i, n)); /* all interval start > merge.end */
        return ret;
    }

    // My 3AC
    public List<Interval> insert3(List<Interval> intervals, Interval insert) {
        List<Interval> ret = new ArrayList<>();
        for (Interval cur : intervals) {
            if (cur.end >= insert.start) break; // Note: first cur overlap insert. insert.end >= cur.start wrong!
            ret.add(cur);
        }
        insert = new Interval(insert.start, insert.end);
        ret.add(insert);
        for (Interval cur : intervals.subList(ret.size() - 1, intervals.size())) {
            if (insert.end < cur.start) ret.add(cur);
            else {
                insert.start = Math.min(insert.start, cur.start);
                insert.end = Math.max(insert.end, cur.end);
            }
        }
        return ret;
    }

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
    public List<Interval> insert2(List<Interval> intervals, Interval newInterval) {
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
