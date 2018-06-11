/*
* @Author: caizhe
* @Date:   2018-06-11 16:40:31
* @Last Modified by:   caizhe
* @Last Modified time: 2018-06-11 17:10:28
*/

/* Invarints:
* The position of the next item to be inserted (using addLast) is always size.
* The number of items in the AList is always size.
* The position of the last item in the list is always size - 1.
*/

public class AList<Item> {
    private int size;
    private Item[] items;
    
    /** Creates an empty list. */
    public AList() {
        size = 0;
        items = (Item[]) new Object[100];
    }

    /** Resizes the list. */
    private void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;

    }
    
    /** Inserts x into the back of the list. */
    public void addLast(Item x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[size] = x;
        size += 1;
    }
    
    /** Gets the last item in the list; */
    public Item getLast() {
        return items[size - 1];
    }

    /** Gets the ith item of the list. */ 
    public Item get(int i) {
        return items[i - 1];
    }

    /** Returns the number of the item in the list. */
    public int size() {
        return size;
    }

    /** Removes the last item in the list and 
    * returns the deleted item. */
    public Item removeLast() {
        Item last = items[size];
        items[size] = null;
        size -= 1;
        return last;
    }

    public static void main(String[] args) {
        AList<Integer> a = new AList<>();
        a.addLast(5);
        System.out.println(a.get(1));
        int i = 0;
        // test 
        while (i < 1000000) {
            a.addLast(i);
            i += 1;
        }
        System.out.println(a.get(1000));
    }

}