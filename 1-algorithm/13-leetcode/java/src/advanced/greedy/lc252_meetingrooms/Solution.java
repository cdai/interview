package advanced.greedy.lc252_meetingrooms;

import miscellaneous.interval.Interval;

import java.util.Arrays;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...]
 * (si < ei), determine if a person could attend all meetings.
 * For example, Given [[0, 30],[5, 10],[15, 20]], return false.
 */
public class Solution {

    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals.length == 0) return false; // Ask interviewer!
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1.end, i2.end));
        for (int i = 1; i < intervals.length; i++)
            if (intervals[i - 1].end > intervals[i].start)
                return false;
        return true;
    }

}
