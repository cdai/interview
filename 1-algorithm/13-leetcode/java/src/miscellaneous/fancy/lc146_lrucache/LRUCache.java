package miscellaneous.fancy.lc146_lrucache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * TLE...
 */
public class LRUCache {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        System.out.println("get(2): " + cache.get(2));
        System.out.println(cache.data);
        System.out.println(cache.freq);
        System.out.println("set(2,6)");
        cache.set(2, 6);
        System.out.println(cache.data);
        System.out.println(cache.freq);
        System.out.println("get(1): " + cache.get(1));
        System.out.println(cache.data);
        System.out.println(cache.freq);
        System.out.println("set(1,5)");
        cache.set(1, 5);
        System.out.println(cache.data);
        System.out.println(cache.freq);
        System.out.println("set(1,2)");
        cache.set(1, 2);
        System.out.println(cache.data);
        System.out.println(cache.freq);
        System.out.println("get(1): " + cache.get(1));
        System.out.println(cache.data);
        System.out.println(cache.freq);
        System.out.println("get(2): " + cache.get(2));
        System.out.println(cache.data);
        System.out.println(cache.freq);
    }

    private Map<Integer, Integer> data;

    private Queue<Integer> freq;

    private int capacity;

    private int size;

    public LRUCache(int capacity) {
        this.data = new HashMap<>();
        this.freq = new LinkedList<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!data.containsKey(key)) {
            return -1;
        }
        freq.remove(key);
        freq.offer(key);
        return data.get(key);
    }

    public void set(int key, int value) {
        if (data.containsKey(key)) { // Update an old k-v
            freq.remove(key);
            freq.offer(key);
            data.put(key, value);
        } else {                    // Insert a new k-v
            if (size == capacity) {
                data.remove(freq.poll());
                size--;
            }
            freq.offer(key);
            data.put(key, value);
            size++;
        }
    }

}
