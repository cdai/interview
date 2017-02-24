package advanced.scan.window.lc243_shortestworddistance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 */
public class Solution {

    @Test
    void test() {
        Assertions.assertEquals(1, shortestDistance(
                new String[]{"a", "c", "c", "b", "d", "a", "b"}, "a", "b"));
    }

    public int shortestDistance(String[] words, String w1, String w2) {
        int min = Integer.MAX_VALUE, p1 = -1, p2 = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(w1)) {
                p1 = i;
                if (p2 != -1) min = Math.min(min, p1 - p2);
            } else if (words[i].equals(w2)) {
                p2 = i;
                if (p1 != -1) min = Math.min(min, p2 - p1);
            }
        }
        return min;
    }

}
