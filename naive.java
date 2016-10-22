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

public class naive 
{
	static  String s;
	public static void main (String[] args) {
	try {
		BufferedReader br = new BufferedReader(new FileReader("naivetrain.txt"));
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();
		while (line != null) {
	        sb.append(line);
        	sb.append("\n");
	        line = br.readLine();
	    	}
		s = sb.toString();
	    }
	catch(Exception e)
	{
		System.out.println ("exception");
	}
	String[] words = s.split("\n");
	int word[][]=new int[words.length][5];
	for (int i = 0; i < words.length; i++)
	{
	String c=words[i];
	String[] w=(c.split(","));
	int j=0;
    for(String str:w){
        word[i][j]=Integer.parseInt(str.trim());//Exception in this line
        j++;
		}
    }
	/*for(int i=0;i<words.length;i++)
	{
		for(int j=0;j<5;j++)
		System.out.print (word[i][j]+" ");
		System.out.println ();
	}*/
	int count1=0,count0=0;
	for(int i=0;i<words.length;i++)
	{
		if(word[i][4]==1)
		count1++;
		else 
		count0++;
	}
	int [][]class1=new int[count1][4]; 
	int [][]class0=new int[count0][4];
	int m=0,n=0;
	for(int i=0;i<words.length;i++)
	{
		if(word[i][4]==1)
		{
		class1[m][0]=word[i][0];
		class1[m][1]=word[i][1];
		class1[m][2]=word[i][2];
		class1[m][3]=word[i][3];	
		m++;
		}
		else
		{
		class0[n][0]=word[i][0];
		class0[n][1]=word[i][1];
		class0[n][2]=word[i][2];
		class0[n][3]=word[i][3];	
		n++;
		} 
	}
	/*for(int i=0;i<count1;i++)
	System.out.println ("class 1  "+class1[i][0]+"  "+class1[i][1]+"  "+class1[i][2]+"  "+class1[i][3]);
 	for(int i=0;i<count0;i++)
	System.out.println ("class 0  "+class0[i][0]+"  "+class0[i][1]+"  "+class0[i][2]+"  "+class0[i][3]);*/
	System.out.println ("count spam   "+count1+"\n    count ham   "+count0); 
	double [][] meanstd1=new double [2][4];
	double [][] meanstd0=new double [2][4];
	for(int j=0;j<4;j++)
	{
		meanstd1[0][j]=0;
		for(int i=0;i<count1;i++)
		{
			meanstd1[0][j]+=class1[i][j];	
		}
		meanstd1[0][j]/=count1;
		System.out.println ("Mean class spam : "+meanstd1[0][j]);
	}
	for(int j=0;j<4;j++)
	{
		meanstd0[0][j]=0;
		for(int i=0;i<count0;i++)
		{
			meanstd0[0][j]+=class0[i][j];	
		}
		meanstd0[0][j]/=count0;
		System.out.println ("Mean class ham : "+meanstd0[0][j]);
	}
	for(int j=0;j<4;j++)
	{
		meanstd1[1][j]=0;
		for(int i=0;i<count1;i++)
		{
			meanstd1[1][j]+=Math.pow((class1[i][j]-meanstd1[0][j]),2);
		}
		meanstd1[1][j]/=(count1-1);
		meanstd1[1][j]=Math.sqrt(meanstd1[1][j]);
		//	System.out.println ("STD class 1 : "+meanstd1[1][j]);
	}

	for(int j=0;j<4;j++)
	{
	meanstd0[1][j]=0;
	for(int i=0;i<count0;i++)
	{
		meanstd0[1][j]+=Math.pow((class0[i][j]-meanstd0[0][j]),2);
	}
	meanstd0[1][j]/=(count0-1);
	meanstd0[1][j]=Math.sqrt(meanstd0[1][j]);
	//System.out.println ("STD class 0 : "+meanstd0[1][j]);
	}

	double prob1=(double)count1/words.length;
	double prob0=1-prob1;
	
	try {
		BufferedReader br = new BufferedReader(new FileReader("naivetest.txt"));
		StringBuilder sb = new StringBuilder();
    	String line = br.readLine();
	    while (line != null) {
    	sb.append(line);
        sb.append("\n");
        line = br.readLine();
    	}
    s = sb.toString();
    //System.out.println (s);
	}
	catch(Exception e)
	{
		System.out.println ("exception");
	}
	String[] samplestring = s.split("\n");
	int wo[][]=new int[samplestring.length][5];
	for (int i = 0; i < samplestring.length; i++)
	{
	String c=samplestring[i];
	String[] w=(c.split(","));
	int j=0;
    for(String str:w){
        wo[i][j]=Integer.parseInt(str);//Exception in this line
        j++;
	}
	}
	/*for(int i=0;i<samplestring.length;i++)
	{
	for(int j=0;j<5;j++)
	System.out.print (wo[i][j]+" ");
	System.out.println ("check   ");
	}*/
	int len=samplestring.length;
	
/*
    for(int j=0;j<samplestring.length;j++){
    System.out.println (samplestring[j]);
        wo[j]=Integer.parseInt(samplestring[j].trim());
}

for(int i=0;i<5;i++)
System.out.println (wo[i]);

*/

	double[][] probab1=new double[len][4];
	double[][] probab0=new double[len][4];
	for(int k=0;k<len;k++)
	for(int i=0;i<4;i++)
	{
	double exponent = Math.exp(-(Math.pow(wo[k][i]-meanstd1[0][i],2)/(2*Math.pow(meanstd1[1][i],2))));
	probab1[k][i]= (1 / (Math.sqrt(2*Math.PI) * meanstd1[1][i])) * exponent;
	//System.out.println (probab1[k][i]);
	}
	for(int k=0;k<len;k++)
	for(int i=0;i<4;i++)
	{
	double exponent = Math.exp(-(Math.pow(wo[k][i]-meanstd0[0][i],2)/(2*Math.pow(meanstd0[1][i],2))));
	probab0[k][i]= (1 / (Math.sqrt(2*Math.PI) * meanstd0[1][i])) * exponent;
//	System.out.println (probab0[k][i]);
	}

	double [] prob1byinput=new double[len];
	double [] prob0byinput=new double[len];
	for(int k=0;k<len;k++)
	{
	prob0byinput[k]=prob0;
	prob1byinput[k]=prob1;

//System.out.println (prob1byinput[k]);
//System.out.println (prob0byinput[k]);
	}
	for(int k=0;k<len;k++)
	for(int i=0;i<4;i++)
	{
	prob1byinput[k]*=probab1[k][i];
	}
	for(int k=0;k<len;k++)
	for(int i=0;i<4;i++)
	{
	prob0byinput[k]*=probab0[k][i];
	/*System.out.println (prob1byinput);
	System.out.println (prob0byinput);*/
	}
	double [] probfinal=new double[len];
	for(int k=0;k<len;k++)
	{
	if(prob1byinput[k]>prob0byinput[k])
	{
		probfinal[k]=1;
		//System.out.println ("Spam");
	}
	else
	{
		probfinal[k]=0;
		//System.out.println ("Ham");
	}
	//System.out.println (wo[k][4]);
	}
	//System.out.println (words.length);
	//System.out.println (len);
	int yes=0,no=0;
	for(int k=0;k<len;k++)
	{
	if(probfinal[k]==wo[k][4])
	yes++;
	else
	no++;
	}

	System.out.println ("\nAccuracy :   "+((double)yes/(yes+no))*100+"%");
	}

	}
