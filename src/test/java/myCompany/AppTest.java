package myCompany;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    
	public void testSearcResult() {
		Entity e1 = new Entity(1, "Ahmet", "Can");
		Entity e2 = new Entity(2, "Ahmet", "Altinok");
		Entity e3 = new Entity(3, "Buse", "Altinok");		
		ArrayList<Entity> entityList = new ArrayList<Entity>(Arrays.asList(e1,e2,e3));
		String out = new String(App.searchResult(entityList));
		String testString = new String("1\nAhmet\nCan\n------------------------------------------------------------------------------------\n2\nAhmet\nAltinok\n------------------------------------------------------------------------------------\n3\nBuse\nAltinok\n------------------------------------------------------------------------------------\n");
		testString = testString.replaceAll("\n", "<br>");
		assertTrue(out.equals(testString));
	}
	
	public void testFirstName() {
		Entity e1 = new Entity(1, "Ahmet", "Can");
		Entity e2 = new Entity(2, "Ahmet", "Altinok");
		Entity e3 = new Entity(3, "Buse", "Altinok");		
		List<Entity> entityList = new ArrayList<Entity>(Arrays.asList(e1,e2,e3));
		List<Entity> searchList = App.search(entityList, "Ahmet", "");
        assertTrue(searchList.size()==2 && searchList.get(0).equals(e1) && searchList.get(1).equals(e2));
    }

    public void testLastName() {
		Entity e1 = new Entity(1, "Ahmet", "Can");
		Entity e2 = new Entity(2, "Ahmet", "Altinok");
		Entity e3 = new Entity(3, "Buse", "Altinok");		
		List<Entity> entityList = new ArrayList<Entity>(Arrays.asList(e1,e2,e3));
		List<Entity> searchList = App.search(entityList, "", "Altinok");
        assertTrue(searchList.size()==2 && searchList.get(0).equals(e2) && searchList.get(1).equals(e3));
    }
    
    public void testFirstNameAndLastName() {
		Entity e1 = new Entity(1, "Ahmet", "Can");
		Entity e2 = new Entity(2, "Ahmet", "Altinok");
		Entity e3 = new Entity(3, "Buse", "Altinok");		
		List<Entity> entityList = new ArrayList<Entity>(Arrays.asList(e1,e2,e3));
		List<Entity> searchList = App.search(entityList, "Ahmet", "Altinok");
        assertTrue(searchList.size()==1 && searchList.get(0).equals(e2));
    }
    
    public void testApproximatelyMatch() {
    	String original = "Tufail";
    	String s1 = "Tuail";
    	String s2 = "Tufil";
    	String s3 = "Ufail";
    	String s4 = "Tfail";
    	String s5 = "Tufaile";
    	assertTrue(App.approximatelyMatch(original, s1) && App.approximatelyMatch(original, s2) && App.approximatelyMatch(original, s3) && App.approximatelyMatch(original, s4) && App.approximatelyMatch(original, s5));
    }

}
