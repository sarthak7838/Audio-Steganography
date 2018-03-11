package remotescreencapture;

import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.JOptionPane;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import javax.swing.JFileChooser;

public class NWMClient {

    final int PORT = 8000;
    Socket itsSocket;
    String itsAddr;
    Robot itsRobot;
    BufferedImage itsImage;
    DataOutputStream out;
    DataInputStream in;
    BufferedReader breader = null;
    private Component Owner;

    public NWMClient(String theAddr) {

        try {

            itsSocket = new Socket(theAddr, PORT);
            breader = new BufferedReader(new InputStreamReader(itsSocket.getInputStream()));
            out = new DataOutputStream(itsSocket.getOutputStream());
            System.out.println("Network Monitor client started");
            itsRobot = new Robot();

        } catch (IOException ioe) {
            System.out.println("Unknown host");
        } catch (AWTException ioe) {
            System.out.println("Robot not Create");
        }

    }

    public void communicate() throws IOException {
        String str, str1;
        int task = 0;

        String osName = null;
        String osVersion = null;

        int delay = 1;

        try {
            osName = System.getProperty("os.name"); // Operating system name
            osVersion = System.getProperty("os.version");

            //sending the os name
            out.writeInt(osName.length());
            for (int i = 0; i < osName.length(); i++) {
                out.writeChar(osName.charAt(i));
            }

            //sending the os name

            out.writeInt(osVersion.length());

            for (int i = 0; i < osVersion.length(); i++) {
                out.writeChar(osVersion.charAt(i));
            }

        } catch (SecurityException e) {
            System.out.println("Could not read: "
                    + "SECURITY EXCEPTION!");
        }

        System.out.println(" O S Name" + osName + "version" + osVersion);

        //Creating the thread of screen Capture........
        // ScreenCaptureThread sct = new ScreenCaptureThread("sct",itsSocket);
        //...................................just testing...........
        //*****************OK***********
        // sct.start();
        //***************** OK*************
        //out.writeChars(osName);
        //out.writeChars(osVersion);

        while (true) {
            // task  = in.readInt();
            String s = breader.readLine();

            task = Integer.parseInt(s);

            switch (task) {
                //just testing that is why 101
                case 101:

                    itsImage = (itsRobot.createScreenCapture(new Rectangle(0, 0, 800, 600)));
                    out.writeInt(itsImage.getWidth());
                    out.writeInt(itsImage.getHeight());

                    for (int i = 0; i < itsImage.getWidth(); i++) {
                        for (int j = 0; j < itsImage.getHeight(); j++) {
                            if ((i % 4 == 0) && (j % 4 == 0)) {
                                out.writeInt(itsImage.getRGB(i, j));
                            }
                        }
                    }

                    System.out.println("Image send");

                    break;

                case 3:   // Mouse Lock
                    MouseLockThread mlt = new MouseLockThread("mlt");
                    mlt.start();
                    break;
                /*
                while(true)
                itsRobot.mouseMove(300,300);
                 */

                case 4:  // Keyboard lock
                    try {
                        Runtime r = Runtime.getRuntime();
                        r.exec("KeyBoardLock.exe");
                        System.out.println("Key Board is Locked");

                    } catch (IOException e) {
                        System.out.println("Unable to exec");
                    }

                    break;
                case 5: // shut down the client m/c

                    try {
                        Robot r = new Robot();
                        r.keyPress(KeyEvent.VK_CONTROL);
                        r.keyPress(KeyEvent.VK_ESCAPE);
                        r.keyRelease(KeyEvent.VK_CONTROL);
                        r.keyRelease(KeyEvent.VK_ESCAPE);
                        /* control + escape is short cut to start menu */

                        r.keyPress(KeyEvent.VK_U);
                        r.keyRelease(KeyEvent.VK_U);
                        /* u recognizes shut down option */

                        r.keyPress(KeyEvent.VK_S);
                        r.keyRelease(KeyEvent.VK_S);

                        r.keyPress(KeyEvent.VK_ENTER);
                        /* Eneter confirms shutdown */

                    } catch (Exception e) {
                        System.out.println("Unable to shutdown");
                    }

                    break;

                //displaying the warning message
                case 6:
                    JOptionPane.showMessageDialog(null,
                            breader.readLine(),
                            "Warning Message",
                            JOptionPane.WARNING_MESSAGE);
                    //System.out.println(breader.readLine());
                    break;

                case 7:
                    TextDisplayDialog.textArea.setEditable(false);
                    TextDisplayDialog.textArea.setLineWrap(true);
                    TextDisplayDialog.textArea.setWrapStyleWord(true);

                    int lineCount = Integer.parseInt(breader.readLine());

                    for (int i = 0; i < lineCount; i++) {
                        TextDisplayDialog.textArea.append(breader.readLine() + "\n");
                    }

                    TextDisplayDialog.initialize(null);
                    break;

                case 8:  //  getting the files from the server

                    String fileNameString = breader.readLine();

                    if (fileNameString.equals("___999")) {
                        //do nothing
                    } else {

                        JOptionPane.showMessageDialog(Owner, "Server Send a file");
                        String outputfilepath = fileChooser();//calling file chooser for  saving file
                        FileOutputStream outs = new FileOutputStream(outputfilepath);
                        int c;
                        while ((c = Integer.parseInt(breader.readLine())) != -1) {
                            outs.write(c);
                        }
                        System.out.println("The new file received");

                        outs.close();
                    }

                    break;
                case 1:
                case 9: // just testing.............
                // this will be for sending the full JPEG file
                // sct.setSendJPEG( true );

                // break;


                case 99:  // just testing.........
                    //  while(sct.isAlive()) { System.out.println("Waiting for sct to dead."); } // wait until sct is sending images
                    try {
                        System.out.println("Creating JPEG.");
                        Robot robot = new Robot();
                        BufferedImage bimg = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

                        ByteArrayOutputStream imgStream = new ByteArrayOutputStream();

                        imgStream.flush();
                        ImageIO.write(bimg, "jpeg", imgStream);
                        System.out.println("Image Size jpeg: " + imgStream.size());
                        out.writeInt(imgStream.size());
                        imgStream.writeTo(out);


                        /*  // this one is my old code....slower one....

                        Robot robot =new Robot();
                        // ObjectOutputStream f = new ObjectOutputStream( new FileOutputStream("i1.jpeg") );

                        OutputStream f = new FileOutputStream("filename1111");

                        //BufferedOutputStream f = new BufferedOutputStream( out );

                        JPEGImageEncoder encoder =JPEGCodec.createJPEGEncoder( f );
                        encoder.encode(robot.createScreenCapture(new
                        Rectangle(Toolkit.getDefaultToolkit().getScreenSize())));

                        f.flush();
                        f.close();

                        //sending the stored file...............
                        System.out.println("Sending JPEG...");
                        FileInputStream insf = new FileInputStream("filename1111");
                        int c ;

                        while( ( c = insf.read() ) != -1 )
                        {
                        out.writeInt( c );

                        }
                        out.writeInt(-1);   //indicating the end  of file
                        out.flush();
                        insf.close();
                         */


                    } catch (AWTException e1) {
                    } catch (IOException e2) {
                    } catch (Exception ee) {
                    }
                    System.out.println("JPEG Image send ");
                    break;

                case 10:  // case for the setting of the delay........
                    String delays = breader.readLine();

                    delay = Integer.parseInt(s);

                    System.out.println("i am going to set the delay of " + delay);

                    break;
                default:
                    System.out.println("Nothing...... ");

            }

            //here i have to put the delay which u want.......

            // delay( ( delay-1)*1000 );


        }
    }

    public static void main(String[] args) {
        String addr;
        if (args.length == 0) {
            addr = "127.0.0.1"
                    + "";
        } else {
            addr = args[0];
        }

       
        NWMClient nwmc = new NWMClient(addr);

        try {
            nwmc.communicate();
        } catch (IOException e) {
            System.out.println("can not communicate" + e);
            System.exit(0);
        }
    }

    //output file path
    public String fileChooser() {

        String path = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(Owner)) {
            path = chooser.getSelectedFile().getPath();

        }
        return path;

    }
}
