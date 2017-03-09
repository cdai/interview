package fundamentals.string.manipulate.lc520_detectcapital;

/**
 * All letters in this word are capitals, like "USA".
 * All letters in this word are not capitals, like "leetcode".
 * Only the first letter in this word is capital if it has more than one letter, like "Google".
 */
public class Solution {

    public boolean detectCapitalUse(String word) {
        return word.equals(word.toUpperCase()) ||
                word.equals(word.toLowerCase()) ||
                (Character.isUpperCase(word.charAt(0)) && word.substring(1).equals(word.substring(1).toLowerCase()));
    }

    public boolean detectCapitalUse_my(String word) {
        if (word.isEmpty()) return true;
        if (Character.isUpperCase(word.charAt(0))) {
            word = word.substring(1);
        }

        if (word.isEmpty()) return true;
        if (Character.isUpperCase(word.charAt(0))) {
            for (int i = 1; i < word.length(); i++) {
                if (Character.isLowerCase(word.charAt(i))) return false;
            }
        } else {
            for (int i = 1; i < word.length(); i++) {
                if (Character.isUpperCase(word.charAt(i))) return false;
            }
        }
        return true;
    }

}
