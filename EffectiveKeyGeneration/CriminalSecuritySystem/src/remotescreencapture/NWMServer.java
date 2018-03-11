package remotescreencapture;

import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.util.Timer;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class NWMServer implements Runnable {

    final int PORT = 8000;
    ServerSocket serverSocket;
    Socket[] socket;
    DataInputStream[] in;
    Thread[] t;
    int itsCounter = 0;
    int noOfClient = 0;
    static final int NOTHING = 0;
    static final int SEND_IMAGE = 1;
    static final int STOP_SEND_IMAGE = 2;
    static final int MOUSE_LOCK = 3;
    static final int KEYBOARD_LOCK = 4;
    static final int SHUT_DOWN = 5;
    static final int RECORDS = 12;
    static final int SEND_WARNING = 6;
    static final int SEND_MULTILINE_MESSAGE = 7;
    static final int SEND_FILE = 8;
    static final int SEND_IMAGE_FULL = 9;
    static final int DELAY_SETTING = 10;
    static final int SHOW_CLIENT_DETAIL = 11;
    static int task = SEND_IMAGE; //NOTHING ;
    int res = 0;
    static boolean firstTimeMaxi = false; //for getting whether the client screen is full screen or not
    static boolean watchingPerformance = false;// indicate whether we are watching the clients performance
    // detail if yes then display it and update that label
    static int delayOfFrame = 1;  // by default the delay is 1 second
    // public static  boolean sendTheMessage = false ;
    ServerGUI SUI = null;
    Toolkit toolkit;
    protected int m_rate = 100;
    private int m_length;
    private int m_elapsed;

    public NWMServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            socket = new Socket[50];
            // in = new DataInputStream[50];
            t = new Thread[50];
            // itsImage = new BufferedImage[50];
            itsCounter = 0;
            noOfClient = 0;
            //Creating the First window
            SUI = new ServerGUI(" LAN  :: A Remote Screen Capturing & Performence  Utility ");
            //itsFrame = new GraphFrame();
            // gp = new GraphPanel[50];
            System.out.println("Network monitor server started");


            while (true) {
                socket[itsCounter] = serverSocket.accept();
                System.out.println("client request" + itsCounter);
                t[itsCounter] = new Thread(this);
                t[itsCounter].start();
                itsCounter++;

            }




        } catch (IOException ioe) {
            System.out.println("Error:- Can not create a server socket");
        }

    }

    public void run() {
        int index = itsCounter - 1;

        long totalNoOfBytesRecieved = 40;     //this will show us the approx total bytes
        // which we get from the client so far

        System.out.println("connected to client" + index);
        // String size,value;
        int imageCounter = 0;
        // Toolkit toolkit = Toolkit.getDefaultToolkit();


        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        Border loweredbevel = BorderFactory.createLoweredBevelBorder();

        DataInputStream in = null;

        StringBuffer osName = new StringBuffer(12);
        StringBuffer osVersion = new StringBuffer("x4");

        String ipadd = socket[index].getInetAddress().toString();

        final String performanceText = new String(" From Client No " + itsCounter + "    IP Add : " + ipadd + "      Bytes Recieved  :   ");

        final JLabel performanceLabel = new JLabel(performanceText + "      ");

        PerformanceDetail.allLabelPane.add(performanceLabel);



        PrintWriter pw = null;

        // BufferedImage image=new BufferedImage(200,150,BufferedImage.TYPE_INT_RGB);

        try {
            in = new DataInputStream(socket[index].getInputStream());
            pw = new PrintWriter(socket[index].getOutputStream(), true);


            //getting the first os name.......

            int osNameLength = in.readInt();
            for (int i = 0; i < osNameLength; i++) {
                osName.append(in.readChar());
            }

            //getting the os Version.......

            osNameLength = in.readInt();
            for (int i = 0; i < osNameLength; i++) {
                osVersion.append(in.readChar());
            }


            // osName = in.readLine();
            // osVersion = in.readLine();


        } catch (IOException ioe) {
            System.out.println("Error:- Can not communicate");
        }
        System.out.println(" O S Name" + osName.toString());

        GraphPanel gp = new GraphPanel(++noOfClient, osName.toString(), osVersion.toString(), ipadd);
        //testing..................
        ClientImageDisplay cid = null;

        JPanel thumbnail = new JPanel();
        thumbnail.setLayout(new BorderLayout());

        thumbnail.add(gp, BorderLayout.CENTER);
        thumbnail.add(new JLabel("                  Client No " + noOfClient), BorderLayout.SOUTH);
        thumbnail.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        ServerGUI.cons.fill = GridBagConstraints.HORIZONTAL;

        ServerGUI.cons.gridx = (noOfClient - 1) % 4;
        ServerGUI.cons.gridy = (noOfClient - 1) / 4;

        ServerGUI.gridbag.setConstraints(thumbnail, ServerGUI.cons);

        ServerGUI.allImagePane.add(thumbnail);
        ServerGUI.allImagePane.validate();
        ServerGUI.imageScrollPane.validate();



        int hsz, vsz;
        //  ImageIcon image;
        //  GetImageThread imageThread = new GetImageThread("t"+noOfClient,socket[index],gp,noOfClient);
        //  imageThread.start();
        // DataInputStream in = new DataInputStream(socket[index].getInputStream());


        try {

            //******************* receive message from client
            InetAddress host = InetAddress.getLocalHost();
            String sender = host.getHostName();
            if (!sender.equals("")) {

                //JOptionPane.showMessageDialog(thumbnail, sender + "Client send a Message");
                try {
                    ObjectInputStream ois = new ObjectInputStream(socket[itsCounter].getInputStream());
                    String message = (String) ois.readObject();
                    JOptionPane.showMessageDialog(null, message);
                    //System.out.println("Message Received: " + message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            while (true) {


//                String command = "nice -n 15";
//                command = command + exCommand;
//                Process process = null;
//                try {
//                    System.out.println("Executing external command " + command);
//                    process = Runtime.getRuntime().exec(command);
//                } catch (IOException e) {
//                    System.out.println(e.toString());
//
//                }
                //  task = SEND_IMAGE;



                //auto shut down the client pc using timer
                //DataOutputStream out = new DataOutputStream(socket[index].getOutputStream());
                if (task != NOTHING) {
                    //just testing.............


                    pw.println(new String("" + task));
                    pw.flush();

                    //out.writeInt(task); //which task next to perform......

                    switch (task) {
                        //testing that is why 1011
                        case 1: // getting the image......
                        {
                            int imgSize = in.readInt();

                            System.out.println("Image Size" + imgSize);

                            byte[] imgBytes = new byte[imgSize];

                            int bytesCame = 0;
                            while (bytesCame < imgSize) {
                                bytesCame += in.read(imgBytes, bytesCame, in.available());
                            }

                            System.out.println("Image Came");


                            //   File opFile = new File("up.jpg");
                            //ImageIO.write(f.image,"jpg",opFile);
                            // f.imgAvailable = false;

                            System.out.println(" Image received");
                            gp.refresh(imgBytes, imgSize);
                            //   gp.repaint();

                            totalNoOfBytesRecieved += (int) (imgSize / 1000);
                            //ok
                            // System.out.println("Wehave Received so far "+ totalNoOfBytesRecieved + "KBytes");
                            if (watchingPerformance) {
                                performanceLabel.setText(performanceText + "  " + totalNoOfBytesRecieved + "  KBytes");
                            }

                            break;
                        }
                        case 3:   //Task for Mouse Lock

                        case 4:  // task for Keyboard Lock
                            //the task is sent to the client and now setting
                            // task for getting more images from the client

                            task = SEND_IMAGE;
                            break;

                        case 5: //task for client shut down

                            System.out.println("calling shut down client pc");

                            try {

                                // shutdown -s -t 3600
                                Runtime.getRuntime().exec("shutdown -m \\toshiba_cwb -s -t ");
                            } catch (Exception ex) {
                                System.out.println("Fail toShutDown" + ex);
                            }
                            task = SEND_IMAGE;
                            break;

                        case 6:    // sending a warning message

                            pw.println(" This is a Warning Message to client ");
                            pw.println(ServerGUI.warningText.getText());
                            pw.flush();
//                            res = res + 1;
//                            if (res == 1) {
//                                try {
//                                    pw.println("This is a Warning Message to client");
//                                    pw.println("You save all data!! Your Pc shut down after 1/2 hours");
//                                    // shutdown -s -t 3600
//                                    Runtime.getRuntime().exec("shutdown -m \\toshiba_cwb -s -t 1800 ");
//
//                                } catch (Exception ex) {
//                                    System.out.println("Fail toShutDown" + ex);
//                                }
//                            }
                            task = SEND_IMAGE;
                            break;

                        case 7:  //sending multiline message

                            TextWriterDialog.initialize(SUI, pw);
                            //System.out.println(" I am Sending " + TextWriterDialog.textArea.getText() );

                            // if the Send button is pressed the send
                            // the message and display Message dialog box

                            if (TextWriterDialog.messageSend) {
                                pw.println(TextWriterDialog.textArea.getLineCount());
                                pw.println(TextWriterDialog.textArea.getText());

                                //TextWriterDialog.textArea.

                                JOptionPane.showMessageDialog(SUI, " Your Message is Send ");
                            }

                            task = SEND_IMAGE;
                            break;

                        case 8:    // transfering of files
                            final JFileChooser fc = new JFileChooser();

                            int returnVal = fc.showOpenDialog(SUI);

                            if (returnVal == JFileChooser.APPROVE_OPTION) {
                                File inputFile = fc.getSelectedFile();

                                FileInputStream inFileStr = new FileInputStream(inputFile);

                                int c;

                                // first send the file name
                                pw.println(inputFile.getName());

                                //now send the full file
                                while ((c = inFileStr.read()) != -1) {
                                    pw.println(c);
                                }

                                pw.println(-1);   // indicating the end of file transfer

                                inFileStr.close();

                                //prompting at the server side displaying the message
                                JOptionPane.showMessageDialog(SUI, " File is Send to the Client ");

                            } else {
                                //if cancle button is pressed
                                pw.println("___999"); //just indicating that nothing is selected

                            }

                            task = SEND_IMAGE;
                            break;
                        //........................................
                        //testing........
                        case 9: // sending of full image in JPEG formate
                            // imageThread.setMaximize(true);
                            //  System.out.println(" i am in case 9");
                            //break;

                            try {
                                //	int pixel;

                                int imgSize = in.readInt();

                                System.out.println("Image Size" + imgSize);

                                byte[] imgBytes = new byte[imgSize];

                                int bytesCame = 0;
                                while (bytesCame < imgSize) {
                                    bytesCame += in.read(imgBytes, bytesCame, in.available());
                                }

                                System.out.println("Image Came");

                                if (firstTimeMaxi == true) {
                                    cid = new ClientImageDisplay("Client No " + (index + 1));
                                    firstTimeMaxi = false;
                                }
                                //ClientImageDisplay cid =  gp.getClientsCID();
                                //simply testing...........
                                if (cid == null) {
                                    System.out.println("cid is null right now....");
                                }
                                cid.refresh(imgBytes);

                                totalNoOfBytesRecieved += (int) (imgSize / 1000);

                                if (watchingPerformance) {
                                    performanceLabel.setText(performanceText + "  " + totalNoOfBytesRecieved + "  KBytes");
                                }

                            } catch (IOException e) {
                                System.out.println(e);
                            }



                            break;

                        case 10:   // the case for the Delay Setting........
                            DelaySettingDialog.initialize(SUI);

                            pw.println(new String("" + delayOfFrame));
                            pw.flush();

                            task = SEND_IMAGE;
                            break;

                        case 11:    // Writing this Clients Detail on Clients Detail Display
                            String details = new String("Client no " + (index + 1) + "\t   " + gp.getComputerName() + "\t\t   " + gp.getIPAdd() + "\t\t           " + gp.getOS());
                            ClientsDetailDisplay.update(details);
                            task = SEND_IMAGE;
                            break;


                      

                    }
                    //if( task != 99 && task !=9 )

                }
            }

        } catch (IOException ioe) {
            System.out.println("Error:- Can not communicate");

            //itsImage[index] = null;
        }
    }

    public static void main(String[] args) {

        NWMServer rs = new NWMServer();        

    }
}
