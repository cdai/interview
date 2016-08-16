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

    // My 2nd: O(2^N) time, O(N) space
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        doPartition(result, new ArrayList<>(), s, 0);
        return result;
    }

    private void doPartition(List<List<String>> result,
                             List<String> path, String s, int start) {
        if (start == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = start + 1; i <= s.length(); i++) {
            String substr = s.substring(start, i);
            if (isPalindrome(substr)) {
                path.add(substr);
                doPartition(result, path, s, i);
                path.remove(path.size() - 1);
            }
        }
    }

    // My 1st
    public List<List<String>> partition1(String s) {
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
