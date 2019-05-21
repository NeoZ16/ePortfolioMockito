import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DemockcracyTest {
    President mockPresident;
    Demockcracy demockcracy;

    @Before
    public void setup(){
        mockPresident = mock(President.class);
        demockcracy = new Demockcracy(mockPresident);

    }

    @Test
    public void testPlay(){
        when(mockPresident.playGolf()).thenReturn(10);
        assertEquals(10, mockPresident.playGolf());

        verify(mockPresident, atLeastOnce()).playGolf();
    }

    @Test
    public void testGetPresident(){
        assertEquals(demockcracy.getPresident(), mockPresident);
    }

    @Test
    public void testStateVisit(){
        demockcracy.stateVisit();

        verify(mockPresident, atLeastOnce()).greet();
    }

    @Test
    public void testScandal(){
        when(mockPresident.resign()).thenReturn("Ich bin zurückgetreteten");

        String dummy = demockcracy.scandal();
        System.out.println(dummy);
        assertEquals(dummy, "Ich bin zurückgetreteten");
        verify(mockPresident).resign();
    }

    @Test
    public void testPassbill()throws Exception{
        doThrow(new Exception("Too long didnt read")).when(mockPresident)
                .signBill(argThat( str -> str.length() > 5));
        //argThat with ArgumentMatcher
        /*
        doThrow(new Exception("Too long didnt read")).when(mockPresident)
                .signBill(argThat(new ArgumentMatcher<String>() {
                    @Override
                    public boolean matches(String s) {
                        return false;
                    }
                }));
         */

        assertEquals(demockcracy.passBill("1234"), true);
        assertEquals(demockcracy.passBill("1234567"),false);

        verify(mockPresident, times(2)).signBill(anyString());
    }


}
