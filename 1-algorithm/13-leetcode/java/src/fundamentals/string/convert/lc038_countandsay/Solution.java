package fundamentals.string.convert.lc038_countandsay;

/**
 * The count-and-say sequence is the sequence of integers beginning as follows: 1, 11, 21, 1211, 111221, ...
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth sequence.
 * Note: The sequence of integers will be represented as a string.
 */
public class Solution {

    public String countAndSay(int n) {
        if (n <= 0) return "";
        String s = "1";
        while (n-- > 1) { /* invariant: s is nth */
            StringBuilder next = new StringBuilder(); /* invariant: contain cnt-say before cur */
            for (int i = 1, cnt = 1; i <= s.length(); i++, cnt++) {
                if (i == s.length() || s.charAt(i) != s.charAt(i - 1)) {
                    next.append(cnt).append(s.charAt(i - 1));
                    cnt = 0;
                }
            }
            s = next.toString();
        }
        return s;
    }

    // My 3AC: O(N^2) time
    public String countAndSay3(int n) {
        String ret = "1";
        while (n-- > 1) {
            StringBuilder tmp = new StringBuilder();
            int cnt = 1;
            for (int i = 1; i < ret.length(); i++) {
                if (ret.charAt(i) == ret.charAt(i - 1)) cnt++;
                else {
                    tmp.append(cnt).append(ret.charAt(i - 1));
                    cnt = 1;
                }
            }
            tmp.append(cnt).append(ret.charAt(ret.length() - 1));
            ret = tmp.toString();
        }
        return ret;
    }

    // My 2AC. O(N^2) time
    public String countAndSay2(int n) {
        StringBuilder result = new StringBuilder();
        result.append("1");
        while (--n > 0) {   // error1: n starts from 1th
            // Count and say: invariant is [0,i] char[i] is being counting or said
            int count = 1;
            for (int i = 0; i < result.length(); i++) {
                if (i < result.length() - 1 && result.charAt(i) == result.charAt(i + 1)) { // error2: forget last batch
                    count++;
                } else {
                    result.append(count).append(result.charAt(i));
                    count = 1;
                }
            }
            // Delete chars of last round
            result.delete(0, result.length());
        }
        return result.toString();
    }

    // My 1AC
    public String countAndSay1(int n) {
        if (n <= 1) {
            return "1";
        }

        char[] pre = countAndSay1(n-1).toCharArray();
        String str = "";

        // Invariant:
        //  last: last diff, cnt: count of same
        //  cur == last, ret: count-and-say(num before last)
        //  cur != last, ret: count-and-say(num before cur)
        char last = pre[0];
        int cnt = 1;
        for (int i = 1; i < pre.length; i++) {
            if (pre[i] == last) {
                cnt++;
            } else {
                str = str + cnt + last;
                last = pre[i];
                cnt = 1;
            }
        }
        str = str + cnt + last; // use integer causing overflow and forgot last batch
        return str;
    }

}
