/*
* @Author: czahie
* @Date:   2018-06-20 15:11:07
* @Last Modified by:   czahie
* @Last Modified time: 2018-06-20 15:18:02
*/

import java.util.Comparator;

public class DogLauncher {
    public static void main(String[] args) {
        Dog d1 = new Dog("Elyse", 3);
        Dog d2 = new Dog("Sture", 9);
        Dog d3 = new Dog("Benjamin", 15);
        Dog[] dogs = new Dog[]{d1, d2, d3};

        Comparator<Dog> nc = Dog.getNameComparator();

        if (nc.compare(d1, d3) > 0) {
            d1.bark();
        }
        else {
            d3.bark();
        }
    }
}
