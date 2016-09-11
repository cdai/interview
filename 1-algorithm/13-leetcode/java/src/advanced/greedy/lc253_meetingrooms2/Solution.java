package advanced.greedy.lc253_meetingrooms2;

import miscellaneous.interval.Interval;

import java.util.Arrays;

/**
 */
public class Solution {

    public int minMeetingRooms(Interval[] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1.end, i2.end));
        boolean[] used = new boolean[intervals.length];
        int cnt = 0, n = intervals.length;
        while (n > 0) {
            int i = 0;
            for (int j = 1; j < intervals.length; j++) {
                if (used[i] || intervals[i].end > intervals[j].start) continue;
                used[i] = true;
                n--;
                i = j;
            }
            used[i] = true;
            n--;
        }
        return cnt;
    }

}
