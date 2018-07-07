import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void randomTest1() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();

        String msg = "\n";

        for (int i = 0; i < 10; i++) {
            Integer randomNum = StdRandom.uniform(100);
            ads1.addFirst(randomNum);
            sad1.addFirst(randomNum);
            msg = msg + "addFirst(" + randomNum + ")\n";
        }

        for (int i = 0; i < 10; i++) {
            msg = msg + "removeFirst()\n";
            assertEquals(msg, ads1.removeFirst(), sad1.removeFirst());
            msg = msg + "removeLast()\n";
            assertEquals(msg, ads1.removeLast(), sad1.removeLast());
        }
    }
}
