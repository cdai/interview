package buildingblock.table.hashing.lc138_copylistwithrandompointer;

import java.util.HashMap;
import java.util.Map;

/**
 * A linked list is given such that each node contains an additional random pointer
 * which could point to any node in the list or null. Return a deep copy of the list.
 */
public class Solution {

    public static void main(String[] args) {
        RandomListNode n1 = new RandomListNode(1);
        RandomListNode n2 = new RandomListNode(2);
        RandomListNode n3 = new RandomListNode(3);
        RandomListNode n4 = new RandomListNode(4);
        RandomListNode n5 = new RandomListNode(5);
        n1.next = n2;
        n1.random = n1;
        n2.next = n3;
        n2.random = n4;
        n3.next = n4;
        n3.random = n1;
        n4.next = n5;
        n4.random = n3;
        n5.next = null;
        n5.random = null;
        RandomListNode clone = new Solution().copyRandomList(n1);
        System.out.println(clone);
        System.out.println(new Solution().copyRandomList(null));
    }

    // Solution from programcreek, use 3 passes with O(1) space
    // Much faster than HashMap solution
    public RandomListNode copyRandomList(RandomListNode head) {
        // 1.Copy node as next of itself
        for (RandomListNode cur = head; cur != null; cur = cur.next.next) {
            RandomListNode copy = new RandomListNode(cur.label);
            copy.next = cur.next;
            cur.next = copy;    // cur.next = copy = not null
        }

        // 2.Set random pointer
        for (RandomListNode cur = head; cur != null; cur = cur.next.next)
            if (cur.random != null) cur.next.random = cur.random.next; // This is the key!!!

        // 3.Split into two lists
        RandomListNode dmy = new RandomListNode(0);
        RandomListNode prev = dmy;
        for (RandomListNode cur = head; cur != null; cur = cur.next) {
            prev.next = cur.next;
            prev = prev.next;
            cur.next = cur.next.next;   // must restore orginal list
        }
        return dmy.next;
    }

    // My 2nd: worse than 1st even... O(N) time O(N) space
    public RandomListNode copyRandomList2(RandomListNode head) {
        RandomListNode clones = new RandomListNode(0);
        RandomListNode prev = clones;

        Map<RandomListNode, RandomListNode> created = new HashMap<>();
        while (head != null) {
            RandomListNode clone = createIfNotExist(created, head);
            if (head.random != null)    // random could be null
                clone.random = createIfNotExist(created, head.random);
            prev.next = clone;
            prev = prev.next;
            head = head.next;
        }
        return clones.next;
    }

    private RandomListNode createIfNotExist(Map<RandomListNode, RandomListNode> created,
                                            RandomListNode node) {
        RandomListNode clone = created.get(node);
        if (clone == null) {
            clone = new RandomListNode(node.label);
            created.put(node, clone);
        }
        return clone;
    }

    // My 1st
    public RandomListNode copyRandomList1(RandomListNode head) {
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode prev = dummy;
        Map<RandomListNode,RandomListNode> clones = new HashMap<>();
        for (RandomListNode node = head; node != null; node = node.next) {
            RandomListNode clone = cloneIfNotExist(node, clones);
            if (node.random != null) // random could be null
                clone.random = cloneIfNotExist(node.random, clones);
            prev.next = clone;
            prev = clone;
        }
        return dummy.next;
    }

    private RandomListNode cloneIfNotExist(RandomListNode node,
                                           Map<RandomListNode,RandomListNode> clones) {
        RandomListNode clone = clones.get(node);
        if (clone == null) {
            clone = new RandomListNode(node.label);
            clones.put(node, clone);
        }
        return clone;
    }

}
