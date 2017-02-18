package fundamentals.string.manipulate.lc388_longestabsolutefilepath;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 */
public class Solution {

    @Test
    void test() {
        Assertions.assertEquals(20, lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
    }

    // Only save length on stack
    public int lengthLongestPath(String input) {
        Stack<Integer> s = new Stack<>();
        s.push(0); // cause dep+1 and s.size()-2
        int max = 0;
        for (String dir : input.split("\n")) {
            int dep = dir.lastIndexOf("\t") + 1;
            while (s.size() > dep + 1) s.pop(); // pop to find parent dir

            int len = s.peek() + dir.length() - dep;
            s.push(len);
            if (dir.contains(".")) { // push to stack and update max and len if cur is file
                max = Math.max(max, len + s.size() - 2); // don't forget add slash
            }
        }
        return max;
    }

    public int lengthLongestPath1(String input) {
        Stack<String> s = new Stack<>();
        String[] dirs = input.split("\n");
        int max = 0, len = 0;
        for (String dir : dirs) {
            // Get depth of dir
            int dep = 0, i = 0;
            while (dir.startsWith("\t", i)) {
                dep++;
                i++;
            }
            // Pop sub dir as well as dir of same depth
            while (s.size() > dep) {
                len -= s.pop().length();
            }
            // Push to stack and update max and len if cur is file
            s.push(dir.substring(i));
            len += s.peek().length();
            if (dir.contains(".")) {
                max = Math.max(max, len + s.size() - 1); // don't forget add slash
            }
        }
        return max;
    }

}
