package remotescreencapture;

import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ScreenCaptureThread extends Thread
{

  Socket sc = new Socket();
  Robot itsRobot;
  DataOutputStream out;
  BufferedImage itsImage;

  private boolean sendJPEG = false ;

  ScreenCaptureThread(String str,Socket sock)
   {
         super(str);

         this.sc = sock ;
   }


   public void run()
   {
       //this.setPriority( (Thread.MAX_PRIORITY )-3);
       //out = new DataOutputStream(sc.getOutputStream());
       try{
            out = new DataOutputStream(sc.getOutputStream());

            itsRobot = new Robot();

           //  while( true )
           //   {
                if(sendJPEG == false)
                 {
                   itsImage = (itsRobot.createScreenCapture(new Rectangle(0,0,800,600)));
	               out.writeInt(itsImage.getWidth());
	               out.writeInt(itsImage.getHeight());

                    for(int i=0; i<itsImage.getWidth();i++)
	                 for(int j=0; j<itsImage.getHeight();j++)
	                   if((i%4==0) && (j%4 ==0))
	                       out.writeInt(itsImage.getRGB(i,j));


	                 System.out.println(" Image send");
	               }
                   else //write code to send a full screen JPEG file
                    {
                       //System.out.println(" the user windows is maximized ");
                        try
                              {
                                 Robot robot =new Robot();
                                // ObjectOutputStream f = new ObjectOutputStream( new FileOutputStream("i1.jpeg") );

                                OutputStream f = new FileOutputStream("filename1111");
                                 System.out.println("preparing to send jpeg....");
                                 //BufferedOutputStream f = new BufferedOutputStream( out );

                                 JPEGImageEncoder encoder =JPEGCodec.createJPEGEncoder( f );
                                 encoder.encode(robot.createScreenCapture(new
                                      Rectangle(Toolkit.getDefaultToolkit().getScreenSize())));

                                 f.flush();
                                 f.close();
                                 System.out.println("Going to send jpeg....");
                                 //sending the stored file...............

                                   FileInputStream insf = new FileInputStream("filename1111");
                                   int c ;
                                   while( ( c = insf.read() ) != -1 )
                                      out.writeInt( c );

                                   out.writeInt(-1);   //indicating the end  of file
                                   out.flush();
                                   insf.close();



                               } catch(AWTException e1) {System.out.println("Exception occur : " +e1);}
                             	 catch(IOException e2) {System.out.println("Exception occur : " +e2);}
                              System.out.println("Image send ");
                     }
             //  }
          }catch(IOException ioe)
	        {
	           System.out.println("Unknown host");
	         }

      	    catch(AWTException ioe)
	         {
	           System.out.println("Robot not created");
	         }

   }
   public void setSendJPEG( boolean b )
    {  sendJPEG = b;  }

}