package advanced.datastructure.cache.lc146_lrucache;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class LRUCache {

    private Map<Integer,Node> cache = new HashMap<>();

    private StatList stat = new StatList();

    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        return stat.touch(cache.get(key)).val;
    }

    public void set(int key, int val) { // Note: keep Map and List consistent all the time!!!
        if (cache.containsKey(key)) {
            Node node = stat.touch(cache.get(key));
            node.val = val;
            cache.put(key, node);
        } else {
            cache.put(key, stat.addLast(new Node(key, val)));
            if (cache.size() > capacity)
                cache.remove(stat.removeFirst().key);
        }
    }

    class StatList {
        private Node head = new Node(0, 0);
        private Node tail = head;

        Node removeFirst() {
            Node first = head.next;
            head.next = first.next;
            first.next.prev = head;
            first.next = first.prev = null;
            return first;
        }

        Node addLast(Node node) {
            node.prev = tail;
            node.next = null;
            tail = tail.next = node;
            return node;
        }

        Node touch(Node node) {
            if (node == tail) return node;
            node.prev.next = node.next;
            node.next.prev = node.prev;
            return addLast(node);
        }
    }

    class Node {
        int key, val;
        Node prev, next;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

}
