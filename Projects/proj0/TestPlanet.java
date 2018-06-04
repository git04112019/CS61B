import java.math.*;

/**
 * Test Planet, written by myself
 */

public class TestPlanet {
    public static void main(String[] args) {
        Planet p1 = new Planet(0, 0, 1, 1, 5, "jupiter.gif");
        Planet p2 = new Planet(1, 1, 2, 2, 10, "jupiter.gif");
        System.out.println(p1.calcForceExertedBy(p2));
        System.out.println(p2.calcForceExertedBy(p1));
    }
}
