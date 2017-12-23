import java.io.*;
import java.lang.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONCreate
{
	public static void main(String[] args)throws IOException,java.lang.Exception,ClassNotFoundException
	{

		// contains both object of object and objec of array;

		File jsample=new File("/home/sony/mongodb/readjson/objectexm.json");
		jsample.createNewFile();
		FileWriter fw=new FileWriter(jsample);
		BufferedWriter bw=new BufferedWriter(fw);
		JSONObject obj=new JSONObject();

		//object

		obj.put("name","rahul");
		obj.put("age",25);
		obj.put("mobile_no","9999999999");

		//object of object

		Map hmap=new HashMap<String,String>();
		hmap.put("houseno","10-89");		
		hmap.put("Street","Dalal");
		hmap.put("pin_code",256000);
		obj.put("address",hmap);

		//object of array

		JSONArray arr=new JSONArray();
		arr.add("Cricket");
		arr.add("Politics");
		arr.add("SocialWork");
		obj.put("likes",arr);
		String line=JSONValue.toJSONString(obj);
		bw.write(line);
		bw.close();

		// contains both array of object and array of array;

		jsample=new File("/home/sony/mongodb/readjson/arrexm.json");
		jsample.createNewFile();
		fw=new FileWriter(jsample);
		bw=new BufferedWriter(fw);
		JSONArray ob=new JSONArray();

		//array of array

		JSONArray obj1=new JSONArray();
		obj1.add("hello");
		obj1.add("walla-walla");
		obj1.add("hello");
		ob.add(obj1);

		//array of object
		JSONObject o=new JSONObject();
		o.put("name","Arun");
		o.put("age",30);
		ob.add(o);

		//array of array of object

		hmap=new HashMap<String,String>();
		hmap.put("houseno","10-89");		
		hmap.put("Street","Dalal");
		hmap.put("pin_code",256000);
		obj1.add(hmap);

		line=JSONValue.toJSONString(ob);
		bw.write(line);
		bw.close();		
	}
}
