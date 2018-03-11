package remotescreencapture;


 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;

public class ClientImageDisplay extends JFrame
  {
     private JScrollPane imageScrollPane = null;
     private JLabel picture;

     Toolkit toolkit = Toolkit.getDefaultToolkit();

     private JTextField clnText;
     private JTextField osNameText;
     private JTextField osVersionText;
     private JTextField osArchiText;
     private JTextField ipAddText;
     private JTextField compNameText;
     private ImageIcon firstImage = null;
     private ImageIcon image1 ;

   //   ClientImageDisplay(){}

      ClientImageDisplay(String tital)
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
                      NWMServer.task = NWMServer.SEND_IMAGE ;
                      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                    }
                });


               //Set up the picture label and put it in a scroll pane
                 firstImage = new ImageIcon("images/aish.jpg");

                picture = new JLabel(firstImage);
                picture.setPreferredSize(new Dimension(firstImage.getIconWidth(),
                                                      firstImage.getIconHeight()));
              // the Scroll Pane Related..........


               imageScrollPane = new JScrollPane(picture,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );
              // imageScrollPane.setPreferredSize(new Dimension(200,100));
               imageScrollPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5) );

               // the lowermost Information Pane.............
               JPanel infoPane = new JPanel();
               infoPane.add(new JLabel("Client No") );
               clnText = new JTextField(3);  clnText.setEditable(false);
               infoPane.add(clnText);

               infoPane.add(new JLabel(" Computer Name" ) );
               compNameText= new JTextField(6);  compNameText.setEditable(false);
               infoPane.add(compNameText);

               infoPane.add(new JLabel(" IP Address") );
               ipAddText = new JTextField(11);  ipAddText.setEditable(false);
               infoPane.add(ipAddText);

               infoPane.add(new JLabel(" Operating System") );
               osNameText = new JTextField(10);  osNameText.setEditable(false);
               infoPane.add(osNameText);

               infoPane.add(new JLabel(" Version") );
               osVersionText = new JTextField(3);  osVersionText.setEditable(false);
               infoPane.add(osVersionText);

               infoPane.setBorder( BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(3,3,3,3), BorderFactory.createRaisedBevelBorder() ) );

                 //Lay out the content pane.
                 JPanel contentPane = new JPanel();
                 contentPane.setLayout(new BorderLayout());
                 setContentPane(contentPane);

               // contentPane.add(toolBarPane, BorderLayout.NORTH);
                contentPane.add(infoPane ,BorderLayout.NORTH );
                contentPane.add(imageScrollPane ,BorderLayout.CENTER );

                //contentPane.add(toolBar2, BorderLayout.NORTH);
              setVisible(true);

        }        //Still to be defined ..............................

       public void refresh(byte[] imgBytes)
        {

              image1 = new ImageIcon( toolkit.createImage(imgBytes) );
        	  picture.setIcon(image1);
              //f.imgAvailable = true;
              picture.revalidate();

              //f.repaint();
            //image1 = toolkit.getImage("ClientImage"+image+".jpg");

            /*  older code ........slower one..........
            image1.flush();

            ImageIcon newImage = new ImageIcon(image1);

           // firstImage.setImage(image1);
            picture.setIcon(newImage);
            picture.setPreferredSize(new Dimension(newImage.getIconWidth(),
                                                newImage.getIconHeight() ));
            picture.revalidate();

            */


         }
  }