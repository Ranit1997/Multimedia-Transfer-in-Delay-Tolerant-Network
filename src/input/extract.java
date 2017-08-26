package input;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
public class extract {
	public static void grayscale()throws IOException
	{
	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat frame=new Mat();int i=0,j=0;String strr,str,strrr;double row,col,gray;String k;
		 
			 strr="C:\\Mini_Project\\One Simulator Media\\src\\input\\content\\image.jpg";
	        frame = Highgui.imread(strr); 
			
			Size size =frame.size();
			row=size.height;col=size.width;
			FileOutputStream fos = new FileOutputStream("C:\\Mini_Project\\One Simulator Media\\src\\input\\content\\image_data.txt");
			OutputStreamWriter osw=new OutputStreamWriter(fos);
			BufferedWriter bw=new BufferedWriter(osw);
			for (i = 0; i <row; i++)
			    for (j = 0; j <col; j++) {
			        double[] data = frame.get(i, j);   //Editing image
			        gray=(data[0]+data[1]+data[2])/3;
			        k=Double.toString(gray);
			        bw.write(k+" ");
					
			       }
			
			bw.close();
			 osw.close();
			 fos.close();		
	}
	}