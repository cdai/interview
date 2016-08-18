package buildingblock.sorting.bucket.lc164_maximumgap;

/**
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 * Try to solve it in linear time/space. Return 0 if the array contains less than 2 elements.
 * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 */
public class Solution {

    // My 2AC: solution from programcreek
    public int maximumGap(int[] nums) {
        // 1.Get min and max to decide boundary per bucket
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        // 2.Create buckets
        Bucket[] buckets = new Bucket[nums.length];
        for (int i = 0; i < nums.length; i++) {
            buckets[i] = new Bucket();
        }

        // 3.Put numbers into proper bucket
        // Note: "The largest gap can not be smaller than (max-min)/(N-1),
        // so if we make the buckets smaller than this number,
        // any gaps within the same bucket is not the amount we are looking for,
        // so we are safe to look only for the inter-bucket gaps."
        int range = (int) Math.ceil((double) (max - min) / (nums.length - 1));
        if (range == 0) {   // error: eg.[1,1,1]
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            int idx = (nums[i] - min) / range;
            buckets[idx].low = Math.min(buckets[idx].low, nums[i]);
            buckets[idx].high = Math.max(buckets[idx].high, nums[i]);
        }

        // 4.Get to search max gap inter-bucket
        Bucket prev = null;
        int maxGap = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i].high < 0) {
                continue;
            }
            if (prev != null) {
                maxGap = Math.max(maxGap, buckets[i].low - prev.high);
            }
            prev = buckets[i];
        }
        return maxGap;
    }

    private static class Bucket {
        int low = Integer.MAX_VALUE;
        int high = -1;      // Safe flag since num must be non-negative
    }

}
