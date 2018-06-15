/*
* @Author: czahie
* @Date:   2018-06-15 09:30:55
* @Last Modified by:   czahie
* @Last Modified time: 2018-06-15 09:30:55
*/

/** Based on Circular Sentinel. */

public class LinkedListDeque<AnyType> {
    public class Node {
        public AnyType item;
        public Node prev;
        public Node next;

        public Node(AnyType i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    /** Creates an empty Deque. */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** Creates an Deque. */
    public LinkedListDeque(AnyType item) {
        sentinel  = new Node(null, null, null);
        Node newNode  = new Node(item, sentinel, sentinel);
        sentinel.prev = newNode;
        sentinel.next = newNode;
        size = 1;
    }

    /** Adds an item to the front of the Deque. */
    public void addFirst(AnyType item) {
        sentinel.next = new Node(item, sentinel, sentinel.next);
        size = size + 1;
    }

    /** Adds an item to the back of the Deque. */
    public void addLast(AnyType item) {
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        size = size + 1;
    }

    /** Returns true if the Deque is empty, false otherwise. */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /** Returns the number of items of the Deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        Node n = sentinel.next;
        while (n != sentinel) {
            System.out.print(n.item + " ");
            n = n.next;
        }
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public AnyType removeFirst() {
        if (isEmpty()) {
            return null;
        }
        AnyType first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        size = size - 1;
        return first;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public AnyType removeLast() {
        if (isEmpty()) {
            return null;
        }
        AnyType last = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        size = size - 1;
        return last;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. */
    public AnyType get(int index) {
        if (index > size-1) {
            return null;
        }
        int i = 0;
        Node p = sentinel.next;
        while (i < index) {
            p = p.next;
            i++;
        }
        return p.item;
    }

}
