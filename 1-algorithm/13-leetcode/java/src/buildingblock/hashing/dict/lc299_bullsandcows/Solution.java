package buildingblock.hashing.dict.lc299_bullsandcows;

import java.util.HashMap;
import java.util.Map;

/**
 * You are playing the following Bulls and Cows game with your friend:
 * You write down a number and ask your friend to guess what the number is.
 * Each time your friend makes a guess, you provide a hint that indicates
 * how many digits in said guess match your secret number exactly in both digit and position (called "bulls")
 * and how many digits match the secret number but locate in the wrong position (called "cows").
 * Your friend will use successive guesses and hints to eventually derive the secret number.
 * For example:
 *  Secret number:  "1807". Friend's guess: "7810".
 *  Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
 * Write a function to return a hint according to the secret number and friend's guess,
 * use A to indicate the bulls and B to indicate the cows. In the above example, your function should return "1A3B".
 * Please note that both secret number and friend's guess may contain duplicate digits,
 * for example: Secret number:  "1123". Friend's guess: "0111".
 *  In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow,
 *  and your function should return "1A1B".
 * You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
 */
public class Solution {

    // My 2nd: O(N) time, O(1) space
    public String getHint(String secret, String guess) {
        int[] nums = new int[10];

        int bulls = 0, cows = 0;
        for (int i = 0; i < secret.length(); i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';
            if (s == g) {
                bulls++;
            } else {
                if (nums[s]++ < 0) { // Negative means there're guess number here
                    cows++;
                }
                if (nums[g]-- > 0) { // Negative means there're secret number here
                    cows++;
                }
            }
        }
        return bulls + "A" + cows + "B";
    }

    // My 1st
    // When dict is limited, using array as dict is much elegant and fast!!!
    public String getHint1(String secret, String guess) {
        int[] letters = new int[10];
        int bulls = 0;
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else {
                letters[secret.charAt(i) - '0']++;
            }
        }

        int cows = 0;
        for (int i = 0; i < guess.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                continue;
            }
            int idx = guess.charAt(i) - '0';
            if (letters[idx] > 0) {
                cows++;
                letters[idx]--;
            }
        }
        return bulls + "A" + cows + "B";
    }

    // Hashmap
    public String getHint2(String secret, String guess) {
        if (secret.isEmpty() || guess.isEmpty() || secret.length() != guess.length()) {
            return "";
        }

        int bulls = 0;
        Map<Character,Integer> dict = new HashMap<>();
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else {
                Integer cnt = dict.get(secret.charAt(i));
                if (cnt == null) {
                    cnt = 0;
                }
                dict.put(secret.charAt(i), cnt + 1);
            }
        }

        int cows = 0;
        for (int i = 0; i < guess.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                continue;
            }

            Integer cnt = dict.get(guess.charAt(i));
            if (cnt != null) {
                cows++;
                if (cnt == 1) {
                    dict.remove(guess.charAt(i));
                } else {
                    dict.put(guess.charAt(i), cnt - 1);
                }
            }
        }
        return bulls + "A" + cows + "B";
    }

}
