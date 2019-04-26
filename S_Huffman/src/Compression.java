import jdk.jfr.Frequency;

import java.util.*;
import java.io.*;

class Node //struct
{
    int freq;
    String data;
    Node left;
    Node right;

}

public class Compression {


    public HashMap<String,Integer> getFreq(String [] text)
    {

        HashMap<String , Integer > Freq =new HashMap<String, Integer>();
        //ArrayList<Integer> FreqArray=new ArrayList<Integer>();
        ArrayList<String> data=new ArrayList<>() ;
        Collections.addAll(data,text);
        //int a= Collections.frequency(data,"a");
        //System.out.println(data);
        //FreqArray.add(Collections.frequency(data,data.get(0)));

        for(int i=0;i<text.length;i++)
        {
            Freq.put(data.get(i),Collections.frequency(data,data.get(i)));
        }
        //System.out.println(Freq.clone()); //for printing the hashmap.
        //System.out.println(Freq.values());  //for printing the the values only


        return Freq;//HashMap.
    }
    HashMap<String,String> encodeed=new HashMap<>();
    public void PrintCode(Node root,String code) throws IOException {
        if(root==null)
            return;
        if(root.data!="*")
        {   //data is the char of the node.
            System.out.println(root.data +" = "+ code);
            encodeed.put(root.data,code); //store.
           // Codes.put(root.data,code);
/***********************************************/
           // System.out.println(c);
           //return;

        }

        PrintCode(root.left, code + "0");
        PrintCode(root.right, code + "1");

        //Decode(root,c);
        //HashMap<Character,String> storedCode=new HashMap<>();

    }

    public String Decode(Node root,String s)
    {
        String Decomp_data="";
        Node current=root;
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)=='0')
                current=current.left;
            else
                current=current.right;

            if(current.left==null && current.right==null) //reached to the leaf node
            {
                Decomp_data+=current.data;
                current=root;
            }
        }
        //System.out.println("the: "+ Decomp_data);
        return Decomp_data;
    }


}
