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

    private Queue<Integer> min = new PriorityQueue<>();

    private Queue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());

    // Maintain abs(|min| - |max|) <= 1
    public void addNum(int num) {
        Queue<Integer> h1 = (max.isEmpty() || num > max.peek()) ? min : max;
        Queue<Integer> h2 = (h1 == min) ? max : min;

        h1.offer(num);
        if (h1.size() - h2.size() > 1)
            h2.offer(h1.poll());
    }

    // Median = min or max or (min + max) / 2;
    public double findMedian() {
        //if (min.isEmpty() && max.isEmpty()) return 0;
        return min.size() > max.size() ? min.peek() :
                min.size() < max.size() ? max.peek() :
                        (min.peek() + max.peek()) / 2.0;
    }

}
