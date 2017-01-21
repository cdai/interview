package hackerrank.string.stringcompression;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 */
public class Solution {

    public String compressString(String s) {
        StringBuilder ret = new StringBuilder();
        int n = s.length();
        for (int i = 0, cnt = 1; i < n; i++) { /* cnt = #A of [1stIdxOfA,i] */
            if (i < n - 1 && s.charAt(i) == s.charAt(i + 1)) cnt++;
            else {
                ret.append(s.charAt(i));
                if (cnt > 1) {
                    ret.append(cnt);
                    cnt = 1;
                }
            }
        }
        return ret.length() >= n ? s : ret.toString();
    }

    @Test
    void testEmpty() {
        Assertions.assertEquals("", compressString(""));
    }

    @Test
    void testNormal() { // consider lowercase letter only
        Assertions.assertEquals("a4", compressString("aaaa"));
        Assertions.assertEquals("a4b2", compressString("aaaabb"));

        Assertions.assertEquals("a4cb2", compressString("aaaacbb"));
        Assertions.assertEquals("ca4cb2c", compressString("caaaacbbc"));
    }

    @Test
    void testDegrade() { // required in CCI but not in Hackerrank
        Assertions.assertEquals("a", compressString("a"));
        Assertions.assertEquals("aabbcc", compressString("aabbcc")); // not a2b2c2
    }

}
