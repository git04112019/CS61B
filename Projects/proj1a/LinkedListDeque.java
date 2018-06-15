/*
* @Author: czahie
* @Date:   2018-06-15 09:30:55
* @Last Modified by:   czahie
* @Last Modified time: 2018-06-15 19:09:27
*/

/** Based on Circular Sentinel. 
* Invariants:
* 1. The first Node and last Node of the Deque are always the same -- The sentinel.
* 2. Sentinel.prev always points to the last Node of the Deque.
* 3. Sentinel.next always points to the first Node of the Deque.
* 4. The number of items always equals to size.
*/

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

    // When insert an item to the front, We need to reassign four pointers:
    // 1. The old first node's next pointer;
    // 2. The new first node's prev pointer;
    // 3. The new first node's next pointer;
    // 4. The old second node's prev pointer.
    /** Adds an item to the front of the Deque. */
    public void addFirst(AnyType item) {
        Node first = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = first;
        if (size == 0) {
            // If the Deque was empty, then the two pointer should both be reassigned.
            sentinel.next = new Node(item, sentinel, sentinel.next);
            sentinel.prev = sentinel.next;
        }
        else {
            // If the Deque was not empty, then only the next pointer should be reassigned.
            // The prev pointer should always points to the last Node.
            sentinel.next = new Node(item, sentinel, sentinel.next);
        }
        size = size + 1;
    }

    // Inserting an item to the back is similar to inserting to the front.
    /** Adds an item to the back of the Deque. */
    public void addLast(AnyType item) {
        Node last = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = last;
        if (size == 0) {
            sentinel.prev = last;
            sentinel.next = sentinel.prev;
        }
        else {
            sentinel.prev = last;
        }
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

    /** Same as get, but uses recursion. */
    public AnyType getRecursive(int index) {
        return getHelper(sentinel.next, index);
    }

    private AnyType getHelper(Node n, int index) {
        if (index == 0) {
            return n.item;
        }
        return getHelper(n.next, index - 1);
    }
}
