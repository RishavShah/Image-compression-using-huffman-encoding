import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
//import java.awt.*;
import java.io.BufferedWriter;
//import java.io.BufferedReader;
import java.io.FileWriter;
//import java.io.FileReader;
class encode
{	public static void main(String args[])throws Exception
	{	try
		{
			File f1 = new File("abcd.png"); 
      			BufferedImage img = new BufferedImage(1024, 720, BufferedImage.TYPE_INT_ARGB);
      			img = ImageIO.read(f1);
			int m=img.getWidth();
			int n=img.getHeight();
			int  a[]=new int[m*n];
			int b[][]=new int[m*n][2];
			String[] code=new String[m*n];
			int x=0,u=1,t;
		        File f2 = new File("hoff.txt");
			    if (!f2.exists()) 
					f2.createNewFile();
					
			for(int i=0;i<m;i++)
				for(int j=0;j<n;j++)
					a[x++]=(img.getRGB(i,j));
			b[0][0]=a[0];
  			for(int i=0;i<x;i++)
     			{
				int flag=1;
				for(int j=0;j<u&&flag==1;j++)
				{
					if(a[i]==b[j][0])
					{
						flag=0;
						b[j][1]++;
					}
				}
				if(flag==1)
				{
					b[u++][0]=a[i];
					b[u-1][1]++;
				}
    			 }
			for(int i=0;i<u-1;i++)
			{	
				for(int j=i+1;j<u;j++)
				{
					if(b[i][1]<b[j][1])
					{
						t=b[i][0];
						b[i][0]=b[j][0];
						b[j][0]=t;
						t=b[i][1];
						b[i][1]=b[j][1];
						b[j][1]=t;
					}
				}
			}
			for(int i=0;i<u;i++)
				code[i]="";			
			
		
			for(int i=0;i<u;i++)
			{
				code[i]=code[i]+"1";
				for(int j=i+1;j<u;j++)
					code[j]=code[j]+"0";
			}
			FileWriter fw = new FileWriter(f2.getAbsoluteFile());
        		BufferedWriter bw = new BufferedWriter(fw);
			bw.write(m+"\n"+n+"\n"+u+"\n");
			for(int i=0;i<u;i++)
			bw.write(b[i][0]+"\n"+code[i]+"\n");
			for(int i=0;i<x;i++)
			{
				   String matchColor="";
				    for(int j=0;j<u;j++)
				     {
						if(a[i]==b[j][0])
							matchColor=code[j];
				      }
				bw.write(matchColor+"\n");
			}
			bw.close();
			
		
		}
		catch(IOException e)
		{
			System.out.println("error : "+e);
		}
	}
}
			
					
