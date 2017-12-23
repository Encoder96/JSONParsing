import java.io.*;
import java.lang.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject; 
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Jsonparsing
{
  public static void main(String[] args) throws java.lang.Exception,ClassNotFoundException
  {
    File[] files=new File("/home/sony/mongodb/readjson").listFiles();
    for(File file : files)
    {

      //This reads json file and write in to text file

      String filename=file.getName();
      FileReader fr=new FileReader("/home/sony/mongodb/readjson/"+filename);
      String fname="/home/sony/mongodb/writetxt/"+filename.substring(0,filename.indexOf("."))+".txt";
      File del=new File(fname);
      if(del.exists())
        del.delete();
      File textfile=new File(fname);
      textfile.createNewFile();
      FileWriter fw=new FileWriter(textfile,true);
      BufferedWriter bw=new BufferedWriter(fw);     
      String line="";

      //Reading of array of array and array of object

      if(filename.startsWith("array"))
      {
        JSONParser parser=new JSONParser();
        Object obj=parser.parse(fr);
        JSONArray outer_array=(JSONArray) obj;
        int o_arrlen=outer_array.size(); 

        // array of object

        if(filename.contains("object"))
        {
          for(int i=0;i<o_arrlen;i++)
          {
            line="";                      
            JSONObject obj1=(JSONObject) outer_array.get(i);
            line+="key is " + obj1.get("id") + " and title is " +obj1.get("title");
            if(line.length() != 0)
            {
              bw.write(line);
              bw.newLine();
            }                                 
          }
        }

        // array of array

        else
        {
          for(int i=0;i<o_arrlen;i++)
          {
            JSONArray inner_array=(JSONArray) outer_array.get(i);
            Iterator<String> inner_iterator=inner_array.iterator();
            line="";
            while(inner_iterator.hasNext())
            {
              String tmp=inner_iterator.next();
              if(tmp.length() !=0)
              {
                line+=tmp;
                line+=" ";
              }
            }
            if(line.length() != 0)
            {
              bw.write(line);
              bw.newLine();
            }
          }
        }                     
      }

      //Reading of object of array and object of object

      else
      {
        JSONParser parser=new JSONParser();
        Object obj=parser.parse(fr);
        JSONObject obj1=(JSONObject) obj;        

        //Object of array

        if(filename.contains("array"))
        {
          JSONArray arr=(JSONArray) obj1.get("preferredids");
          line="preferredids are- ";
          for(Object o:arr)
          line+=o + " ";          
          if(line.length() != 0)
          {
            bw.write(line);
            bw.newLine();
          }

          arr=(JSONArray) obj1.get("likes");        
          line="likes are- ";
          for(Object o:arr)
            line+=o + " ";          
          if(line.length() != 0)
          {
            bw.write(line);
            bw.newLine();
          }     
        }

        //object of object

        else
        {
            Map details=(Map)obj1.get("std1");
            line="Student 1 details- \n";
            Iterator<Map.Entry> itr=details.entrySet().iterator();
            while(itr.hasNext())
            {
              Map.Entry pair=itr.next();
              line+=pair.getKey() + " - " + pair.getValue() + "\n";
            }
            if(line.length() != 0)
            {
              bw.write(line);
              bw.newLine();
            }
            details=(Map)obj1.get("std2");
            line="Student 2 details- \n";
            itr=details.entrySet().iterator();
            while(itr.hasNext())
            {
              Map.Entry pair=itr.next();
              line+=pair.getKey() + " - " + pair.getValue() + "\n";
            }
            if(line.length() != 0)
            {
              bw.write(line);
              bw.newLine();
            }                          
        }
      }   
      bw.close();
    }
  }
} 
