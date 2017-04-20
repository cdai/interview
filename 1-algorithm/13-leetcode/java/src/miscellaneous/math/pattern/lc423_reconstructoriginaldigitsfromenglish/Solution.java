package miscellaneous.math.pattern.lc423_reconstructoriginaldigitsfromenglish;

/**
 * Given a non-empty string containing an out-of-order English representation of digits 0-9, output the digits in ascending order.
 */
public class Solution {

    // "Unique level":
    // Zero  tWo  foUr  siX  eiGht
    // One   <- zerO twO fOur
    // Five  <- Four
    // Seven <- Six
    // Three <- Two eighT
    // nIne  <- fIve sIx eIght
    public String originalDigits(String s) {
        int[] cnt = new int[10];
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'z': cnt[0]++; break; // must be Zero
                case 'w': cnt[2]++; break; // must be tWo
                case 'u': cnt[4]++; break; // must be foUr
                case 'x': cnt[6]++; break; // must be siX
                case 'g': cnt[8]++; break; // must be eiGht
                case 'o': cnt[1]++; break; // could be One zerO twO fOur
                case 'f': cnt[5]++; break; // could be Five Four
                case 's': cnt[7]++; break; // could be Seven Six
                case 't': cnt[3]++; break; // could be Three Two eighT
                case 'i': cnt[9]++; break; // could be nIne fIve sIx eIght
            }
        }
        cnt[1] -= cnt[0] + cnt[2] + cnt[4];
        cnt[5] -= cnt[4];
        cnt[7] -= cnt[6];
        cnt[3] -= cnt[2] + cnt[8];
        cnt[9] -= cnt[5] + cnt[6] + cnt[8];

        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < cnt.length; i++) {
            for (int j = cnt[i]; j > 0; j--) ret.append(i);
        }
        return ret.toString();
    }

}
