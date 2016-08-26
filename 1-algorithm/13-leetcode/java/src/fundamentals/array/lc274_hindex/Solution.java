package fundamentals.array.lc274_hindex;

import java.util.Arrays;

/**
 * Given an array of citations (each citation is a non-negative integer) of a researcher,
 * write a function to compute the researcher's h-index.
 * According to the definition of h-index on Wikipedia:
 * "A scientist has index h if h of his/her N papers have at least h citations each,
 * and the other N − h papers have no more than h citations each."
 * For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and
 * each of them had received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each
 * and the remaining two with no more than 3 citations each, his h-index is 3.
 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
 */
public class Solution {

    // My 2AC: O(NlogN)
    // eg.   [0, 1, 3, 5, 6]
    // cites  0  1  3  5  6
    // papers 5  4  3  2  1
    // hindex 0  1  3  2  1
    // max = 3
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int max = 0;
        for (int i = 0; i < citations.length; i++) {
            int cites = citations[i];
            int papers = citations.length - i; // include itself since "at least h citations"
            int hindex = Math.min(cites, papers);
            max = Math.max(max, hindex);
        }
        return max;
    }

    public int hIndex1(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int paper = n - i;
            int cites = citations[i];
            int h = Math.min(paper, cites);
            max = Math.max(max, h);
        }
        return max;
    }

    public int hIndex2(int[] citations) {
        int n = citations.length;
        if (n == 0) {
            return 0;
        }

        Arrays.sort(citations);

        for (int i = n - 1; i >= 0; i--) {
            int h = n - i;
            int cites = citations[i];
            if (cites >= h) { // h papers have >= h citations
                return h;
            }
        }
        return n;
    }

}
