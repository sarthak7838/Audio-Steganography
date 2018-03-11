package remotescreencapture;

//this is just we are trying
//basically to display the details of each clients


 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;


public class PerformanceDetail extends JFrame
  {

    static protected JPanel allLabelPane = new JPanel();

    protected JPanel  contentPane = null;

    static protected JScrollPane labelScrollPane = null ;

  // static protected ServerGUICount = 0; // this will count

   //  GraphPanel gp = null ;
   //  private static  int noOfGraphPanel = 0 ;
    //private Toolkit itsToolkit;
      PerformanceDetail()
       {

             super(" LAN COP :: The Performance Detail of All Clients ");
              /* To make the Network monitor window fit
               * according to current screen resolution
               */
                Dimension aDimension= Toolkit.getDefaultToolkit().getScreenSize();
            // itsToolkit=Toolkit.getDefaultToolkit();


            //Container contentPane = getContentPane();
           //if this iis the first frame then


              addWindowListener(new WindowAdapter()
               {
                  public void windowClosing(WindowEvent e)
                   {
                      NWMServer.watchingPerformance = false ;
                      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                    }
                });


              setSize(450,450);


               allLabelPane.setBorder( BorderFactory.createLoweredBevelBorder() );
               allLabelPane.setLayout( new GridLayout(0,1) );

              // the Scroll Pane Related..........
               labelScrollPane = new JScrollPane(allLabelPane,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
               labelScrollPane.setPreferredSize(new Dimension(440,440));
               labelScrollPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5) );

                 //Lay out the content pane.
                 contentPane = new JPanel();
                 contentPane.setLayout(new BorderLayout());
                 setContentPane(contentPane);

                contentPane.add(labelScrollPane ,BorderLayout.CENTER );

               setVisible(true);

        }
}