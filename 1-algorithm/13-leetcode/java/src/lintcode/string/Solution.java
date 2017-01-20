package lintcode.string;

/**
 */
public class Solution {

    // 8-Rotate String
    public void rotateString(char[] str, int offset) {
        if (str.length == 0) return;
        int n = str.length;
        offset %= n;
        swap(str, 0, n - 1);
        swap(str, 0, offset - 1);
        swap(str, offset, n - 1);
    }

    private void swap(char[] str, int i, int j) {
        for (; i < j; i++, j--) {
            char tmp = str[i];
            str[i] = str[j];
            str[j] = tmp;
        }
    }

    // 157-Unique Character
    public boolean isUnique(String str) {
        if (str.length() > 256) return false;

        boolean[] exist = new boolean[256];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (exist[c]) return false;
            exist[c] = true;
        }
        return true;
    }

    // 397-Longest Increasing Continuous Subsequence
    public int longestIncreasingContinuousSubsequence(int[] A) {
        if (A.length == 0) return 0;
        int maxinc = 1, maxdec = 1;
        for (int i = 1, inc = 1, dec = 1; i < A.length; i++) {
            if (A[i - 1] == A[i]) inc = dec = 1;
            else if (A[i - 1] < A[i]) {
                inc++;
                dec = 1;
            } else {
                dec++;
                inc = 1;
            }
            maxinc = Math.max(maxinc, inc);
            maxdec = Math.max(maxdec, dec);
        }
        return Math.max(maxinc, maxdec);
    }

}
