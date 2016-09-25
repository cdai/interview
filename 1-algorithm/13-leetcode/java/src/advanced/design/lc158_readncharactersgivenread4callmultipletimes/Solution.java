package advanced.design.lc158_readncharactersgivenread4callmultipletimes;

import advanced.design.lc157_readncharactersgivenread4.Reader4;

/**
 */
public class Solution extends Reader4 {

    private char[] buf4 = new char[4];

    private int i4 = 0, n4 = 0;

    // "What's the difference between I and II?":
    // When you call read4() which reads 4 bytes into your buffer you might read more than you need,
    // so you want to store those bytes in the structure, and next time you call read will start
    // from those stored bytes, then read more from the file.
    // Idea: copy from buf4 to buf, if none then read more. If no more, then quit.
    public int read(char[] buf, int n) {
        int i = 0;
        while (i < n) {
            if (i4 >= n4) {
                i4 = 0;
                n4 = read4(buf4);
                if (n4 == 0) break;
            }
            /* 1) i4<n4. 2) i4=0, n4>0 */
            buf[i++] = buf4[i4++];
        }
        return i;
    }

}
