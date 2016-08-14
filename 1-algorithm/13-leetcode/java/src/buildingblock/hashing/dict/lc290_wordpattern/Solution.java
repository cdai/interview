package buildingblock.hashing.dict.lc290_wordpattern;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 * Examples:
 *  pattern = "abba", str = "dog cat cat dog" should return true.
 *  pattern = "abba", str = "dog cat cat fish" should return false.
 *  pattern = "aaaa", str = "dog cat cat dog" should return false.
 *  pattern = "abba", str = "dog dog dog dog" should return false.
 * Notes: You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().wordPattern(
                "itwasthebestoftimesitwastheworstoftimesitwastheageofwisdomitwastheageoffoolishnessitwastheepochofbeliefitwastheepochofincredulityitwastheseasonoflightitwastheseasonofdarknessitwasthespringofhopeitwasthewinterofdespair",
                "i tttttttttttttttt wwww aa sssss tttttttttttttttt hhhhhhhhhhhhhhhhhhhhhhh eeeeeeeeeee bbbbbbbbbbbbbbbbbb eeeeeeeeeee sssss tttttttttttttttt oo fffffffff tttttttttttttttt i mmm eeeeeeeeeee sssss i tttttttttttttttt wwww aa sssss tttttttttttttttt hhhhhhhhhhhhhhhhhhhhhhh eeeeeeeeeee wwww oo rrrrrrrrrrrrrr sssss tttttttttttttttt oo fffffffff tttttttttttttttt i mmm eeeeeeeeeee sssss i tttttttttttttttt wwww aa sssss tttttttttttttttt hhhhhhhhhhhhhhhhhhhhhhh eeeeeeeeeee aa gggggggggggggg eeeeeeeeeee oo fffffffff wwww i sssss ddddddddddd oo mmm i tttttttttttttttt wwww aa sssss tttttttttttttttt hhhhhhhhhhhhhhhhhhhhhhh eeeeeeeeeee aa gggggggggggggg eeeeeeeeeee oo fffffffff fffffffff oo oo llllllll i sssss hhhhhhhhhhhhhhhhhhhhhhh nnnnnn eeeeeeeeeee sssss sssss i tttttttttttttttt wwww aa sssss tttttttttttttttt hhhhhhhhhhhhhhhhhhhhhhh eeeeeeeeeee eeeeeeeeeee pppp oo cccccccccccc hhhhhhhhhhhhhhhhhhhhhhh oo fffffffff bbbbbbbbbbbbbbbbbb eeeeeeeeeee llllllll i eeeeeeeeeee fffffffff i tttttttttttttttt wwww aa sssss tttttttttttttttt hhhhhhhhhhhhhhhhhhhhhhh eeeeeeeeeee eeeeeeeeeee pppp oo cccccccccccc hhhhhhhhhhhhhhhhhhhhhhh oo fffffffff i nnnnnn cccccccccccc rrrrrrrrrrrrrr eeeeeeeeeee ddddddddddd uuuuuuuuuu llllllll i tttttttttttttttt yyyyyyyyyyy i tttttttttttttttt wwww aa sssss tttttttttttttttt hhhhhhhhhhhhhhhhhhhhhhh eeeeeeeeeee sssss eeeeeeeeeee aa sssss oo nnnnnn oo fffffffff llllllll i gggggggggggggg hhhhhhhhhhhhhhhhhhhhhhh tttttttttttttttt i tttttttttttttttt wwww aa sssss tttttttttttttttt hhhhhhhhhhhhhhhhhhhhhhh eeeeeeeeeee sssss eeeeeeeeeee aa sssss oo nnnnnn oo fffffffff ddddddddddd aa rrrrrrrrrrrrrr kkkkkkkkk nnnnnn eeeeeeeeeee sssss sssss i tttttttttttttttt wwww aa sssss tttttttttttttttt hhhhhhhhhhhhhhhhhhhhhhh eeeeeeeeeee sssss pppp rrrrrrrrrrrrrr i nnnnnn gggggggggggggg oo fffffffff hhhhhhhhhhhhhhhhhhhhhhh oo pppp eeeeeeeeeee i tttttttttttttttt wwww aa sssss tttttttttttttttt hhhhhhhhhhhhhhhhhhhhhhh eeeeeeeeeee wwww i nnnnnn tttttttttttttttt eeeeeeeeeee rrrrrrrrrrrrrr oo fffffffff ddddddddddd eeeeeeeeeee sssss pppp aa i rrrrrrrrrrrrrr"
        ));
    }

    // Stefan solution: very similiar to 205-Isomorphic Strings
    // Eg."abc","b a c" won't confuse map, since key is of different type
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (pattern.length() != words.length) {
            return false;
        }

        Map<Object,Integer> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            if (!Objects.equals(map.put(pattern.charAt(i), i), map.put(words[i], i))) {
                return false;
            }
        }
        return true;
    }

    // My 2nd: O(KN)?
    public boolean wordPattern2(String pattern, String str) {
        String[] words = str.split(" ");
        if (pattern.length() != words.length) {
            return false;
        }

        Map<Character,String> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            String word = map.get(pattern.charAt(i));
            if (word == null) {
                if (map.containsValue(words[i])) { // Note: linear time O(N)
                    return false;
                }
                map.put(pattern.charAt(i), words[i]);
            } else {
                if (!word.equals(words[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    // My 1st
    public boolean wordPattern1(String pattern, String str) {
        char[] patterns = pattern.toCharArray();
        String[] strs = str.split(" ");
        if (patterns.length != strs.length) {
            return false;
        }

        // Bijection mapping
        Map<Character,String> map = new HashMap<>();
        for (int i = 0; i < patterns.length; i++) {
            String mapped = map.get(patterns[i]);
            if (mapped == null) {
                if (map.values().contains(strs[i])) {
                    return false;
                }
                map.put(patterns[i], strs[i]);
            } else {
                if (!mapped.equals(strs[i])) {
                    return false;
                }
            }
        }
        return true;
    }

}
