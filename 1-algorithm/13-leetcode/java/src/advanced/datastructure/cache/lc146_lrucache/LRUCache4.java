package advanced.datastructure.cache.lc146_lrucache;

import java.util.HashMap;
import java.util.Map;

/**
 * 4AC. No big difference.
 */
public class LRUCache4 {
    private Map<Integer, LruNode> cache = new HashMap<>();
    private LruList list = new LruList();
    private int capacity;

    public LRUCache4(int capacity) { this.capacity = capacity; }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        list.moveToTail(cache.get(key));
        return cache.get(key).val;
    }

    public void set(int key, int val) {
        if (cache.containsKey(key)) { // Replace existing value and move node to tail
            cache.get(key).val = val;
            list.moveToTail(cache.get(key));
        } else { // Create new entry, put before tail and remove least recent one if full
            cache.put(key, new LruNode(key, val));
            list.moveToTail(cache.get(key));
            if (cache.size() > capacity)
                cache.remove(list.removeFromHead());
        }
    }

    // Existing Deque is no help since we cannot acquire inner Node object
    // Remove incurs linear scan. So expose inner node representation in this list impl.
    class LruList {
        private LruNode head = new LruNode(0, 0), tail = head;

        private void moveToTail(LruNode node) {
            if (node == tail) return;
            if (node.prev != null) { // cut out of existing node
                node.next.prev = node.prev;
                node.prev.next = node.next;
            }
            node.prev = tail;
            node.next = null;
            tail = tail.next = node;
        }

        private int removeFromHead() {
            LruNode node = head.next;
            head.next = node.next;
            node.next.prev = head;
            return node.key;
        }
    }

    class LruNode {
        int key, val;
        LruNode prev, next;
        LruNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
