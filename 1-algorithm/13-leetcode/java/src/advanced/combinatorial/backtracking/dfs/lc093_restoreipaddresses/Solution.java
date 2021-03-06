package advanced.combinatorial.backtracking.dfs.lc093_restoreipaddresses;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * For example: Given "25525511135", return ["255.255.11.135", "255.255.111.35"].
 * (Order does not matter)
 */
public class Solution {

    // My 2AC: O(2^N)? without cutting
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        doRestore(result, "", s, 0);
        return result;
    }

    private void doRestore(List<String> result, String path, String s, int k) {
        if (s.isEmpty() || k == 4) {
            if (s.isEmpty() && k == 4)
                result.add(path.substring(1));
            return;
        }
        for (int i = 1; i <= (s.charAt(0) == '0' ? 1 : 3) && i <= s.length(); i++) { // Key: avoid corner case, like leading 0
            String part = s.substring(0, i);
            if (Integer.valueOf(part) <= 255)
                doRestore(result, path + "." + part, s.substring(i), k + 1);
        }
    }

    // My 1AC
    public List<String> restoreIpAddresses1(String s) {
        List<String> result = new ArrayList<>();
        int[] path = new int[5]; // substring(p[0], p[1]), substring(p[1], p[2])...
        restore(result, path, s, 1);
        return result;
    }

    private void restore(List<String> result, int[] path, String str, int k) {
        if (path[k-1] > str.length()) { // error2,3: forget and must be placed at first
            return;
        }

        if (k > 1) { // error3: must be placed at second, before if(k==len)
            if (Integer.parseInt(str.substring(path[k-2], path[k-1])) > 255) {
                return;
            }

            if (path[k-1] - path[k-2] > 1 && str.charAt(path[k-2]) == '0') { // error4: if lead by 0, then must be 0
                return;
            }
        }

        if (k == path.length) {
            if (str.length() == path[k-1]) {
                StringBuilder ip = new StringBuilder();
                for (int i = 0; i < 4; i++) { // error1: i+1<len => i<4
                    ip.append(str.substring(path[i], path[i+1])).append(".");
                }
                result.add(ip.deleteCharAt(ip.length()-1).toString());
            }
            return;
        }

        for (int i = 1; i <= 3; i++) {
            path[k] = path[k-1] + i;            // => base case: path[k] < str.length() (k=k+1)
            restore(result, path, str, k+1);    // => base case: k+1 <= path.length
        }
    }

}
