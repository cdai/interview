package miscellaneous.math.arithmetic.lc504_base7;

/**
 * Given an integer, return its base 7 string representation.
 */
public class Solution {

    public String convertToBase7(int num) {
        return Integer.toString(num, 7);
    }

    public String convertToBase7_recursion(int num) {
        if (num < 0) return "-" + convertToBase7(-num);
        if (num < 7) return String.valueOf(num);
        return convertToBase7(num / 7) + (num % 7);
    }

    public String convertToBase7_my(int num) {
        if (num == 0) return "0";
        StringBuilder ret = new StringBuilder();
        for (int i = Math.abs(num); i > 0; i /= 7) {
            ret.append(i % 7);
        }
        ret.append(num < 0 ? "-" : "");
        return ret.reverse().toString();
    }

}
