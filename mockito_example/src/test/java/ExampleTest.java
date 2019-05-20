import org.junit.Test;
import java.util.Random;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ExampleTest {

    @Test
    public void someTestMethod(){
        Random random = mock(Random.class);
        when(random.nextInt()).thenReturn(1);
        doReturn(true).when(random).nextBoolean();

        assertEquals(1, random.nextInt());
        assertTrue(random.nextBoolean());
        verify(random).nextInt();

    }
}
