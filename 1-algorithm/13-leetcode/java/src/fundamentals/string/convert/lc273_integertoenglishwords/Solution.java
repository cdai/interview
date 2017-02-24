package fundamentals.string.convert.lc273_integertoenglishwords;

/**
 * Convert a non-negative integer to its english words representation.
 * Given input is guaranteed to be less than 231 - 1.
 * For example,
 *  123 -> "One Hundred Twenty Three"
 *  12345 -> "Twelve Thousand Three Hundred Forty Five"
 *  1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * Hint: Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
 *  Group the number by thousands (3 digits). You can write a helper function that takes a number less than 1000 and convert just that chunk to words.
 *  There are many edge cases. What are some good test cases? Does your code work with input such as 0? Or 1000010?
 *  (middle chunk is zero and should not be printed out)
 */
public class Solution {

    // Merge to one variable LESS_THAN_20 to simplify branch
    private static final String[] LESS20 =
            " One Two Three Four Five Six Seven Eight Nine Ten Eleven Twelve Thirteen Fourteen Fifteen Sixteen Seventeen Eighteen Nineteen".split(" ");
    private static final String[] TENS = " Ten Twenty Thirty Forty Fifty Sixty Seventy Eighty Ninety".split(" ");

    public String numberToWords(int num) {
        return num == 0 ? "Zero" : num2Word(num);
    }

    // Skill: 1) 3 base cases: 10,100,1000 then recursion 2) trim before return, check zero at last
    // Wonderful solution from leetcode discuss. Beat 97.90!!!
    private String num2Word(int num) {
        String result = "";
        if (num < 20) result = LESS20[num];
        else if (num < 100) result = TENS[num / 10] + " " + LESS20[num % 10];
        else if (num < 1000) result = LESS20[num / 100] + " Hundred " + num2Word(num % 100);
        else if (num < 1000000) result = num2Word(num / 1000) + " Thousand " + num2Word(num % 1000);
        else if (num < 1000000000) result = num2Word(num / 1000000) + " Million " + num2Word(num % 1000000);
        else result = num2Word(num / 1000000000) + " Billion " + num2Word(num % 1000000000);
        return result.trim();
    }

    // In Chinese, 10000. In English, 1000.
    public String numberToWords2(int num) {
        if (num == 0) {             // edge case-1: zero
            return "Zero";
        }

        String[] less20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
                "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] tens = { "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
        String[] thousands = { "", "Thousand", "Million", "Billion" };

        StringBuilder result = new StringBuilder();
        for (int i = 0; num > 0; i++) {
            int less1k = num % 1000;
            if (less1k > 0) {       // edge case-2: middle zero
                result.insert(0, getWord(less20, tens, less1k) + thousands[i] + " ");
            }
            num /= 1000;
        }
        return result.toString().trim();
    }

    // I thought it in the hard way, "Thousands/Hundreds", "And Five". So we only concern " "
    private String getWord(String[] less20, String[] tens, int less1k) {
        StringBuilder word = new StringBuilder();
        int d100 = less1k / 100;
        if (d100 > 0) {
            word.append(less20[d100]).append(" Hundred ");
            less1k -= d100 * 100;
        }

        int d10 = less1k / 10;
        int d1 = less1k % 10;
        if (d10 == 1 && d1 != 0) {
            word.append(less20[less1k % 100]).append(" ");
            return word.toString();
        }

        if (d10 > 0) {
            word.append(tens[d10]).append(" ");
        }

        if (d1 > 0) {
            word.append(less20[d1]).append(" ");
        }
        return word.toString();
    }

}
