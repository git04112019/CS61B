/*
* @Author: czahie
* @Date:   2018-06-15 09:31:12
* @Last Modified by:   czahie
* @Last Modified time: 2018-06-15 09:31:12
*/

/**
 * Invariants:
 * 1. addFirst always adds an item at the position of nextFirst;
 * 2. addLast always adds an item at the position of nextLast;
 * 3. The number of items in the deque is always size;
 * @param <Item>
 */

public class ArrayDeque<Item> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private Item[] items;

    /** Returns the index immediately “before” a given index. */
    private int minusOne(int index) {
        int i = index - 1;
        if (i < 0) {
            i = items.length - 1;
        }
        return i;
    }

    /** Returns the index immediately "after" a given index. */
    private int plusOne(int index) {
        int i = index + 1;
        if (i == items.length) {
            i = 0;
        }
        return i;
    }


     /** Creates an empty Deque. */
    public ArrayDeque() {
        nextFirst = 0;
        nextLast = 1;
        size = 0;
        items = (Item[]) new Object[8];
    }

    /** Create a Deque. */
    public ArrayDeque(Item x) {
        items = (Item[]) new Object[8];
        items[0] = x;
        size = 1;
        nextFirst = 7;
        nextLast = 1;
    }

    /** Adds an item to the front of the Deque. */
    public void addFirst(Item x) {
        items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
        size = size + 1;

    }

    /** Adds an item to the back of the Deque. */
    public void addLast(Item x) {
        items[nextLast] = x;
        nextLast = plusOne(nextLast);
        size = size + 1;
    }

    /** Returns true if the Deque is empty, false otherwise. */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /** Returns the number of items in the Deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the Deque from first to last, separated by a space. */
    public void printDeque() {
        int i = 0;
        int firstPos = plusOne(nextFirst);
        while (i < size) {
            System.out.print(items[firstPos] + " ");
            firstPos = plusOne(firstPos);
            i = i + 1;
        }
    }

    /** Removes and returns the first item in the Deque, if no such item exists, returns null. */
    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int fP = plusOne(nextFirst);
        Item first = items[fP];
        items[fP] = null;
        size = size - 1;
        nextFirst = fP;
        return first;
    }

    /** Removes and returns the last item in the Deque, if no such item exists, returns null. */
    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        int lP = minusOne(nextLast);
        Item last = items[lP];
        items[lP] = null;
        size = size - 1;
        nextLast = lP;
        return last;
    }

    /** Returns true if the ith item exists, false otherwise. */
    private boolean isExist(int i) {
        if (i > nextFirst || i < nextLast) {
            return true;
        }
        return false;
    }

    /** Gets the item at the given index, if no such items exists, returns null. */
    public Item get(int i) {
        if (isExist(i)) {
            return items[i];
        }
        return null;
    }
}