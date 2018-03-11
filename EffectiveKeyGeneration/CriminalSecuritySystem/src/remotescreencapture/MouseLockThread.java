package remotescreencapture;

import java.awt.*;

public class MouseLockThread extends Thread
 {
    Robot r  = null ;

     MouseLockThread(String s)
      {
         super(s);
       }

     public void run()
       {
         try{
                r = new  Robot();

              //  this.setPriority( Thread.MAX_PRIORITY );

                while(true)
                 {
                    r.mouseMove(0,0);
                  }

             }catch(AWTException ioe)
	           {
	             System.out.println("Robot not created");
	           }

        }
  }