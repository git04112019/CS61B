/*
* @Author: czahie
* @Date:   2018-06-20 14:50:30
* @Last Modified by:   czahie
* @Last Modified time: 2018-06-20 15:18:31
*/

import java.util.Comparator;

public class Dog implements Comparable<Dog> {
    private String name;
    private int size;

    public Dog(String n, int i) {
        name = n;
        size = i;
    }

    public void bark() {
        System.out.println(name + " says: bark!");
    }

    /** Returns negative number if this dog is less than the other, and so forth. */
    public int compareTo(Dog otherDog) {
        return this.size - otherDog.size;
    }

    private static class NameComparator implements Comparator<Dog> {
        public int compare(Dog a, Dog b) {
            return a.name.compareTo(b.name);
        }
    }

    public static Comparator<Dog> getNameComparator() {
        return new NameComparator();
    }
}