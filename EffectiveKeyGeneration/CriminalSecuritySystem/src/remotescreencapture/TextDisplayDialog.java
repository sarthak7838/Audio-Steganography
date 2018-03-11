package remotescreencapture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;

public class TextDisplayDialog extends JDialog
 {
    private static TextDisplayDialog dialog ;
    public static JTextArea textArea = new JTextArea();


    // protected String newline = "\n";

    //textArea.setEditable(false);

    public static void initialize(Component comp) {

        Frame frame = JOptionPane.getFrameForComponent(comp);
        dialog = new TextDisplayDialog(frame);
        dialog.setLocationRelativeTo(comp);
        dialog.setVisible(true);

    }

    private TextDisplayDialog(Frame frame) {
        //Do frame stuff.

        super(frame,"LAN COP :: Message for the Client... ", true);

       /* addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //System.exit(0);
            }
        });  */


        //Create the text area used for output.
       // textArea = new JTextArea();

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
             JButton okButton = new JButton("O K ");
             okButton.setToolTipText("Click here to Close Message Dialog");
             okButton.setMnemonic(KeyEvent.VK_O);

             okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                       //  NWMServer.task = NWMServer.SEND_MULTILINE_MESSAGE ;
                         TextDisplayDialog.dialog.setVisible(false);
                         //System.exit(0);

                     }
              });


            //creating button pane
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
            buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            buttonPane.add(Box.createHorizontalGlue());
            buttonPane.add(okButton);
            buttonPane.add(Box.createRigidArea(new Dimension(30, 0)));
            //buttonPane.add(cancelButton);



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