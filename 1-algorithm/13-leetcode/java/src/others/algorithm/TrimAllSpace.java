package others.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 */
public class TrimAllSpace {

    @Test
    void test() {
        Assertions.assertEquals("", trimAll("   "));
        Assertions.assertEquals("abc", trimAll("   abc"));
        Assertions.assertEquals("abc", trimAll("abc   "));
        Assertions.assertEquals("abc", trimAll("   abc   "));
        Assertions.assertEquals("abc def NY.", trimAll("   abc  def    NY.  "));
    }

    private String trimAll(String str) {
        StringBuilder ret = new StringBuilder();
        boolean inSpace = false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                if (ret.length() == 0) continue; // leading space
                inSpace = true;
            } else {
                if (inSpace) {
                    ret.append(" ");
                    inSpace = false;
                }
                ret.append(str.charAt(i));
            }
        }
        return ret.toString();
    }

}
