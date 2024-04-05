package linkedlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MergeTwoSortedListsTest {
    @Test
    void testMergeTwoLists() {
        // Create two sorted linked lists: 1 -> 3 -> 5 and 2 -> 4 -> 6
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(3);
        list1.next.next = new ListNode(5);

        ListNode list2 = new ListNode(2);
        list2.next = new ListNode(4);
        list2.next.next = new ListNode(6);

        // Merge the lists
        ListNode merged = new MergeTwoSortedLists().mergeTwoLists(list1, list2);

        // Verify the merged list
        assertEquals(1, merged.val);
        assertEquals(2, merged.next.val);
        assertEquals(3, merged.next.next.val);
        assertEquals(4, merged.next.next.next.val);
        assertEquals(5, merged.next.next.next.next.val);
        assertEquals(6, merged.next.next.next.next.next.val);
        assertNull(merged.next.next.next.next.next.next);
    }

    @Test
    void testMergeTwoListsWithOneListEmpty() {
        // Test when one list is empty
        ListNode list1 = new ListNode(1);
        ListNode list2 = null;

        ListNode merged1 = new MergeTwoSortedLists().mergeTwoLists(list1, list2);
        assertEquals(list1, merged1);

        ListNode merged2 = new MergeTwoSortedLists().mergeTwoLists(list2, list1);
        assertEquals(list1, merged2);
    }

    @Test
    void testMergeTwoListsWithBothListsEmpty() {
        // Test when both lists are empty
        ListNode list1 = null;
        ListNode list2 = null;

        ListNode merged = new MergeTwoSortedLists().mergeTwoLists(list1, list2);
        assertNull(merged);
    }
}