package advanced.design.lc379_designphonedirectory;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Design a Phone Directory which supports the following operations:
 *  get: Provide a number which is not assigned to anyone.
 *  check: Check if a number is available or not.
 *  release: Recycle or release a number.
 *
 * Faster than two hashset solution. Why?
 */
public class PhoneDirectory {

    private Set<Integer> used = new HashSet<>();

    private Queue<Integer> free = new LinkedList<>();

    private int max;

    public PhoneDirectory(int max) {
        this.max = max;
        for (int i = 0; i < max; i++) {
            free.offer(i);
        }
    }

    public int get() {
        if (free.isEmpty()) return -1;
        used.add(free.peek());
        return free.poll();
    }

    public boolean check(int num) {
        if (0 <= num && num < max) {
            return !used.contains(num);
        }
        return false;
    }

    public void release(int num) {
        if (used.remove(num)) {
            free.offer(num);
        }
    }
}
