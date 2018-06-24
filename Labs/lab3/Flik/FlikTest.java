import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {
    @Test
    public void isSameNumberTest() {
        int a = 1;
        int b = 1;
        assertTrue(Flik.isSameNumber(a, b));
    }
}
