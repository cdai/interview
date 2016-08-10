package fundamentals.list.removal.lc328_oddevenlinkedlist;

import fundamentals.list.ListNode;

/**
 * Given a singly linked list, group all odd nodes together followed by the even nodes.
 * Please note here we are talking about the node number and not the value in the nodes.
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 * Example: Given 1->2->3->4->5->NULL, return 1->3->5->2->4->NULL.
 * Note: The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on ...
 */
public class Solution {

    // Smart and clean!
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }

        // even=null if even nodes, otherwise even.next=null
        // Smart! Use even as condition, so odd must not be null
        ListNode odd = head, even = odd.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;

            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    // My 2nd: errornous if we want odd stop at last one
    public ListNode oddEvenList2(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode head2 = new ListNode(0);
        ListNode odd = head, even = head2;
        while (odd.next != null) { // NPE here without "if" in loop body
            even.next = odd.next;
            odd.next = odd.next.next;

            even = even.next;
            even.next = null;

            if (odd.next == null) { // error: hard to handle cur.next
                break;
            }
            odd = odd.next;
        }
        odd.next = head2.next; // NPE here if add "odd != null" in loop condition
        return head;
    }

    // My 1st: ugly...
    // Use your code to assert, do NOT use your head to assume!!!
    public ListNode oddEvenList3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // Invariant: [head,odd] are odd, (odd,even] are even, (even,tail] are to handle
        ListNode odd = head;
        ListNode even = head.next;
        while (even.next != null) {
            insertAfterOdd(odd, even);
            odd = odd.next;
            even = even.next;     // error2: even.next is not null before insert but now uncertain...
            if (even == null) {
                break;
            }
        }
        return head;
    }

    /* even.next != null */
    private void insertAfterOdd(ListNode odd, ListNode even) {
        //   1->2->3->4 (odd=1, even=2, tmp=3)
        // =>1->3->2->4
        ListNode nextOdd = even.next;
        even.next = nextOdd.next;

        ListNode firstEven = odd.next;  // error1: don't miss first even node, "even" point to last even node
        odd.next = nextOdd;
        nextOdd.next = firstEven;
    }

}
