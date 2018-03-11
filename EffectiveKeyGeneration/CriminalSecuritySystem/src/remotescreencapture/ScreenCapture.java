package remotescreencapture;




import java.awt.*;
import java.awt.image.*;
import java.io.*;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ScreenCapture
{

  public static void ScreenCapture(int seconds,String filename)
   {
     Robot robot = null;
      // remember to catch AWTException
     try {
          robot =new Robot();
      	  // delay the time
	  robot.delay(seconds);

          // encode to jpg file
          OutputStream f = new FileOutputStream(filename);
          JPEGImageEncoder encoder =JPEGCodec.createJPEGEncoder(f);
          encoder.encode(robot.createScreenCapture(new
          Rectangle(Toolkit.getDefaultToolkit().getScreenSize())));
          f.close();
     	 }
     	 catch(AWTException e1) {}
     	 catch(IOException e2) {}

   }
 	// Usage
   private static void usage() 
    {
      	System.out.println("Usage:java ScreenCapture [-d seconds] [-f filename]");
  	System.out.println("\t-d Seconds to delay before capturing screen");
  	System.out.println("\t-f JPG filename to save");
  	System.exit(0);
    }
  public static void main(String args[]) 
   {
    int s = 10;
    String filename = "ScreenCapture.jpg";

    // determine the arguments
   
/* end else*/
  ScreenCapture(s * 1000, filename);
  System.exit(0);
 }/* end main*/

}/* end class*/

