package buildingblock.hashing.lc138_copylistwithrandompointer;

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

    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode dummy = new RandomListNode(-1);
        RandomListNode prev = dummy;

        Map<RandomListNode,RandomListNode> clones = new HashMap<>();
        for (RandomListNode node = head; node != null; node = node.next) {
            RandomListNode clone = cloneIfNotExist(node, clones);
            if (node.random != null) {
                clone.random = cloneIfNotExist(node.random, clones);
            }
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
