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
 * @param <T>
 */

public class ArrayDeque<T> implements Deque<T>{
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;

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

    /** Resizes the array. For arrays of length 16 or more, usage factor should always be at least 25%. */
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        int beginning = plusOne(nextFirst);
        int end = minusOne(nextLast);
        // When the the beginning of the Deque is actually "behind" the end of it.
        // Then we need to cut the old array into 2 pieces.
        if (beginning > end) {
            int sizeOfFirstHalf = items.length - beginning;
            int sizeOfSecondHalf = size - sizeOfFirstHalf;
            System.arraycopy(items, beginning, newItems, 0, sizeOfFirstHalf);
            System.arraycopy(items, 0, newItems, sizeOfFirstHalf, sizeOfSecondHalf);
        }
        else {
            System.arraycopy(items, beginning, newItems, 0, size);
        }
        nextFirst = newItems.length - 1;
        nextLast = size;
        items = newItems;
    }


     /** Creates an empty Deque. */
    public ArrayDeque() {
        nextFirst = 0;
        nextLast = 1;
        size = 0;
        items = (T[]) new Object[8];
    }

    /** Create a Deque. */
    public ArrayDeque(T x) {
        items = (T[]) new Object[8];
        items[0] = x;
        size = 1;
        nextFirst = 7;
        nextLast = 1;
    }

    @Override
    /** Adds an item to the front of the Deque. */
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
        size = size + 1;

    }

    @Override
    /** Adds an item to the back of the Deque. */
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = x;
        nextLast = plusOne(nextLast);
        size = size + 1;
    }

    @Override
    /** Returns true if the Deque is empty, false otherwise. */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    /** Returns the number of items in the Deque. */
    public int size() {
        return size;
    }

    @Override
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

    @Override
    /** Removes and returns the first item in the Deque, if no such item exists, returns null. */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if ((float) size / items.length < 0.25 && items.length >= 16) {
            resize(items.length / 2);
        }
        int fP = plusOne(nextFirst);
        T first = items[fP];
        items[fP] = null;
        size = size - 1;
        nextFirst = fP;
        return first;
    }

    @Override
    /** Removes and returns the last item in the Deque, if no such item exists, returns null. */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if ((float) size / items.length < 0.25 && items.length >= 16) {
            resize(items.length / 2);
        }
        int lP = minusOne(nextLast);
        T last = items[lP];
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

    @Override
    /** Gets the item at the given index, if no such items exists, returns null. */
    public T get(int i) {
        if (isExist(i)) {
            return items[i];
        }
        return null;
    }
}