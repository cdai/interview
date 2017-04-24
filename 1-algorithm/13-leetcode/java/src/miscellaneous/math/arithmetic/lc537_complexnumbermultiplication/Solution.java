package miscellaneous.math.arithmetic.lc537_complexnumbermultiplication;

/**
 * Given two strings representing two complex numbers.
 * You need to return a string representing their multiplication. Note i2 = -1 according to the definition.
 */
public class Solution {

    public String complexNumberMultiply(String a, String b) {
        int p1 = a.indexOf('+'), p2 = b.indexOf('+'), n1 = a.length() - 1, n2 = b.length() - 1;
        int x1 = Integer.valueOf(a.substring(0, p1)), y1 = Integer.valueOf(a.substring(p1 + 1, n1));
        int x2 = Integer.valueOf(b.substring(0, p2)), y2 = Integer.valueOf(b.substring(p2 + 1, n2));
        return (x1 * x2 - y1 * y2) + "+" + (x1 * y2 + x2 * y1) + "i";
    }

}
