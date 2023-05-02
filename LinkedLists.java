//Linked List Middle
//Given the head of a singly linked list, return the middle node of the linked list.
//If there are two middle nodes, return the second middle node.
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
        
    }
}

//Remove duplicates from sorted list
//Given the head of a sorted linked list, delete all duplicates such that each element appears only once. 
//The returned linked list should be sorted
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }
}

//Reverse Linked List
//Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        
        if (left == right) {
            return head;
        }
        
        ListNode next = null;
        ListNode current = head;
        ListNode previous = null;
        ListNode leftNode = null;
        ListNode preLeftNode = null;
        int index = 1;
        while (index <= right) {
            next = current.next;
            if (index == left) {
                leftNode = current;
                preLeftNode = previous;
            }
            if (index > left) {
                current.next = previous;
            }
            previous = current;
            current = next;
            index++;
        }
        
        leftNode.next = current;
        if (preLeftNode != null) {
            preLeftNode.next = previous;
        } else {
            head = previous;
        }
        
        return head;
    }
}

