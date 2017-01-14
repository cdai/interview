package advanced.datastructure.cache.lc460_lfucache;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Frequently Used (LFU) cache.
 */
public class LFUCache {

    private Map<Integer, LfuNode> cache = new HashMap<>();
    private LfuList list = new LfuList();
    private int capacity;

    public LFUCache(int capacity) { this.capacity = capacity; }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        list.updateFreq(cache.get(key));
        return cache.get(key).val;
    }

    public void put(int key, int val) {
        if (cache.containsKey(key)) {
            cache.get(key).val = val;
            list.updateFreq(cache.get(key));
        } else {
            cache.put(key, new LfuNode(key, val));
            list.updateFreq(cache.get(key));
            if (cache.size() > capacity)
                cache.remove(list.removeFromHead());
        }
    }

    class LfuList {
        LfuNode head = new LfuNode(0, 0), tail = head;

        void addFromHead(LfuNode node) {
            node.next = head.next;
            node.prev = head;
            node.next.prev = head.next = node;
        }

        int removeFromHead() {
            LfuNode node = head.next;
            head.next = node.next;
            node.next.prev = head;
            return node.val;
        }

        void updateFreq(LfuNode node) {
            // TODO
        }
    }

    class LfuNode {
        int key, val, freq = 0;
        LfuNode prev, next;
        LfuNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

}
