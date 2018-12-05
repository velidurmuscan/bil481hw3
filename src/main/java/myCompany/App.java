package myCompany;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

public class App {
    public static boolean search(ArrayList<Integer> array, int e) {
        System.out.println("inside search");
        if (array == null)
            return false;

        for (int elt : array) {
            if (elt == e)
                return true;
        }
        return false;
    }
    
    public static boolean doubleSearch(ArrayList<Integer> arr1, int num1, ArrayList<Integer> arr2, int num2) {
    	if(arr1==null || arr2==null)
    		return false;
    	for(int i:arr1) {
    		if(i==num1) {
    			for(int y:arr2) {
    				if(y==num2) {
    					return true;
    				}
    			}
    		}
    	}
    	return false;
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Hello, World");

        post("/compute", (req, res) -> {

            String array1_ = req.queryParams("array1");
            String num1_ = req.queryParams("num1").replaceAll("\\s", "");
            String array2_ = req.queryParams("array2");
            String num2_ = req.queryParams("num2").replaceAll("\\s", "");

            java.util.Scanner sc1 = new java.util.Scanner(array1_);
            sc1.useDelimiter("[;\r\n]+");
            java.util.ArrayList<Integer> input1List = new java.util.ArrayList<>();
            while (sc1.hasNext()) {
                int value = Integer.parseInt(sc1.next().replaceAll("\\s", ""));
                input1List.add(value);
            }
            
            int num1AsInt = Integer.parseInt(num1_);
            
            java.util.Scanner sc2 = new java.util.Scanner(array2_);
            sc2.useDelimiter("[;\r\n]+");
            java.util.ArrayList<Integer> input2List = new java.util.ArrayList<>();
            while (sc2.hasNext()) {
                int value = Integer.parseInt(sc2.next().replaceAll("\\s", ""));
                input2List.add(value);
            }
            
            int num2AsInt = Integer.parseInt(num2_);

            boolean result = App.doubleSearch(input1List, num1AsInt, input2List, num2AsInt);

            Map map = new HashMap();
            map.put("result", result);
            return new ModelAndView(map, "compute.mustache");
		
        }, new MustacheTemplateEngine());

        get("/compute", (rq, rs) -> {
            Map map = new HashMap();
            map.put("result", "not computed yet!");
            return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; // return default port if heroku-port isn't set (i.e. on localhost)
    }
}
