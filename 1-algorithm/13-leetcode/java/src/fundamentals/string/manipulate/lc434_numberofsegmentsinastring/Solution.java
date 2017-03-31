package fundamentals.string.manipulate.lc434_numberofsegmentsinastring;

/**
 * Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.
 */
public class Solution {

    // O(N) time without flag variable
    public int countSegments(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ' && (i == 0 || s.charAt(i - 1) == ' ')) {
                cnt++;
            }
        }
        return cnt;
    }

    // Must check empty and trim, otherwise ""->1, "   aaa"->2
    public int countSegments_oneliner(String s) {
        s = s.trim();
        if (s.isEmpty()) return 0;
        return s.split("\\s+").length;
    }

    public int countSegments_my(String s) {
        boolean inWord = false;
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                if (inWord) inWord = false;
            } else {
                if (!inWord) {
                    cnt++;
                    inWord = true;
                }
            }
        }
        return cnt;
    }

}
