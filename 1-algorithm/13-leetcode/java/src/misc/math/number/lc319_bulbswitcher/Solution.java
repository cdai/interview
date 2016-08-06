package misc.math.number.lc319_bulbswitcher;

/**
 * There are n bulbs that are initially off. You first turn on all the bulbs.
 * Then, you turn off every second bulb.
 * On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on).
 * For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb.
 * Find how many bulbs are on after n rounds.
 * Example: Given n = 3.
 *  At first, the three bulbs are [off, off, off].
 *  After first round, the three bulbs are [on, on, on].
 *  After second round, the three bulbs are [on, off, on].
 *  After third round, the three bulbs are [on, off, off].
 * So you should return 1, because there is only one bulb is on.
 */
public class Solution {

    public int bulbSwitch(int n) {
        // 1.Pattern:
        //  12: (1,12),(2,6),(3,4) bulb will be off (exclude 1)
        //  16: (1,16),(2,8),(4,4) bulb will be on due to round 4
        // 2.Sqrt=number of sqrt. Sqrt(36)=6 (1,4,9,16,25,36) just the answer!
        return (int) Math.sqrt(n);
    }

    public int bulbSwitch2(int n) {
        if (n <= 0) {
            return 0;
        }

        int bulbOn = 1;
        for (int i = 2; i < n; i++) {
            if (isBulbOn(i)) {
                bulbOn++;
            }
        }
        return isBulbOn(n) ? bulbOn + 1 : bulbOn;
    }

    private boolean isBulbOn(int n) { // count i itself = even toggles which means bulb is on
        return factorCount(n) % 2 == 1;
    }

    private int factorCount(int n) {
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                cnt++;
            }
        }
        return cnt;
    }

}
