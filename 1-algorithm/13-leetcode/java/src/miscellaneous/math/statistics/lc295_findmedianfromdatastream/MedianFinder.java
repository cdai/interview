package miscellaneous.math.statistics.lc295_findmedianfromdatastream;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Median is the middle value in an ordered integer list.
 * If the size of the list is even, there is no middle value.
 * So the median is the mean of the two middle value.
 */
public class MedianFinder {

    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        int[] nums = new int[]{5, 1, 2, 10, 5, 6, 0, -1};
        for (int num : nums) {
            finder.addNum(num);
            System.out.printf("Add %d, median= %f\n", num, finder.findMedian());
        }
    }

    // max heap for first half
    private Queue<Integer> small = new PriorityQueue<>(Comparator.reverseOrder());

    // min heap for last half
    private Queue<Integer> large = new PriorityQueue<>();

    // Maintain: 1) two heaps represent two halves; 2) abs(|large| - |small|) <= 1
    public void addNum(int num) {
        Queue<Integer> h1 = (!small.isEmpty() && num > small.peek()) ? large : small;
        Queue<Integer> h2 = (h1 == large) ? small : large;

        h1.offer(num);
        if (h1.size() - h2.size() > 1)
            h2.offer(h1.poll());
    }

    // Median = large or small or (large + small) / 2;
    public double findMedian() {
        return large.size() > small.size() ? large.peek() :
                large.size() < small.size() ? small.peek() :
                        (large.peek() + small.peek()) / 2.0;
    }
    // Lintcode has different definition. Note:
    // Median is A[(n - 1) / 2]. A=[1,2,3], median=2. A=[1,19], median=1.
    //  med[i] = left.size() >= right.size() ? left.peek() : right.peek();

}
