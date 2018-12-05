package myCompany;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
	
	
	public void testFound() {
        ArrayList<Integer> array1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> array2 = new ArrayList<>(Arrays.asList(5, 6, 7, 8));
        assertTrue(new App().doubleSearch(array1, 4, array2, 8));
    }

    public void testNotFound1() {
        ArrayList<Integer> array1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> array2 = new ArrayList<>(Arrays.asList(5, 6, 7, 8));
    	assertFalse(new App().doubleSearch(array1, 0, array2, 8));
    }
    
    public void testNotFound2() {
        ArrayList<Integer> array1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> array2 = new ArrayList<>(Arrays.asList(5, 6, 7, 8));
    	assertFalse(new App().doubleSearch(array1, 4, array2, 9));
    }

    public void testEmptyArray1() {
        ArrayList<Integer> array1 = new ArrayList<>();
        ArrayList<Integer> array2 = new ArrayList<>(Arrays.asList(5, 6, 7, 8));
    	assertFalse(new App().doubleSearch(array1, 4, array2, 8));
    }
    public void testEmptyArray2() {
        ArrayList<Integer> array1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> array2 = new ArrayList<>();
    	assertFalse(new App().doubleSearch(array1, 4, array2, 8));
    }

    public void testNull1() {
        ArrayList<Integer> array2 = new ArrayList<>(Arrays.asList(5, 6, 7, 8));
    	assertFalse(new App().doubleSearch(null, 4, array2, 8));
    }
    
    public void testNull2() {
        ArrayList<Integer> array1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
    	assertFalse(new App().doubleSearch(array1, 4, null, 8));
    }
}
