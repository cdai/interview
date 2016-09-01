package miscellaneous.interval.lc056_mergeintervals;

import miscellaneous.interval.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * For example, Given [1,3],[2,6],[8,10],[15,18], return [1,6],[8,10],[15,18].
 */
public class Solution {

    // My 2AC: Avoid inplace remove and get(i)
    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, (i1, i2) -> (Integer.compare(i1.start, i2.start)));
        List<Interval> result = new ArrayList<>();
        for (Interval interval : intervals) {
            if (!result.isEmpty() && result.get(result.size() - 1).end >= interval.start) {
                Interval prev = result.get(result.size() - 1);
                prev.start = Math.min(prev.start, interval.start);
                prev.end = Math.max(prev.end, interval.end);
            } else {
                result.add(interval);
            }
        }
        return result;
    }

    // Sort O(NlogN): very slow due to lambda? and many get(i) and remove(i+1)
    public List<Interval> merge_inplace(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>(intervals);
        Collections.sort(result, (i1, i2) -> (Integer.compare(i1.start, i2.start))); // start-start, not start-end compare
        for (int i = 0; i < result.size() - 1; ) {
            if (result.get(i).end >= result.get(i + 1).start) {
                result.get(i).start = Math.min(result.get(i).start, result.get(i + 1).start);
                result.get(i).end = Math.max(result.get(i).end, result.get(i + 1).end);
                result.remove(i + 1);
            } else i++;
        }
        return result;
    }

    // O(N^2) but exteremly fast
    public List<Interval> merge2(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();
        for (Interval interval : intervals)
            doMergeOne(result, interval);
        return result;
    }

    // As merging proceed, intervals are in order naturally
    private void doMergeOne(List<Interval> merged, Interval interval) {
        int i = 0;
        for (; i < merged.size() && merged.get(i).end < interval.start; i++);
        while (i < merged.size() && merged.get(i).start <= interval.end) {
            interval = new Interval(Math.min(merged.get(i).start, interval.start),
                    Math.max(merged.get(i).end, interval.end));
            merged.remove(i);
        }
        merged.add(i, interval);
    }

    // My 1AC
    public List<Interval> merge1(List<Interval> intervals) {
        // O(n) Segments: positive for start, negative for end count at that point
        Map<Integer,Integer> segments = new TreeMap<>();
        for (Interval i : intervals) {
            Integer count = segments.get(i.start);
            segments.put(i.start, (count == null) ? 1 : (count + 1));
            count = segments.get(i.end);
            segments.put(i.end, (count == null) ? -1 : (count - 1));
        }

        // O(n) iterate: <<<>><>> = one interval...
        List<Interval> result = new ArrayList<>();
        Integer opened = null, start = 0;
        for (Map.Entry<Integer,Integer> e : segments.entrySet()) {
            if (opened == null) {
                if (e.getValue() == 0) {         // error1: could be [0,0]
                    result.add(new Interval(e.getKey(), e.getKey()));
                } else {
                    start = e.getKey();
                    opened = e.getValue();
                }
                continue;
            }

            opened += e.getValue();
            if (opened == 0) {
                result.add(new Interval(start, e.getKey()));
                opened = null;
            }
        }
        return result;
    }

}
