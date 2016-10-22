import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.*;


///////////////////////AddStudent for Adding any member//////////////menubar.addmem////toolbar.button[5]///////////////////////////


public class contentbased 
{
		static  String s;
	String posvalue="Student";																			//For Student or Faculity
	
	
	
	
	
////////////////////////////////////Constructor////////////////////////////////////////////////////////////////////////////////////
	
	
	

	
public static void main (String[] args) {
try {
	BufferedReader br = new BufferedReader(new FileReader("inputcontentbased.txt"));

    StringBuilder sb = new StringBuilder();
    String line = br.readLine();

    while (line != null) {
        sb.append(line);
        sb.append("\n");
        line = br.readLine();
    }
    s = sb.toString();
    System.out.println (s);
}
catch(Exception e)
{
	System.out.println ("exception");
		}
		
		
		
	HashMap<String,Integer> hm=new HashMap<String,Integer>();
		
Set<String> uniqueWords = new HashSet<String>();
	String[] words = s.split(" ");
for (int i = 0; i < words.length; i++)
{
    uniqueWords.add(words[i]);
}

Iterator iterator = uniqueWords.iterator();
   while (iterator.hasNext()){
  // System.out.println("Value: "+iterator.next() + " ");  
   	hm.put(iterator.next().toString(),0);
   }
//for(Set.) 
//{
//	hm.put(word,0);
//	System.out.println (word);
//}

int c=0;
//for(String word : words) 
//for(int j=0; j<words.length();j++){
  //       if(s.contains(word))
    //     {
     //   
       //  	c=hm.get(word);
         //	c++;
         	//hm.put(word,c);
         	//System.out.println (c);
        
      //   } }
    //  System.out.println (words.length);
     
     int size=hm.size();
     //System.out.println("size"+size);
     
    for (Map.Entry<String, Integer> entry : hm.entrySet()) {
    	
    	String x=entry.getKey();
    //	System.out.println (x);
    	for(int i=0;i<words.length;i++) 
    	{
    	//	System.out.println (words[i]);
    		if(x==words[i])
    		//{ System.out.println (words[i]+"\n");
    		{//	System.out.println ("yes\n");
    		c=hm.get(words[i]);
    	//	System.out.println (c);
         	c++;
         	hm.put(words[i],c);
      //   	System.out.println (c);
        //	 System.out.println(entry.getKey()+" : "+entry.getValue());
    			}
    		}
    //System.out.println(entry.getKey()+" : "+entry.getValue());
}


















for (Map.Entry<String, Integer> entry : hm.entrySet()) {
String x=entry.getKey();

int i = 0;
Pattern p = Pattern.compile(x);
Matcher m = p.matcher( s );
while (m.find()) {
    i++;
}
System.out.println (x+"  "+i);
}




/*for (Map.Entry<String, Integer> entry : hm.entrySet()) 
{
	System.out.println (entry.getKey());
}

*/



















































String [][] content=null;
int count=0;
	try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=database.mdb");
			Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs;
			rs=stmt.executeQuery("Select * from spamnaive");
			
			
			rs.afterLast();
			int rownum=0;
			
			if(rs.previous())
				count=rs.getRow();
			
		
			
			rs.beforeFirst();
			if(count!=0)
				{
					content=new String[count][2];
					while(rs.next())
						{
							content[rownum][0]=""+rs.getString("word");
							content[rownum][1]=""+rs.getString("count");
							rownum++;
						}
				}
			else
				{
				System.out.println ("exception");
					content=new String[1][2];
					content[0][0]=" ";
					content[0][1]=" ";

				}

	
			stmt.close();
			con.close();
			
		}

	catch (Exception e)
		{
			System.out.println ("Exception");
			e.printStackTrace();
		}
/*for(int i=0;i<count;i++)
{
	System.out.println (content[i][0]+"         "+content[i][1]);
}

*/



String [][] content1=null;
int count1=0;
	try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=database.mdb");
			Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs1;
			rs1=stmt.executeQuery("Select * from hamnaive");
			
			
			rs1.afterLast();
			int rownum1=0;
			
			if(rs1.previous())
				count1=rs1.getRow();
			
		
			
			rs1.beforeFirst();
			if(count1!=0)
				{
					content1=new String[count1][2];
					while(rs1.next())
						{
							content1[rownum1][0]=""+rs1.getString("word");
							content1[rownum1][1]=""+rs1.getString("count");
							rownum1++;
						}
				}
			else
				{
				System.out.println ("exception");
					content1=new String[1][2];
					content1[0][0]=" ";
					content1[0][1]=" ";

				}

	
			stmt.close();
			con.close();
			
		}

	catch (Exception e)
		{
			System.out.println ("Exception");
			e.printStackTrace();
		}

/*for(int i=0;i<count1;i++)
{
	System.out.println (content1[i][0]+"         "+content1[i][1]);
}
*/




























String [][]fina=new String[size][5];
for(int i=0;i<size;i++)
{
	for(int j=0;j<5;j++)
	{
		fina[i][j]="0";
	}
}






String map[][]=new String [size][2];
int n1=0;
for (Map.Entry<String, Integer> entry : hm.entrySet()) {
    	String x=entry.getKey();
    	map[n1][0]=x;
    	map[n1][1]=entry.getValue().toString();
    	n1++;
}
//for(int i=0;i<size;i++)
{
	//System.out.println ("\n"+map[i][0]+"  "+map[i][1]);
}



















int n=-1;
for (int j=0;j<size;j++) {
	n++;
    	String x=map[j][0];
    	fina[n][0]=x;
    	fina[n][1]=map[j][1];
    	for(int i=0;i<content.length;i++) 
    	{
    	
    		if(x.equalsIgnoreCase(content[i][0]))
	  		{
    		fina[n][2]=content[i][1];
    		}
    	}
    	for(int i=0;i<content1.length;i++) 
    	{
    	
    		if(x.equalsIgnoreCase(content1[i][0]))
	  		{
    		fina[n][3]=content1[i][1];
    		}
    	}
    	
}





for(int i=0;i<size;i++)
{
//	System.out.println (Math.abs(Integer.parseInt(fina[i][2]))+"    "+Math.abs(Integer.parseInt(fina[i][3])));
	if((Math.abs(Integer.parseInt(fina[i][2])-Integer.parseInt(fina[i][1]))<(Math.abs(Integer.parseInt(fina[i][3])-Integer.parseInt(fina[i][1])))))
	{
		fina[i][4]="1";
	}
	else
	if((Math.abs(Integer.parseInt(fina[i][2])-Integer.parseInt(fina[i][1]))>(Math.abs(Integer.parseInt(fina[i][3])-Integer.parseInt(fina[i][1])))))
		fina[i][4]="0";
	else
		fina[i][4]="-1";
}



/*for(int i=0;i<size;i++)
{
	System.out.println ("\n"+fina[i][0]+"  "+fina[i][1]+"   "+fina[i][2]+"   "+fina[i][3]+"  "+fina[i][4]);
}*/







int spam=0,ham=0;

for(int i=0;i<size;i++)
{

	if((Integer.parseInt(fina[i][4]))==1)
		spam++;
	else
		if((Integer.parseInt(fina[i][4]))==0)
		ham++;
}

System.out.print ("\nSpam = "+spam+"words"+"\n"+"Ham = "+ham+"words\nResult :    ");
System.out.println (spam>ham?"spam":"ham");




}
		//	String[] words = s.split(" ");
		//for(String word : words) {
		//	System.out.println (word);
		
		






















































}
	
//////////////////////////////////////////////////////Action Performed//////////////////////////////////////////////////////////////
	