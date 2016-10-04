package advanced.greedy.lc252_meetingrooms;

import miscellaneous.interval.Interval;

import java.util.Arrays;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...]
 * (si < ei), determine if a person could attend all meetings.
 * For example, Given [[0, 30],[5, 10],[15, 20]], return false.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().canAttendMeetings(new Interval[]{
                new Interval(0, 30),
                new Interval(5, 10),
                new Interval(15, 20)
        }));
        System.out.println(new Solution().canAttendMeetings(new Interval[]{
                new Interval(0, 3),
                new Interval(5, 10),
                new Interval(15, 20)
        }));
    }

    public boolean canAttendMeetings(Interval[] meetings) {
        Arrays.sort(meetings, (m1, m2) -> Integer.compare(m1.end, m2.end));
        Interval prev = null;
        for (Interval m : meetings) {
            if (prev != null && prev.end > m.start) return false;
            prev = m;
        }
        return true;
    }

    public boolean canAttendMeetings2(Interval[] intervals) {
        if (intervals.length == 0) return false; // Ask interviewer!
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1.end, i2.end));
        for (int i = 1; i < intervals.length; i++)
            if (intervals[i - 1].end > intervals[i].start)
                return false;
        return true;
    }

}
