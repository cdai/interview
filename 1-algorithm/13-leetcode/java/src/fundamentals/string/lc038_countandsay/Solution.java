package fundamentals.string.lc038_countandsay;

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
        if (n <= 1) {
            return "1";
        }

        char[] pre = countAndSay(n-1).toCharArray();
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
