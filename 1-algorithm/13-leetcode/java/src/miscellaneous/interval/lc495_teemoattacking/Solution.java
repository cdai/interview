package miscellaneous.interval.lc495_teemoattacking;

/**
 * In LLP world, there is a hero called Teemo and his attacking can make his enemy Ashe be in poisoned condition.
 * Now, given the Teemo's attacking ascending time series towards Ashe and the poisoning time duration per Teemo's attacking,
 * you need to output the total time that Ashe is in poisoned condition.
 */
public class Solution {

    // Maintain invariant: duration of poison condition before ts[i] are accumulated on total
    public int findPoisonedDuration(int[] ts, int duration) {
        if (ts.length == 0) return 0;
        int total = 0, reach = ts[0] + duration; // how long poison condition will last by now
        for (int i = 1; i < ts.length; i++) {
            total += Math.min(ts[i], reach) - ts[i - 1];
            reach = ts[i] + duration;
        }
        return total + duration; // don't forget last duration
    }

}
