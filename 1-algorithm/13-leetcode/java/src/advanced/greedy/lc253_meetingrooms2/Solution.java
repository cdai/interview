package advanced.greedy.lc253_meetingrooms2;

import miscellaneous.interval.Interval;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.
 * For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
 */
public class Solution {

    public int minMeetingRooms(Interval[] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
        Queue<Interval> q = new PriorityQueue<>((a, b) -> Integer.compare(a.end, b.end));
        int rooms = 0;
        for (Interval in : intervals) {
            while (!q.isEmpty() && in.start >= q.peek().end)
                q.poll();
            q.offer(in);
            rooms = Math.max(rooms, q.size());
        }
        return rooms;
    }

    // My thought...
    public int minMeetingRooms1(Interval[] intervals) {
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
