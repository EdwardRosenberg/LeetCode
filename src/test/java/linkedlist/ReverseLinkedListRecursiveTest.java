package linkedlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ReverseLinkedListRecursiveTest {
    @Test
    void testReverseList() {
        // Create a linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        // Reverse the list
        ListNode reversed = new ReverseLinkedListRecursive().reverseList(head);

        // Check the reversed list
        assertEquals(5, reversed.val);
        assertEquals(4, reversed.next.val);
        assertEquals(3, reversed.next.next.val);
        assertEquals(2, reversed.next.next.next.val);
        assertEquals(1, reversed.next.next.next.next.val);
        assertNull(reversed.next.next.next.next.next);
    }

    @Test
    void testReverseListWithEmptyList() {
        // Test with an empty list
        ListNode head = null;
        ListNode reversed = new ReverseLinkedListRecursive().reverseList(head);
        assertNull(reversed);
    }

    @Test
    void testReverseListWithSingleElement() {
        // Test with a single element list
        ListNode head = new ListNode(1);
        ListNode reversed = new ReverseLinkedListRecursive().reverseList(head);
        assertEquals(1, reversed.val);
        assertNull(reversed.next);
    }
}