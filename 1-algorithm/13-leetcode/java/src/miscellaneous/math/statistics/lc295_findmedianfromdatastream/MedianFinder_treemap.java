package miscellaneous.math.statistics.lc295_findmedianfromdatastream;

import java.util.Collections;
import java.util.TreeMap;

/**
 */
public class MedianFinder_treemap {

    private int lsize = 0, rsize = 0, lmax = 0, rmin = 0;
    private TreeMap<Integer,Integer> left = new TreeMap<>(Collections.reverseOrder());
    private TreeMap<Integer,Integer> right = new TreeMap<>();

    // Maintain: 1) two heaps represent two halves; 2) abs(|large| - |small|) <= 1
    public void addNum(int num) {
        right.put(num, right.getOrDefault(num, 0) + 1);
        transfer(right, left);
        if (++lsize > rsize) {
            transfer(left, right);
            lsize--;
            rsize++;
        }
        if (!left.isEmpty()) lmax = left.firstKey(); // tend to speed up findMedian(), but slow down addNum() indeed
        if (!right.isEmpty()) rmin = right.firstKey();
    }

    /** Transfer first element (max of left or min of right) in src to dst */
    private void transfer(TreeMap<Integer,Integer> src,
                          TreeMap<Integer,Integer> dst) {
        int first = src.firstKey();
        if (!src.remove(first, 1)) {
            src.put(first, src.get(first) - 1);
        }
        dst.put(first, dst.getOrDefault(first, 0) + 1);
    }

    // Median = large or small or (large + small) / 2;
    public double findMedian() {
        if (rsize > lsize) return rmin;
        return (lmax + rmin) / 2.0;
    }

}
