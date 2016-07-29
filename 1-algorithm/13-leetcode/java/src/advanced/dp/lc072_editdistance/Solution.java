package advanced.dp.lc072_editdistance;

/**
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2.
 * (each operation is counted as 1 step.)
 * You have the following 3 operations permitted on a word:
 *  a) Insert a character
 *  b) Delete a character
 *  c) Replace a character
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().minDistance("apple", "are"));
    }

    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }

        int len1 = word1.length();
        int len2 = word2.length();
        int[][] ed = new int[len1 + 1][len2 + 1];

        // Boundary is special: either ed(i-1,j) or ed(i,j-1), so it must increment by 1
        ed[0][0] = 0;   // error2: [0,0] is always 0 since matrix is m+1*n+1 and ed(i,j) means cost from [0,i-1] => [0,j-1]
        for (int i = 1; i <= len1; i++) { // error1: init only boundary or init all to MAX_INT, wrong if leave it to 0
            ed[i][0] = i;
        }
        for (int j = 1; j <= len2; j++) {
            ed[0][j] = j;
        }

        // Boundary is initialized, then just calculate safely
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                char c1 = word1.charAt(i - 1);
                char c2 = word2.charAt(j - 1);
                ed[i][j] = Math.min(Math.min(ed[i - 1][j] + 1, ed[i][j - 1] + 1),
                        ed[i - 1][j - 1] + ((c1 == c2) ? 0 : 1));
            }
        }
        return ed[len1][len2];
    }

}
