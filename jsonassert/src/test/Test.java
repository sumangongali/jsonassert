package test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;

public class Test {
	
	public static String readFile(String pathname) throws IOException {

        File file = new File(pathname);
        StringBuilder fileContents = new StringBuilder((int) file.length());
        Scanner scanner = new Scanner(file);
        String lineSeparator = System.getProperty("line.separator");

        try {
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + lineSeparator);
            }
            return fileContents.toString();
        } finally {
            scanner.close();
        }
    }
	 public String testSimple() throws IOException, JSONException {
	        //String result = getJSONResult("/user/1.json");
	        String difmsg = "";
	        boolean strict=false;
	        String ajson ,bjson;

	        ajson = Test.readFile("work/a.json");
	        bjson = Test.readFile("work/b.json");
	        
	       // System.

	     //   JSONAssert.assertEquals(ajson, bjson, false);

	       // JSONCompareResult jrs= JSONCompare.compareJSON(ajson,bjson,strict);
	try {
	    String msg="myexception";
	  //  throw new JSONException(msg);
	    //JSONAssert.
	    JSONAssert.assertEquals(ajson, bjson, true, ".#", " \n\t/-.:"); // Pass
	}catch(AssertionError e)
	{
	    System.out.println("====>"+ e.getMessage());
	    System.out.println("========>"+e.getLocalizedMessage());
	    difmsg= e.getMessage();
	}
	  return difmsg;  }
	
	
	public static void main(String[] args) throws IOException, JSONException {
		Test t = new Test();
		t.testSimple();
		
	}

}
