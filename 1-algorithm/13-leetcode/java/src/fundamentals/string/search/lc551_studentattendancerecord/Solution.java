package fundamentals.string.search.lc551_studentattendancerecord;

/**
 * You are given a string representing an attendance record for a student. The record only contains the following three characters:
 *
 *  'A' : Absent.
 *  'L' : Late.
 *  'P' : Present.
 *
 * A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
 * You need to return whether the student could be rewarded according to his attendance record.
 */
public class Solution {

    public boolean checkRecord(String s) {
        int absent = 0, late = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A' && ++absent > 1) return false;
            if (s.charAt(i) == 'L') {
                if (++late > 2) return false;
            } else late = 0;
        }
        return true;
    }

}
