package buildingblock.searching.lc275_hindex2;

/**
 * Follow up for H-Index: What if the citations array is sorted in ascending order?
 * Could you optimize your algorithm?
 * Hint: Expected runtime complexity is in O(log n) and the input is sorted.
 */
public class Solution {

    public int hIndex(int[] citations) {
        int n = citations.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (n - mid > citations[mid]) { // index (papers) is too big
                low = mid + 1;
            } else {                        // citation is too big
                high = mid - 1;
            }
        }
        return n - low;
    }

}
