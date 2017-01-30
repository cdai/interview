package lintcode.hashing;

import fundamentals.list.ListNode;
import org.junit.jupiter.api.Assertions;

/**
 */
public class MyHashTbl {

    public static void main(String[] args) {
        MyHashTbl tbl = new MyHashTbl(10);
        Assertions.assertTrue(tbl.add(1));
        Assertions.assertTrue(tbl.add(2));
        Assertions.assertTrue(tbl.add(7));

        // Normal test
        Assertions.assertTrue(tbl.contains(1));
        Assertions.assertTrue(tbl.contains(2));
        Assertions.assertTrue(tbl.contains(7));
        Assertions.assertFalse(tbl.contains(3));
        Assertions.assertFalse(tbl.contains(9));

        // Collision test
        Assertions.assertTrue(tbl.add(11));
        Assertions.assertTrue(tbl.add(21));
        Assertions.assertFalse(tbl.add(21));
        Assertions.assertTrue(tbl.add(31));

        Assertions.assertTrue(tbl.contains(11));
        Assertions.assertTrue(tbl.contains(21));
        Assertions.assertTrue(tbl.contains(31));
        Assertions.assertFalse(tbl.contains(41));

        // Remove test
        Assertions.assertTrue(tbl.remove(11)); // remove first
        Assertions.assertFalse(tbl.contains(11));
        Assertions.assertTrue(tbl.contains(21));
        Assertions.assertTrue(tbl.contains(31));
        tbl.remove(31); // remove last
        Assertions.assertFalse(tbl.contains(31));
        Assertions.assertTrue(tbl.contains(21));
        tbl.remove(21); // empty
        Assertions.assertFalse(tbl.contains(21));
    }

    private int capacity;
    private ListNode[] tbl;

    public MyHashTbl(int capacity) {
        this.capacity = capacity;
        this.tbl = new ListNode[capacity];
        for (int i = 0; i < capacity; i++) { // Dummy as <Algorithm 4e>
            tbl[i] = new ListNode(0);
        }
    }

    // ===== Chaining =====

    // O(1) insert from head, dirty duplicates exist in list though
    public boolean add(int key) {
        ListNode pre = search(key);
        if (pre.next == null) {
            pre.next = new ListNode(key);
            return true;
        }
        return false;
    }

    public boolean contains(int key) {
        ListNode pre = search(key);
        return pre.next != null;
    }

    public boolean remove(int key) {
        ListNode pre = search(key);
        if (pre.next != null) {
            pre.next = pre.next.next;
            return true;
        }
        return false;
    }

    // 3 cases -> 2 cases with dummy head:
    //  x) pre is null (slot is empty)
    //  b) pre.next is null (key doesn't exist)
    //  c) pre.next not null (pre.next.val==key -> key exists)
    private ListNode search(int key) {
        ListNode pre = tbl[hashcode(key)];
        while (pre.next != null && pre.next.val != key) {
            pre = pre.next;
        }
        return pre;
    }

    private int hashcode(int val) {
        return (val % capacity + capacity) % capacity;
    }

    // 129-Rehashing: Optimize
    //  1) O(1) insert from head
    //  2) Reuse ListNode
    public ListNode[] rehashing(ListNode[] tbl) {
        int n = tbl.length * 2;
        ListNode[] newtbl = new ListNode[n];
        for (ListNode node : tbl) {
            for (; node != null; node = node.next) {
                int idx = hash(node.val, n);
                if (newtbl[idx] == null) {
                    newtbl[idx] = new ListNode(node.val);
                } else {
                    ListNode pre = newtbl[idx];
                    while (pre.next != null) pre = pre.next;
                    pre.next = new ListNode(node.val);
                }
            }
        }
        return newtbl;
    }

    // In Java, if you directly calculate -4 % 3 you will get -1.
    // You can use function: a % b = (a % b + b) % b to make it is a non negative integer.
    private int hash(int val, int n) {
        return (val % n + n) % n;
    }

    // ===== Chaining =====

    public void put2(int key, int val) {

    }

    public Integer get2(int key) {
        return null;
    }
}
