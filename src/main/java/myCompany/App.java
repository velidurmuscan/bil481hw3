package myCompany;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;


public class App {
	
    public static boolean approximatelyMatch(String s1, String s2) {
    	if(s1.equalsIgnoreCase(s2))
    		return true;
    	for(int i=0; i<s1.length(); i++) {
    		String s1_ = s1.substring(0, i) + s1.substring(i+1, s1.length());
    		if(s1_.equalsIgnoreCase(s2))
    			return true;
    	}
    	for(int i=0; i<s2.length(); i++) {
    		String s2_ = s2.substring(0, i) + s2.substring(i+1, s2.length());
    		if(s2_.equalsIgnoreCase(s1))
    			return true;
    	}
    	return false;
    }
    
    public static List<Entity> search(List<Entity> entityList, String firstName, String lastName) {	
    	List<Entity> returnList = new ArrayList<Entity>();    	
        if(firstName.equals("") && lastName.equals("")) {	// None of FIRSTNAME and LASTNAME is entered
        }
        else if(firstName.equals("")) {	// Only LASTNAME is entered
        	for(Entity e : entityList) {
        		if(approximatelyMatch(e.getLastName(),lastName))
        			returnList.add(e);
        	}
        }
        else if(lastName.equals("")) { // Only FIRSTNAME is entered
        	for(Entity e : entityList) {
        		if(approximatelyMatch(e.getFirstName(),firstName))
        			returnList.add(e);
        	}
        }
        else {	// Both of FIRSTNAME and LASTNAME are entered
        	for(Entity e : entityList) {
        		if(approximatelyMatch(e.getFirstName(),firstName) && approximatelyMatch(e.getLastName(),lastName))
        			returnList.add(e);
        	}
        }       
    	return returnList;  
    }
    
    public static String searchResult(List<Entity> entityList) {
    	StringBuilder sb = new StringBuilder();
    	for(Entity e : entityList) {
    		sb.append(e.toString());
    		sb.append("\n------------------------------------------------------------------------------------\n");
    	}
//    	return sb.toString();
    	return sb.toString().replaceAll("\n", "<br>");
    }
    
    public static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; // return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {
	    
        port(getHerokuAssignedPort());

        post("/search", (req, res) -> {
        	
        	SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        	List<Entity> entityList = null;
        	try {
    	        SAXParser saxParser = saxParserFactory.newSAXParser();
    	        MyHandler handler = new MyHandler();
    	        saxParser.parse(new File("EEAS.xml"), handler);
    	        entityList = handler.getEntityList();
    	        for(Entity ent : entityList)
    	            System.out.println(ent);
    	    } catch (ParserConfigurationException | SAXException | IOException e) {
    	        e.printStackTrace();
    	    }
        	
            String firstname_ = req.queryParams("firstNameParam");
            String lastname_ = req.queryParams("lastNameParam");

            String result = App.searchResult(App.search(entityList, firstname_, lastname_));	// All the computation is done here

            Map map = new HashMap();
            map.put("result", result);
            return new ModelAndView(map, "search.mustache");
		
        }, new MustacheTemplateEngine());

        get("/search", (rq, rs) -> {
            Map map = new HashMap();
            map.put("result", "not computed yet!");
            return new ModelAndView(map, "search.mustache");
        }, new MustacheTemplateEngine());
    }
}