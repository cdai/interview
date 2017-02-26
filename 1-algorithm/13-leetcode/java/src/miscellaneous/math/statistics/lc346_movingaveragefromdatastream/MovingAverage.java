package miscellaneous.math.statistics.lc346_movingaveragefromdatastream;

import org.junit.jupiter.api.Assertions;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 */
public class MovingAverage {

    public static void main(String[] args) {
        MovingAverage avg = new MovingAverage(3);
        Assertions.assertEquals(1, avg.next(1));
        Assertions.assertEquals(5, avg.next(10));
        Assertions.assertEquals(4, avg.next(3));
        Assertions.assertEquals(6, avg.next(5));
    }

    private Queue<Integer> q = new LinkedList<>();
    private final int size;
    private int sum = 0;

    public MovingAverage(int size) {
        this.size = size;
    }

    public int next(int num) {
        q.offer(num);
        sum += num;
        if (q.size() > size) {
            sum -= q.poll();
        }
        return sum / q.size();
    }

}
