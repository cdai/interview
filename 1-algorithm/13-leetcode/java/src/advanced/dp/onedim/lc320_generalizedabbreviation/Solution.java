package advanced.dp.onedim.lc320_generalizedabbreviation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 */
public class Solution {

    @Test
    void testNormal() {
        Assertions.assertEquals(new HashSet<>(Arrays.asList(
                "word", "1ord", "w1rd", "wo1d", "wor1",
                "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1",
                "1o2", "2r1", "3d", "w3", "4")), new HashSet<>(abbr("word")));
    }

    public List<String> abbr(String word) {
        Queue<String> abbr = new LinkedList<>();
        abbr.offer("");
        for (int i = 0; i < word.length(); i++) {
            // Append a new letter
            for (int j = abbr.size(); j > 0; j--) {
                abbr.offer(abbr.poll() + word.charAt(i));
            }

            // Only consider new abbr caused by last letter
            for (int j = abbr.size(); j > 0; j--) {
                String w = abbr.poll();
                for (int k = w.length() - 1; k >= 0 && Character.isLetter(w.charAt(k)); k--) {
                    if (k == 0 || Character.isLetter(w.charAt(k - 1))) {
                        abbr.offer(w.substring(0, k) + (w.length() - k));
                    }
                }
                abbr.offer(w);
            }
        }
        return (List<String>) abbr;
    }

}
