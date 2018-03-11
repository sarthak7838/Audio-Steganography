package remotescreencapture;

import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.*;

public class GetImageThread extends Thread
{

  Socket sc = new Socket();
  GraphPanel gp ;
  DataInputStream in;
 // BufferedImage itsImage;

  //ServerGUI thisClientUI = null ;
  private int clientNo ;
// private ClientImageDisplay cid = new ClientImageDisplay(); ;

   boolean enterMaxi = false ;

   boolean isMaximize = false;  //this will indicate whether we have to get bmp or jpg
                                // and on the basis of this we will display image

  GetImageThread( String str,Socket sock,GraphPanel gpanel,int cln )
   {
         super(str);

         this.sc = sock ;
         gp = gpanel;
       //  itsImage = image ;
         clientNo = cln;
   }


   public void run()
   {

       //out = new DataOutputStream(sc.getOutputStream());
      // this.setPriority( ( Thread.MAX_PRIORITY )-3  );


       try{
              in = new DataInputStream(sc.getInputStream());




              while( true )
              {

                 int imgSize = in.readInt();

                 System.out.println("Image Size"+imgSize);

                 byte[] imgBytes = new byte[imgSize];

                 int bytesCame = 0;

                   while(bytesCame < imgSize)
                    {
                      bytesCame += in.read(imgBytes,bytesCame,in.available());
                     }

                  System.out.println("Image Came");





                 if( NWMServer.task != NWMServer.SEND_IMAGE_FULL  )
                  {
                       gp.refresh(imgBytes,imgSize);

                   }
                   else{
                         System.out.println(" The Clients windows is Maximized ");

                         ClientImageDisplay  cid =  gp.getClientsCID();

                         cid.refresh(imgBytes);


                       }  //write code for getting the full JPEGfile and display it in new GUI

               }
          }catch(IOException ioe)
	        {
	           System.out.println("Unknown host");
	         }



   }

  public void setMaximize( boolean b )
   {  isMaximize = b ;
       System.out.println(" The Clients windows is Maximized ");
     }

 public boolean getMaximize( )
   {  return isMaximize ;  }

}