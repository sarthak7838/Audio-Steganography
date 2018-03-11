package remotescreencapture;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.accessibility.Accessible;

import java.beans.*;
import java.awt.event.*;

class GraphPanel extends JPanel implements MouseListener,FocusListener,Accessible
  {
    private JLabel picture;
    private ImageIcon image;

    private int clientNo = 0 ;
  	private String osName = "Windows 98";
  	private String osArchi = "x86" ;
  	private String osVersion ="5.1" ;
  	private String ipAdd ="127.0.1.1";
  	private String computerName = "meenu " ;


    private Toolkit toolkit = Toolkit.getDefaultToolkit();

   // private ServerGUI imageUI = null;
    private ClientImageDisplay cid ; // this will indicate that this Graph Panel has created a new
                                            // ClientImageDisply and on that we have to Display image......
   // GraphPanel() {}

  	public GraphPanel(BufferedImage theImage)
  	 {
  	  // itsImage = theImage;
  	   this.setBorder(BorderFactory.createLineBorder(Color.white,2));
  	 }
  	 public GraphPanel( int cln,String osn ,String osv ,String ipa )
  	  {
        // this.setName(cln);
         ipAdd = ipa;
         clientNo = cln ;
         //itsImage = theImage;
         osName = osn ;
         osVersion = osv ;
         setFocusable(true);
        addMouseListener(this);
        addFocusListener(this);


  	   this.setLayout(new BorderLayout() );
  	     //firstImage = new ImageIcon("images/aish.jpg");
  	    picture = new JLabel( new ImageIcon("images/aish.jpg") );

        this.add(picture,BorderLayout.CENTER);
        /*
  	    Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        Border loweredbevel = BorderFactory.createLoweredBevelBorder();

        imagePanel = new JPanel();
        imagePanel.setBorder( BorderFactory.createCompoundBorder(
			                                      raisedbevel, loweredbevel) );
		imagePanel.setPreferredSize( new Dimension(185,185));
		imagePanel.setMaximumSize(new Dimension(185,185) );
		imagePanel.setMinimumSize(new Dimension(185,185) );
        imagePanel.setOpaque(true);

        this.add(imagePanel,BorderLayout.CENTER);

        //JLabel  clientNo = new JLabel(" Client No "+ cln);
        //this.add(clientNo,BorderLayout.SOUTH);

         */
        this.setPreferredSize( new Dimension(190,190) );
        this.setMaximumSize( new Dimension(190,190) );
        this.setMinimumSize( new Dimension(190,190) );

       // this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
       this.setBorder( BorderFactory.createLineBorder(Color.GRAY,2) );

        this.setCursor(new Cursor(12)); // hand cursor

  	  }



  	 public void mouseClicked(MouseEvent e) {

         //imageUI = new ServerGUI(" Client No "+ clientNo);
        // NWMServer.task = NWMServer.SEND_IMAGE_FULL;
        NWMServer.task = NWMServer.SEND_IMAGE_FULL;

        NWMServer.firstTimeMaxi = true;
       // cid = new ClientImageDisplay("Client No "+ clientNo);

      }

    public void mouseEntered(MouseEvent e) {
      //Since the user clicked on us, let's get focus!
       this.setBorder(BorderFactory.createLineBorder(Color.GREEN,2) );
       requestFocusInWindow();
      }
    public void mouseExited(MouseEvent e) { this.setBorder(BorderFactory.createLineBorder(Color.GRAY,2) ); }
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }

    public void focusGained(FocusEvent e) {
        //Draw the component with a red border
        //indicating that it has focus.

        this.setBorder(BorderFactory.createLineBorder(Color.GREEN,2) );

        ServerGUI.clnText.setText( ""+clientNo );
        ServerGUI.compNameText.setText( ""+computerName );
        ServerGUI.ipAddText.setText( ""+ipAdd );
        ServerGUI.osNameText.setText( ""+osName );
        ServerGUI.osVersionText.setText( ""+osVersion );
    }

    public void focusLost(FocusEvent e) {
        //Draw the component with a black border
        //indicating that it doesn't have focus.
     //this.setBorder(BorderFactory.createEmptyBorder());
      this.setBorder(BorderFactory.createLineBorder(Color.GRAY,2) );
        ServerGUI.clnText.setText( "" );
        ServerGUI.compNameText.setText( "" );
        ServerGUI.ipAddText.setText( "" );
        ServerGUI.osNameText.setText( "" );
        ServerGUI.osVersionText.setText("");


    }


  	public void paintComponent(Graphics g)
  	   {
         //super.paintComponent(g);
        // g.drawImage(itsImage,3,3,185,185,this);

       }

    public void setImage(BufferedImage theImage)
     {
      // itsImage = theImage;
     }

     public ClientImageDisplay getClientsCID()
      {
          return cid;
       }


       public void refresh(byte[] imgBytes,int imgSize)
        {
         //  image = new ImageIcon( toolkit.createImage(imgBytes) );
           image = new ImageIcon(toolkit.createImage(imgBytes,0,imgSize).getScaledInstance(picture.getWidth(),picture.getHeight(),Image.SCALE_FAST));

           picture.setIcon(image);
              //f.imgAvailable = true;
           picture.revalidate();

        }

       public String getIPAdd()
        { return ipAdd;  }

       public String getOS()
        { return( new String( ""+osName+osVersion ) );  }

       public String getComputerName()
        {return computerName ;}
  }