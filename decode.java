import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
//import java.awt.*;
//import java.io.BufferedWriter;
import java.io.BufferedReader;
//import java.io.FileWriter;
import java.io.FileReader;

class decode
{	public static void main(String args[])throws Exception
	{	try
		{	int m,n,u;
			BufferedReader br = new BufferedReader(new FileReader("hoff.txt"));
			m=Integer.parseInt(br.readLine());
			n=Integer.parseInt(br.readLine());
			u=Integer.parseInt(br.readLine());
			int arr[]=new int[u+1];
			String code[] = new String[u+1];
			for(int i=0;i<u;i++)
			{
				arr[i]=Integer.parseInt(br.readLine());
				code[i]=br.readLine();
			}
			File f1 = new File("abcd.png");
			if (!f1.exists()) {
				f1.createNewFile();
			}
 
      			BufferedImage img = new BufferedImage(m, n, BufferedImage.TYPE_INT_ARGB);
			img = ImageIO.read(f1);
			
			for (int i = 0; i < m; i++) {
        		for (int j = 0; j < n; j++) {
					int pi=0;
					String col=br.readLine();
					for(int k=0;k<u;k++)
					{
						if(col.compareTo(code[k])==0)
						{
							pi=arr[k];
							break;
						}
					 }
					img.setRGB(i, j, pi);
					
				}
			}
			br.close();
		
		File f2 = new File("compress.png");  
      		ImageIO.write(img, "png", f2);
      		System.out.println("Writing complete.");
		}
		catch(Exception e)
		{
			System.out.println("error : "+e);
		}
	}
}
		
