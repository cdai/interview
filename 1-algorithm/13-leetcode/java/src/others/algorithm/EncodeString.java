package others.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 */
public class EncodeString {

    @Test
    void testEncode() {
        // Too short. No benefit
        Assertions.assertEquals("", encode(""));
        Assertions.assertEquals("a", encode("a"));
        Assertions.assertEquals("aa", encode("aa"));
        Assertions.assertEquals("ab", encode("ab"));
        Assertions.assertEquals("abc", encode("abc"));

        // Compress
        Assertions.assertEquals("3a", encode("aaa"));
        Assertions.assertEquals("3a4b", encode("aaabbbb"));
        Assertions.assertEquals("3ab5c", encode("aaabccccc"));
    }

    @Test
    void testDecode() {
        Assertions.assertEquals("", decode(""));
        Assertions.assertEquals("a", decode("a"));
        Assertions.assertEquals("aa", decode("aa"));
        Assertions.assertEquals("ab", decode("ab"));
        Assertions.assertEquals("abc", decode("abc"));

        Assertions.assertEquals("aaa", decode("3a"));
        Assertions.assertEquals("aaabbbb", decode("3a4b"));
        Assertions.assertEquals("aaabccccc", decode("3ab5c"));
    }

    public String encode(String word) {
        StringBuilder enc = new StringBuilder();
        int n = word.length();
        for (int i = 1, cnt = 1; i <= n; i++) {
            if (i == n || word.charAt(i - 1) != word.charAt(i)) {
                if (cnt > 1) {
                    enc.append(cnt);
                }
                enc.append(word.charAt(i - 1));
                cnt = 0;
            }
            cnt++;
        }
        return enc.length() < word.length() ? enc.toString() : word;
    }

    public String decode(String word) {
        StringBuilder dec = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (Character.isDigit(word.charAt(i))) {
                int rep = word.charAt(i) - '0';
                char c = word.charAt(++i);
                while (rep-- > 0) {
                    dec.append(c);
                }
            } else {
                dec.append(word.charAt(i));
            }
        }
        return dec.toString();
    }

}
