package linkedlist;

public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Check if either list is null
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        // Initialize variables
        // Determine the head of the merged list based on which list has the smaller value
        ListNode head = (list1.val <= list2.val) ? list1 : list2;
        // Initialize a current pointer to keep track of the current node in the merged list
        ListNode current = head;
        // Initialize next pointers for both lists to iterate through them
        ListNode next1 = (head == list1) ? list1.next : list1;
        ListNode next2 = (head == list2) ? list2.next : list2;

        // Merge the lists
        while (next1 != null && next2 != null) {
            // Compare the values of the next nodes in both lists
            if (next1.val <= next2.val) {
                // If the value in list1 is smaller or equal, append list1's node to the merged list
                current.next = next1;
                // Move to the next node in list1
                next1 = next1.next;
            } else {
                // If the value in list2 is smaller, append list2's node to the merged list
                current.next = next2;
                // Move to the next node in list2
                next2 = next2.next;
            }
            // Move the current pointer to the newly appended node in the merged list
            current = current.next;
        }

        // Append remaining nodes if any (either from list1 or list2)
        current.next = (next1 != null) ? next1 : next2;

        // Return the head of the merged list
        return head;
    }


}
