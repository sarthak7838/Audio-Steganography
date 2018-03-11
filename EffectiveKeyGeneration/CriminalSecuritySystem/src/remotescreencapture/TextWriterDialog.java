package remotescreencapture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import java.io.*;

public class TextWriterDialog extends JDialog
 {
    private static TextWriterDialog dialog ;
    public static  JTextArea textArea ;
    public static  boolean messageSend = false;
    protected String newline = "\n";
    //private static PrintWriter pw  = null;

    public static void initialize(Component comp, PrintWriter w) {

        Frame frame = JOptionPane.getFrameForComponent(comp);
        dialog = new TextWriterDialog(frame);
        dialog.setLocationRelativeTo(comp);
        dialog.setVisible(true);

      // pw = new PrintWriter(w);

    }

    private TextWriterDialog(Frame frame) {
        //Do frame stuff.
        super(frame,"LAN COP :: Send Multi Line Message to the Client... ", true);

       /* addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //System.exit(0);
            }
        });  */


        //Create the text area used for output.
        textArea = new JTextArea();

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

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

        // Creating buttons......

            //first button
             JButton sendButton = new JButton("Send Message");
             sendButton.setToolTipText("Click here to Send the Message");
             sendButton.setMnemonic(KeyEvent.VK_S);

             sendButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                         messageSend = true ;
                         TextWriterDialog.dialog.setVisible(false);

                         //System.exit(0);

                     }
              });

            //Second button
              JButton cancelButton = new JButton("Cancel");
             cancelButton.setToolTipText("Click here to if you don't want to Send Message");
             cancelButton.setMnemonic(KeyEvent.VK_C);

             cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                         NWMServer.task = NWMServer.NOTHING ;
                         TextWriterDialog.dialog.setVisible(false);
                        // TextWriterFrame.
                         //System.exit(0);
                     }
              });

            //creating button pane
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
            buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            buttonPane.add(Box.createHorizontalGlue());
            buttonPane.add(sendButton);
            buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
            buttonPane.add(cancelButton);



        //Lay out the content pane.
        //Container contentPane = new getContentPane();
        JPanel contentPane = new JPanel();
        contentPane.setLayout( new BorderLayout() );

        contentPane.setPreferredSize(new Dimension(450, 400));
      //  contentPane.add(toolBar, BorderLayout.NORTH);
        contentPane.add(areaScrollPane, BorderLayout.CENTER);
        contentPane.add(buttonPane , BorderLayout.SOUTH);

        setContentPane(contentPane);

        pack();
        //this.setVisible(true);

    }


  }