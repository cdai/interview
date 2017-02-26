package miscellaneous.math.statistics.lc295_findmedianfromdatastream;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 */
public class MedianFinder {

    private Queue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
    private Queue<Integer> right = new PriorityQueue<>();

    // Maintain |left| <= |right|
    public void addNum(int num) {
        right.offer(num);
        left.offer(right.poll()); // if num should be in left, right.poll() should return it
        if (left.size() > right.size()) { // |right| stay same, only |left| possibly increase. maintain invariant.
            right.offer(left.poll());
        }
    }

    // Median = top element of right if |left| < |right|. Otherwise
    public double findMedian() {
        if (left.size() < right.size()) return right.peek();
        return (left.peek() + right.peek()) / 2.0;
    }

}
