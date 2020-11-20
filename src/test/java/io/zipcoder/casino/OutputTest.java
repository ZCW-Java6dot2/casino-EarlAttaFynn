package io.zipcoder.casino;

import io.zipcoder.casino.utilities.Output;
import org.junit.Assert;

import static org.mockito.Mockito.mock;

public class OutputTest {
    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void TestPrintToScreen(){
        Output output = new Output();
        //given
        String expected = "hello";
        //actual
        String actual = output.printToScreen(expected);
        //then
        Assert.assertEquals(expected,actual);
    }

    @org.junit.Test
    public void TestWelcomeMenu(){
        Output output = new Output();
        //given
        String expected = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n~~~~ Welcome to the Casino ~~~~\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        //actual
        String actual = output.welcomeMenu();
        //then
        Assert.assertEquals(expected,actual);
    }

   /* @org.junit.Test
    public void TestGetPlayerType(){
        Output output = new Output();
        Input input = mock(Input.class);
        when(input.getIntInput("prompt")).thenReturn(1);
        Boolean expected = true;
        Boolean actual = output.getPlayerType();
        Assert.assertEquals(,actual);
    } */
}
