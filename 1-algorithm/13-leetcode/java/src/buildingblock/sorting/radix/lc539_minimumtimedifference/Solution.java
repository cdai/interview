package buildingblock.sorting.radix.lc539_minimumtimedifference;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Given a list of 24-hour clock time points in "Hour:Minutes" format,
 * find the minimum minutes difference between any two time points in the list.
 */
public class Solution {

    @Test
    public void test() {
        Assert.assertEquals(1, findMinDifference(Arrays.asList("23:59", "00:00")));
    }

    public int findMinDifference(List<String> timePoints) {
        int n = timePoints.size();
        int[] ts = new int[n];
        for (int i = 0; i < n; i++) { // Convert date to minutes format
            String[] hhmm = timePoints.get(i).split(":");
            ts[i] = Integer.valueOf(hhmm[0]) * 60 + Integer.valueOf(hhmm[1]);
        }

        for (int d = 0; d < 4; d++) { // Perform radix sorting
            ts = countingSort(ts, d);
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) { // Compare adjacent time
            min = Math.min(min, ts[i] - ts[i - 1]);
        }
        min = Math.min(min, 1440 - (ts[n - 1] - ts[0]));
        return min;
    }

    private int[] sort(int[] ts) {
        for (int d = 0; d < 4; d++) {
            ts = countingSort(ts, d);
        }
        return ts;
    }

    private int[] countingSort(int[] ts, int d) {
        // Count number of occurance of each digit
        int[] cnt = new int[10];
        for (int t : ts) {
            for (int i = 0; i < d; i++) t /= 10; // Get digit value
            cnt[t % 10]++;
        }

        // Add up number of occurance into position in final output
        for (int i = 1; i < cnt.length; i++) cnt[i] += cnt[i - 1];

        // Place each number on its position
        int n = ts.length;
        int[] ret = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int t = ts[i];
            for (int j = 0; j < d; j++) t /= 10;
            ret[--cnt[t % 10]] = ts[i]; // off-by-one
        }
        return ret;
    }

    // NlogN quick sort
    private void sort_my(int[] ts) {
        Arrays.sort(ts);
    }

}

