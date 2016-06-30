package advanced.combinatorial.partition.lc131_palindromepartitioning;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * For example, given s = "aab",
 * Return
 *  [
 *      ["aa","b"],
 *      ["a","a","b"]
 *  ]
 */
public class Solution {

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        LinkedList<String> path = new LinkedList<>();
        partition(result, path, s);
        return result;
    }

    private void partition(List<List<String>> result, LinkedList<String> path, String str) {
        if (str.length() == 0) {
            result.add(new ArrayList<>(path)); // error1: forget to make a copy
            return;
        }

        if (isPalindrome(str)) { // error2: str itself could be a partition as well
            path.add(str);
            result.add(new ArrayList<>(path));
            path.removeLast();
            //return;
        }

        for (int i = 1; i < str.length(); i++) { // i=1..len-1: substring(0, len-1), substring(len-1) at most
            if (isPalindrome(str.substring(0, i))) {
                path.add(str.substring(0, i));
                partition(result, path, str.substring(i));
                path.removeLast();
            }
        }
    }

    private boolean isPalindrome(String str) {
        for (int i = 0, j = str.length()-1; i < j; i++, j--) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
        }
        return true;
    }

}
