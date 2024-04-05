package linkedlist;

public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode head = (list1.val <= list2.val) ? list1 : list2;
        ListNode list1current = list1;
        ListNode list1next = list1.next;
        ListNode list2current = list2;
        ListNode list2next = list2.next;

        while (list2current != null) {
            if (list1current.val <= list2current.val && ((list1current.next != null && list1current.next.val >= list2current.val) || list1current.next == null)) {
                list1next = list1current.next;
                list1current.next = list2current;
                list2current = list2current.next;
                list1current.next.next = list1next;
            } else if (list1current.next != null && list1current.next.val < list2current.val) {
                list1current = list1current.next;
            } else {
                list2next = list2current.next;
                list1next = list1current;
                list1current = list2current;
                list1current.next = list1next;
                list2current = list2next;
            }
        }

        return head;
    }

}
