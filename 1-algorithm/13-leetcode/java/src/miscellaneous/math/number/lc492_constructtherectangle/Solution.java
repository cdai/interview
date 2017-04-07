package miscellaneous.math.number.lc492_constructtherectangle;

/**
 * So, given a specific rectangular web page’s area, your job by now is to design a rectangular web page, whose length L and width W satisfies:
 *  1. The area of the rectangular web page you designed must equal to the given target area.
 *  2. The width W should not be larger than the length L, which means L >= W.
 *  3. The difference between length L and width W should be as small as possible.
 */
public class Solution {

    public int[] constructRectangle(int area) {
        for (int w = (int) Math.sqrt(area); w > 0; w--) {
            if (area % w == 0) return new int[]{ area / w, w };
        }
        return new int[]{ 0, 0 };
    }

}
