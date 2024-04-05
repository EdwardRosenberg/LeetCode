package linkedlist;

public class ReverseLinkedList {

    /**
     * Reverses a linked list.
     *
     * @param head The head of the linked list to be reversed.
     * @return The head of the reversed linked list.
     */
    public ListNode reverseList(ListNode head) {
        // If the list is empty or has only one node, it is already reversed, so return the head
        if (head == null || head.next == null) return head;

        // Initialize two pointers, one pointing to the current node and one to the previous node
        ListNode previous = null;
        ListNode current = head;

        // Traverse the list and reverse the links between nodes
        while (current != null) {
            // Save the reference to the next node to prevent losing it during reversal
            ListNode next = current.next;
            // Reverse the link by pointing the current node to the previous node
            current.next = previous;
            // Move the previous pointer to the current node
            previous = current;
            // Move the current pointer to the next node
            current = next;
        }

        // At the end of the loop, 'previous' will be pointing to the new head of the reversed list
        // So, return 'previous'
        return previous;
    }

}
