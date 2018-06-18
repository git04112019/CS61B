/*
* @Author: caizhe
* @Date:   2018-06-05 20:54:07
* @Last Modified by:   caizhe
* @Last Modified time: 2018-06-18 10:40:24
*/
public class SLList {
    private static class IntNode {
        private int item;
        private IntNode next;
        private IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    /* The first item (if it exists) is at sentinel.next. */
    private IntNode sentinel;
    private int size;

    /** Creates an empty SLList. */
    public SLList() {
        sentinel = new IntNode(63, null);
        size = 0;
    }

    public SLList(int x) {
        sentinel = new IntNode(63, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    /** Adds x to the front of the list. */
    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    /** Returns the first item in the list. */
    public int getFirst() {
        return sentinel.next.item;
    }

    /** Adds x to the end of the list. */
    public void addLast(int x) {
        size += 1;

        IntNode p = sentinel;
        /* Advance p to the end of the list. */
        while (p.next != null) {
            p = p.next;
        }
        
        p.next = new IntNode(x, null);
    }

    /** Returns the size of the list. */
    public int size() {
        return size;
    }

    /** Takes in an integer and inserts it at the given position. */
    public void insert(int x, int position) {
        // If the position is after the end of the list, insert the new node at the end. 
        if (position > size) {
            addLast(x);
        }
        else {
            IntNode p = sentinel;
            IntNode n = new IntNode(x, null);
            while (position > 0) {
                p = p.next;
                position = position - 1;
            }
            n.next = p.next;
            p.next = n;
            size = size + 1;
        }
    }
    

    /** Reverses the elements in the list. */
    public void reverse() {
        IntNode frontOfReversed = null;
        IntNode nextNodeToAdd = sentinel.next;
        while (nextNodeToAdd != null) {
            IntNode remainderOfOriginal = nextNodeToAdd.next;
            nextNodeToAdd.next = frontOfReversed;
            frontOfReversed = nextNodeToAdd;
            nextNodeToAdd = remainderOfOriginal;
        }
        sentinel.next = frontOfReversed;
    }

    public void reveseRecursive() {
        sentinel.next = reveseRecursiveHelper(sentinel.next);
    }

    private IntNode reveseRecursiveHelper(IntNode front) {
        if (front == null || front.next == null) {
            return front;
        }
        else {
            IntNode reversed = reveseRecursiveHelper(front.next);
            front.next.next = front;
            front.next = null;
            return reversed;
        }
    }

    public static void main(String[] args) {
        /* Creates a list of one integer, namely 10 */
        SLList L = new SLList();
        L.addLast(20);
        L.addLast(40);
        L.addLast(80);
        L.insert(30, 0);
        L.insert(100, 5);
        System.out.println(L.size());
        System.out.println(L.getFirst());
        L.reveseRecursive();
        System.out.println(L.getFirst()); 
    }
}