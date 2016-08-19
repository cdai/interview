package miscellaneous.math.pattern.lc365_waterandjugproblem;

/**
 * You are given two jugs with capacities x and y litres.
 * There is an infinite amount of water supply available.
 * You need to determine whether it is possible to measure exactly z litres using these two jugs.
 * If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.
 * Operations allowed:
 *  Fill any of the jugs completely with water.
 *  Empty any of the jugs.
 *  Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.
 * Example 1: (From the famous "Die Hard" example)
 * Input: x = 3, y = 5, z = 4. Output: True.
 * Example 2: Input: x = 2, y = 6, z = 5. Output: False.
 */
public class Solution {

    // Model: z = m * x + n * y (Huge container, use two cups to fill (m/n positive) or empty(negative)) a jug of x or y
    // Then according to Bézout's identity, d = GCD(x, y), so it's true if z is multiple of d. Okay, kill me now...
    public boolean canMeasureWater(int x, int y, int z) {
        return (z == 0) || (x + y >= z && z % gcd(x, y) == 0);
    }

    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }

    // Too complex, cannot deal with x=0 or y=0...
    public boolean canMeasureWater2(int x, int y, int z) {
        // Make sure x < y
        if (x > y) {
            int tmp = x;
            x = y;
            y = tmp;
        }

        // Increment x
        int lit = 0;
        while (lit <= y) {
            if (lit == z) {
                return true;
            }
            lit += x;
        }
        if (lit == z) { // Now lit means both x and y fill water
            return true;
        }

        // use margin: n * x - y
        lit -= y;
        if (lit != 0) {
            while (lit <= y) {
                if (lit == z) {
                    return true;
                }
                lit += x;
            }
        }

        // use margin: y - x
        lit = y - x;
        if (lit != 0) {
            if (z == lit + y) {
                return true;
            }
            while (lit <= y) {
                if (lit == z) {
                    return true;
                }
                lit += x;
            }
        }

        // decrement y
        lit = y;
        while (lit >= 0) {
            if (lit == z) {
                return true;
            }
            lit -= x;
        }
        return (x + y == z);
    }

}
