package advanced.datastructure.cache.lc146_lrucache;

import java.util.HashMap;
import java.util.Map;

/**
 * Classic doubly linked list implementation
 */
public class LRUCache5 {
    private Map<Integer, LruNode> cache = new HashMap<>();
    private LruList list = new LruList();
    private int capacity;

    public LRUCache5(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        LruNode node = cache.get(key);
        list.moveToTail(node);
        return node.val;
    }

    public void put(int key, int val) {
        if (cache.containsKey(key)) {
            LruNode node = cache.get(key);
            node.val = val;
            list.moveToTail(node);
        } else {
            LruNode node = new LruNode(key, val);
            list.moveToTail(node);
            cache.put(key, node);
            if (cache.size() > capacity) {
                cache.remove(list.removeHead().key);
            }
        }
    }

    // Implement a Doubly Linked List using two dummy nodes
    class LruList {
        private LruNode head = new LruNode();
        private LruNode tail = new LruNode();

        LruList() {
            head.next = tail;
            tail.prev = head;
        }

        void moveToTail(LruNode node) {
            if (node.prev != null) { // Remove first if node exists in list rather than a new node
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            LruNode last = tail.prev;
            node.next = tail;
            node.prev = last;
            last.next = tail.prev = node;
        }

        LruNode removeHead() {
            LruNode first = head.next;
            head.next = first.next;
            first.next.prev = head;
            return first;
        }
    }

    class LruNode {
        int key, val;
        LruNode prev, next;
        LruNode() {}
        LruNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
