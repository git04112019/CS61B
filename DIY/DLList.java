/*
* @Author: caizhe
* @Date:   2018-06-05 22:03:30
* @Last Modified by:   caizhe
* @Last Modified time: 2018-06-05 22:21:25
*/
public class DLList {
    private static class IntNode {
        private int item;
        private IntNode prev;
        private IntNode next;
        private IntNode(int i, IntNode n, IntNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    /* The first item (if it exists) is at sentinel.next. */
    private IntNode sentinel;
    private int size;

    /** Creates an empty SLList. */
    public DLList() {
        sentinel = new IntNode(63, sentinel, sentinel);
        size = 0;
    }

    public DLList(int x) {
        sentinel = new IntNode(63, sentinel, sentinel);
        sentinel.next = new IntNode(x, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /** Adds x to the front of the list. */
    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next, sentinel);
        size += 1;
    }

    /** Returns the first item in the list. */
    public int getFirst() {
        return sentinel.next.item;
    }

    /** Adds x to the end of the list. */
    public void addLast(int x) {
        sentinel.prev = new IntNode(x, sentinel, sentinel.prev);
        size += 1;
    }

     /** Returns the last item in the list. */
    public int getLast() {
        return sentinel.prev.item;
    }

    /** Removes the last item in the list. */
    public void removeLast() {
        sentinel.prev = sentinel.prev.prev;
    }

    /** Returns the size of the list. */
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        /* Creates a list of one integer, namely 10 */
        DLList L = new DLList();
        L.addLast(20);
        L.addLast(30);
        L.addFirst(10);
        System.out.println(L.size());
        System.out.println(L.getFirst());
        System.out.println(L.getLast());
        L.removeLast();
        System.out.println(L.getLast());
    }
}