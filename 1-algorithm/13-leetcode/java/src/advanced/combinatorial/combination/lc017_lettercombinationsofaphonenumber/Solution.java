package advanced.combinatorial.combination.lc017_lettercombinationsofaphonenumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a digit string, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class Solution {

    // BFS solution from leetcode discuss
    // DFS and BFS are uniformed between Tree and Combinatorial algorithm!
    public List<String> letterCombinations(String digits) {
        final String[] map = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        Queue<String> queue = new LinkedList<>();
        if (!digits.isEmpty()) {
            queue.offer("");
        }

        // For tree, there're only two possibilities: left and right child.
        // For this problem, there're many according to digits first char
        while (!queue.isEmpty() && !digits.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String str = queue.poll();
                for (char c : map[digits.charAt(0) - '0'].toCharArray()) {
                    queue.offer(str + c);
                }
            }
            digits = digits.substring(1);
        };
        return (List<String>) queue;
    }

    // My 2nd: this map could be replaced by string array
    private static char[][] map = {
            {},     // 0
            {},     // 1
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
    };

    public List<String> letterCombinations2(String digits) {
        if (digits.isEmpty()) {
            return new ArrayList<>();
        }
        return doLetterCombinations(new char[digits.length()], digits);
    }

    private List<String> doLetterCombinations(char[] path, String digits) { // Simplify: reduce result and k
        if (digits.isEmpty()) {
            return Arrays.asList(new String(path));
        }

        List<String> result = new ArrayList<>();
        for (char c : map[digits.charAt(0) - '0']) {
            path[path.length - digits.length()] = c;
            result.addAll(doLetterCombinations(path, digits.substring(1)));
        }
        return result;
    }

    // My 1st: comb(n) = all possible letters at n * comb(n-1)
    public List<String> letterCombinations1(String digits) {
        if (digits.length() == 0) {
            return Collections.emptyList();
        }

        String[] letters;
        switch (digits.charAt(0)) {
            case '2': letters = new String[]{ "a", "b", "c" }; break;
            case '3': letters = new String[]{ "d", "e", "f" }; break;
            case '4': letters = new String[]{ "g", "h", "i" }; break;
            case '5': letters = new String[]{ "j", "k", "l" }; break;
            case '6': letters = new String[]{ "m", "n", "o" }; break;
            case '7': letters = new String[]{ "p", "q", "r", "s" }; break;
            case '8': letters = new String[]{ "t", "u", "v" }; break;
            case '9': letters = new String[]{ "w", "x", "y", "z" }; break;
            default: letters = new String[]{}; break;
        }

        if (digits.length() == 1) {
            return Arrays.asList(letters);
        }

        List<String> ret = new ArrayList<>();
        List<String> comb = letterCombinations(digits.substring(1));
        for (String l : letters) {
            for (String str : comb) {
                ret.add(l + str);
            }
        }
        return ret;
    }

}
