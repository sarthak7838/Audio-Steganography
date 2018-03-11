package remotescreencapture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.event.*;
import java.io.*;

public class DelaySettingDialog extends JDialog
 {
    private static DelaySettingDialog dsdialog ;
    //public static  JTextArea textArea ;
    //public static  boolean messageSend = false;
    //protected String newline = "\n";
    //private static PrintWriter pw  = null;

    static int delay = NWMServer.delayOfFrame ; // initialize with the current delay parameter value

    public static void initialize(Component comp) {

        Frame frame = JOptionPane.getFrameForComponent(comp);
        dsdialog = new DelaySettingDialog(frame);
        dsdialog.setLocationRelativeTo(comp);
        dsdialog.setVisible(true);

      // pw = new PrintWriter(w);

    }

    private DelaySettingDialog(Frame frame) {
        //Do frame stuff.
        super(frame," LAN COP :: Setting the Delay for frames ", true);

       /* addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //System.exit(0);
            }
        });  */

        //Create the slider and its label
       // JLabel sliderLabel = new JLabel("Delay in Second", JLabel.CENTER);
       // sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSlider framesDelay = new JSlider(JSlider.HORIZONTAL,
                                              1, 60, delay);
        framesDelay.addChangeListener(new SliderListener());

        //Turn on labels at major tick marks.
        framesDelay.setMajorTickSpacing(5);
        framesDelay.setMinorTickSpacing(1);
        framesDelay.setPaintTicks(true);
        framesDelay.setPaintLabels(true);
       // framesDelay.setBorder(
       //         BorderFactory.createEmptyBorder(0,0,10,0));


        framesDelay.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder(" Frame Delay in Seconds "),
                                BorderFactory.createEmptyBorder(10,5,10,5)),
                framesDelay.getBorder() ) );

        // Creating buttons......

            //first button
             JButton setButton = new JButton("Set Delay");
             setButton.setToolTipText("Click here to Set the Delay");
             setButton.setMnemonic(KeyEvent.VK_S);

             setButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                       System.out.println("The Delay is going to set " + delay);
                        // messageSend = true ;
                        NWMServer.delayOfFrame = delay ;

                      DelaySettingDialog.dsdialog.setVisible(false);
                      //DelaySettingDialog.DISPOSE_ON_CLOSE ;
                      DelaySettingDialog.dsdialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                         //System.exit(0);

                     }
              });

            //Second button
              JButton cancelButton = new JButton("Cancel");
             cancelButton.setToolTipText("Click here to if you don't want to Set Delay");
             cancelButton.setMnemonic(KeyEvent.VK_C);

             cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        // NWMServer.task = NWMServer.NOTHING ;
                         DelaySettingDialog.dsdialog.setVisible(false);
                       //  DelaySettingDialog.DISPOSE_ON_CLOSE ;
                        // TextWriterFrame.
                         //System.exit(0);
                     }
              });

            //creating button pane
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
            buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            buttonPane.add(Box.createHorizontalGlue());
            buttonPane.add(setButton);
            buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
            buttonPane.add(cancelButton);



        //Lay out the content pane.
        //Container contentPane = new getContentPane();
        JPanel contentPane = new JPanel();
        contentPane.setLayout( new BorderLayout() );

       contentPane.setPreferredSize(new Dimension(550, 200));
      //  contentPane.add(toolBar, BorderLayout.NORTH);

       // contentPane.add(sliderLabel , BorderLayout.NORTH);
        contentPane.add(framesDelay, BorderLayout.CENTER);
        contentPane.add(buttonPane , BorderLayout.SOUTH);

        setContentPane(contentPane);

        pack();
        //this.setVisible(true);

    }


  }

   /** Listens to the slider. */
    class SliderListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider)e.getSource();
            if (!source.getValueIsAdjusting()) {
                DelaySettingDialog.delay = (int)source.getValue();


            }
        }
    }
