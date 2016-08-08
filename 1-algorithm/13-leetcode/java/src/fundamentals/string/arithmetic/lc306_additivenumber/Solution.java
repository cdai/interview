package fundamentals.string.arithmetic.lc306_additivenumber;

/**
 * Additive number is a string whose digits can form additive sequence.
 * A valid additive sequence should contain at least three numbers.
 * Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
 * For example: "112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8. 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * "199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199. 1 + 99 = 100, 99 + 100 = 199.
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 * Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
 * Follow up: How would you handle overflow for very large input integers?
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().isAdditiveNumber("211738"));
    }

    public boolean isAdditiveNumber(String num) {
        // Generate all possible first two number, meanwhile make sure no leading 0 and there is a third number
        for (int i = 1; i <= num.length() - 2; i++) {
            String first = num.substring(0, i);
            if (first.startsWith("0") && !first.endsWith("0")) {
                continue;
            }
            for (int j = i + 1; j <= num.length() - 1; j++) {
                String second = num.substring(i, j);
                String remaining = num.substring(j);

                if ((second.length() > 1 && second.startsWith("0")) ||          // error2: could be '0' but '0xxx' is illegal
                        (remaining.startsWith("0")) && !remaining.endsWith("0")) {  // error3: remaining could be '0000' but no '000x'
                    continue;
                }

                StringBuilder str = new StringBuilder();
                String prev = first, cur = second;          // error4: do NOT modify outer variable "first", use another tmp variable
                while (str.length() < remaining.length()) {
                    String third = add(prev, cur);
                    str.append(third);
                    prev = cur;
                    cur = third;
                }

                if (str.toString().equals(remaining)) {     // error1: must str.toString() before using equals for StringBuilder
                    return true;
                }
            }
        }
        return false;
    }

    private String add(String s1, String s2) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        for (int i = s1.length() - 1, j = s2.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int n1 = (i >= 0) ? s1.charAt(i) - '0' : 0;
            int n2 = (j >= 0) ? s2.charAt(j) - '0' : 0;
            int sum = n1 + n2 + carry;
            carry = sum / 10;
            result.insert(0, sum % 10);
        }
        if (carry > 0) {
            result.insert(0, carry);
        }
        return result.toString();
    }

}
