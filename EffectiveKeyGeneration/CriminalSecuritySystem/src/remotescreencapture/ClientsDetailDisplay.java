package remotescreencapture;

import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;

public class ClientsDetailDisplay extends JFrame
  {
    public static JTextArea textArea = new JTextArea();
    private JScrollPane areaScrollPane = null;

   //   ClientImageDisplay(){}

      ClientsDetailDisplay(String tital)
       {

             super(tital);
              /* To make the Network monitor window fit
               * according to current screen resolution
               */
             Dimension aDimension= Toolkit.getDefaultToolkit().getScreenSize();

             this.setBounds(20,20,(int)aDimension.getWidth()-30,(int)aDimension.getHeight()-30 );

            // this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

              //testing...........
              addWindowListener(new WindowAdapter()
               {
                  public void windowClosing(WindowEvent e)
                   {
                     // NWMServer.task = NWMServer.SEND_IMAGE ;
                      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                    }
                });

                textArea.setEditable(false);
               JScrollPane areaScrollPane = new JScrollPane(textArea);
               areaScrollPane.setVerticalScrollBarPolicy(
                                   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                areaScrollPane.setPreferredSize(new Dimension(350, 300));
                areaScrollPane.setBorder(
                          BorderFactory.createCompoundBorder(
                                 BorderFactory.createCompoundBorder(
                                             BorderFactory.createTitledBorder(" Your Message "),
                                             BorderFactory.createEmptyBorder(15,5,5,5)),
                                             areaScrollPane.getBorder()));


              //Lay out the content pane.
                //Container contentPane = new getContentPane();
              JPanel contentPane = new JPanel();
                  contentPane.setLayout( new BorderLayout() );

             // contentPane.setPreferredSize(new Dimension(450, 400));
             //  contentPane.add(toolBar, BorderLayout.NORTH);
               contentPane.add(areaScrollPane, BorderLayout.CENTER);
              //contentPane.add(buttonPane , BorderLayout.SOUTH);

             setContentPane(contentPane);


             textArea.append("Client no \t      Computer Name \t   IP Address   \t\t       Operating System"+"\n");
              textArea.append("======== \t       =============\t    ==========  \t       ==============="+"\n\n");


                //contentPane.add(toolBar2, BorderLayout.NORTH);
              setVisible(true);

        }        //Still to be defined ..............................

      public static synchronized void update(String updateString)
       {
         textArea.append(updateString+"\n");
       }

  }