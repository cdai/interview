package miscellaneous.math.geometry.lc223_rectanglearea;

/**
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 * Assume that the total area is never beyond the maximum possible value of int.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().computeArea(
                -1500000001, 0, -1500000000, 1,
                1500000000, 0, 1500000001, 1));
    }

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = (C - A) * (D - B);
        int area2 = (G - E) * (H - F);
        return area1 + area2 - overrideLength(A, C, E, G) * overrideLength(B, D, F, H);
    }

    private int overrideLength(int x1, int y1, int x2, int y2) {
        int minY = Math.min(y1, y2);
        int maxX = Math.max(x1, x2); // error: (minY - maxX) cause overflow, eg. 15000000000 - (-15000000000).
        return (minY <= maxX) ? 0 : (minY - maxX);
    }

    private int overrideLength2(int x1, int y1, int x2, int y2) {
        // x1,x2,y1,y2 -> [x2,y1]
        if (x1 <= x2 && x2 < y1 && y1 <= y2) {
            return y1 - x2;
        }
        // x1,x2,y2,y1 -> [x2,y2]
        if (x1 <= x2 && y2 <= y1) {
            return y2 - x2;
        }
        // x2,x1,y1,y2 -> [x1,y1]
        if (x2 <= x1 && y1 <= y2) { // error: forget...
            return y1 - x1;
        }
        // x2,x1,y2,y1 -> [x1,y2]
        if (x2 < x1 && x1 < y2 && y2 <= y2) {
            return y2 - x1;
        }
        // x1,y1,x2,y2 or x2,y2,x1,y1
        return 0;
    }

}

