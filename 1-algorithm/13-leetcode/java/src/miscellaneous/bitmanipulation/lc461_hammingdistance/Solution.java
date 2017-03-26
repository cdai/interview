package miscellaneous.bitmanipulation.lc461_hammingdistance;

/**
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 */
public class Solution {

    public int hammingDistance(int x, int y) {
        int cnt = 0, xor = x ^ y;
        for (int i = 0; i < 32; i++) {
            cnt += (xor >>> i) & 1;
        }
        return cnt;
    }

    public int hammingDistance_my(int x, int y) {
        int cnt = 0;
        for (int i = 0; i < 32; i++) {
            if (((x >>> i) & 1) != ((y >>> i) & 1)) cnt++;
        }
        return cnt;
    }

}
