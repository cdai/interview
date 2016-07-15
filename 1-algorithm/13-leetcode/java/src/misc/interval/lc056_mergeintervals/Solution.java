package misc.interval.lc056_mergeintervals;

import misc.interval.Interval;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * For example, Given [1,3],[2,6],[8,10],[15,18], return [1,6],[8,10],[15,18].
 */
public class Solution {

    public List<Interval> merge(List<Interval> intervals) {
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
