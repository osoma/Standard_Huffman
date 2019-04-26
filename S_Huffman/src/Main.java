import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
class Comprator implements Comparator<Node>
{
    public int compare(Node a,Node b)
    {return a.freq-b.freq;}
}

public class Main {
    public  static String readfile(String fname) throws Exception
    {
        String data="";
        data=new String(Files.readAllBytes(Paths.get(fname)));
        return data;
    }

    public static void main(String [] arg) throws Exception {

        String text= readfile("E:\\J.Workspace\\S_Huffman\\Data.txt");
        //Scanner input=new Scanner(System.in);
        //System.out.println("Enter text:");
       // text=input.nextLine();

       // char [] temp; //=text.split("");
        //temp = new char[text.length()];
        //System.out.println(temp);
        //for(int r=0;r<text.length();r++) {
          //  temp [r] =text.charAt(r);
        //}
        String [] textt=text.split("");
        Compression obj=new Compression();
        HashMap<String,Integer> data_freq=new HashMap<String, Integer>(obj.getFreq(textt));

        System.out.println("Data Frequencies: " + data_freq);

        ArrayList<Integer> freqValues=new ArrayList<Integer>(data_freq.values());
        ArrayList<String> Chars=new ArrayList<String>(data_freq.keySet());
        PriorityQueue<Node> queueNodes= new PriorityQueue<>(Chars.size(),new Comprator());

        for(int i=0;i<Chars.size();i++)
        {
            Node newNode=new Node();
            newNode.data=Chars.get(i);
            newNode.freq=freqValues.get(i);
            newNode.left=null;
            newNode.right=null;
            queueNodes.add(newNode);

        }
        Node root=null;

        while (queueNodes.size() > 1)
        {
            Node extract1 = queueNodes.peek();
            queueNodes.poll();


            Node extract2 = queueNodes.peek();
            queueNodes.poll();

            Node sum = new Node();
            sum.freq = extract1.freq + extract2.freq;
            sum.data="*";

            sum.left=extract1;
            sum.right=extract2;

            root=sum; //make sum as the root node.
            queueNodes.add(root); //add it to the priorityQueue.
        }

        obj.PrintCode(root,"");


        String encode="";
        for(int f=0 ; f<textt.length;f++)
        {
            String x=textt[f];
            encode+=obj.encodeed.get(x);

        }

        BufferedWriter brw= new BufferedWriter(new FileWriter("outFile.txt"));
        brw.write(encode);
        brw.newLine();
        brw.append(obj.Decode(queueNodes.peek(),encode));
        brw.close();
        System.out.println("the Encoded: "+encode);
        System.out.println("The Decoded: "+ obj.Decode(queueNodes.peek(),encode));
        /*PriorityQueue<Integer> d=new PriorityQueue<>();
        d.add(50);
        d.add(8);
        d.add(20);
        d.add(600);
        System.out.println(d);*/
    }
}
