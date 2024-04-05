package linkedlist;

public class ReverseLinkedListRecursive {

    /**
     * Reverses a linked list recursively.
     *
     * @param head The head of the linked list to be reversed.
     * @return The head of the reversed linked list.
     */
    public ListNode reverseList(ListNode head) {
        // If the list is empty, there is nothing to reverse, so return null
        if (head == null) return head;

        // Base case: if the current node is the last node, return it as the new head
        if (head.next == null) return head;

        // Recursive call to reverse the sublist starting from the next node
        ListNode newHead = reverseList(head.next);

        // Reverse the link from the current node to the next node
        head.next.next = head;

        // Break the link from the current node to the next node
        head.next = null;

        // Return the new head of the reversed sublist
        return newHead;
    }

}
