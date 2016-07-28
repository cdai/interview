package buildingblock.sorting.merge.lc179_largestnumber;

/**
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * Note: The result may be very large, so you need to return a string instead of an integer.
 */
public class Solution {

    public String largestNumber(int[] nums) {
        if (nums.length == 0) {
            return "0";
        }
        sort(nums, 0, nums.length - 1);
        return join(nums);
    }

    // Rely on mergeSort framework
    private void sort(int[] nums, int low, int high) { // Simplify-1: use Arrays.sort(nums, new Comparator...)
        if (low >= high) {
            return;
        }

        int mid = (low + high) / 2;
        sort(nums, low, mid);
        sort(nums, mid + 1, high);

        int[] tmp = new int[high - low + 1];
        for (int i = low, j = mid + 1, k = 0; (i <= mid) || (j <= high); k++) {
            int a = (i <= mid) ? nums[i] : -1;
            int b = (j <= high) ? nums[j] : -1;
            tmp[k] = (compare(a, b) > 0) ? nums[i++] : nums[j++];
        }
        System.arraycopy(tmp, 0, nums, low, tmp.length);
    }

    // A > B, return 1
    private int compare(int a, int b) { // Simplify-2: use ab.compare(ba), it works since a and b share same length
        String ab = a + "" + b;
        String ba = b + "" + a;
        int i = 0;
        for ( ; (i < ab.length()) && (ab.charAt(i) == ba.charAt(i)); i++);

        if (i == ab.length()) {
            return 0;
        }
        return ab.charAt(i) - ba.charAt(i);
    }

    private String join(int[] nums) {
        if (nums[0] == 0) {     // error: return 0 if all nums are 0
            return "0";
        }

        StringBuilder result = new StringBuilder();
        for (int n : nums) {
            result.append(n);
        }
        return result.toString();
    }

}
