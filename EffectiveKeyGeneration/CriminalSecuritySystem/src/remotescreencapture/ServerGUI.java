package remotescreencapture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ServerGUI extends JFrame {

    protected Action startSendImageAction;
    protected Action stopSendImageAction;
    protected Action sendWarningAction;
    protected Action sendMessageAction;
    protected Action sendFileAction;
    //protected Action keyBoardLockAction;
   // protected Action mouseLockAction;
    protected Action shutDownAction;
    //protected Action clientActivityRecord;
    protected Action showClientDetailsAction;
    protected Action showClientPerformanceAction;
    //protected Action showTrafficAnalyzerAction;
    protected Action showDelayDialogAction;
    public static JTextField warningText;
    public static JTextField clnText;
    public static JTextField osNameText;
    public static JTextField osVersionText;
    public static JTextField osArchiText;
    public static JTextField ipAddText;
    public static JTextField compNameText;
    static protected JPanel allImagePane = null;
    protected JPanel contentPane = null;
    static protected JScrollPane imageScrollPane = null;
    static protected GridBagLayout gridbag = new GridBagLayout();
    static protected GridBagConstraints cons = new GridBagConstraints();
    // static protected ServerGUICount = 0; // this will count
    GraphPanel gp = null;
    private static int noOfGraphPanel = 0;
    //private Toolkit itsToolkit;

    ServerGUI(String tital) {

        super(tital);
        /* To make the Network monitor window fit
         * according to current screen resolution
         */
        Dimension aDimension = Toolkit.getDefaultToolkit().getScreenSize();
        // itsToolkit=Toolkit.getDefaultToolkit();


        //Container contentPane = getContentPane();
        //if this iis the first frame then


        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);

            }
        });


        setSize((int) aDimension.getWidth(), (int) aDimension.getHeight());




        //testing.........
        cons.anchor = GridBagConstraints.NORTHEAST;

        // Creating Menu Bar and setting it for this frame
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);


        //Build the first menu.

        JMenu menu = new JMenu(" Monitor ");
        menu.setMnemonic(KeyEvent.VK_M);
        menu.getAccessibleContext().setAccessibleDescription(
                "Menu Which has Option Related to the Monitoring the Clients ");
        menu.setToolTipText(
                "Menu Which has Option Related to the Monitoring the Clients ");
        menuBar.add(menu);

        //  Build the first & second tool Bar
        JToolBar toolBar1 = new JToolBar();
        JToolBar toolBar2 = new JToolBar();
        toolBar1.setFloatable(false);
        toolBar2.setFloatable(false);

        createActionsForFirst(menu, toolBar1, toolBar2); // function to create the same actions for menu and toolbar button

        //Building Second Menu
        menu = new JMenu(" Performance ");
        menu.setMnemonic(KeyEvent.VK_P);
        menu.getAccessibleContext().setAccessibleDescription(
                "Menu Which has Option Related to the Performance of the Clients ");
        menu.setToolTipText(
                "Menu Which has Option Related to the Performance of the Clients ");
        menuBar.add(menu);

        createActionsForSecond(menu, toolBar1);

        // too bar Pane
        JPanel toolBarPane = new JPanel();
        toolBarPane.setLayout(new GridLayout(0, 1));
        toolBarPane.add(toolBar1);
        toolBarPane.add(toolBar2);


        allImagePane = new JPanel();
        allImagePane.setBorder(BorderFactory.createLoweredBevelBorder());
        //allImagePane.setLayout( new GridLayout(0,5,2,2) );
        allImagePane.setLayout(gridbag);
        //allImagePane.setPreferredSize(new Dimension(200,100));
        // allImagePane.add(new JButton("testing!"));
        // for(int i=0;i<19;i++)
        //  allImagePane.add(new GraphPanel(i));

        // the Scroll Pane Related..........
        imageScrollPane = new JScrollPane(allImagePane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        imageScrollPane.setPreferredSize(new Dimension(200, 100));
        imageScrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // the lowermost Information Pane.............
        JPanel infoPane = new JPanel();
        infoPane.add(new JLabel("Client No"));
        clnText = new JTextField(3);
        clnText.setEditable(false);
        infoPane.add(clnText);

        infoPane.add(new JLabel(" Computer Name"));
        compNameText = new JTextField(6);
        compNameText.setEditable(false);
        infoPane.add(compNameText);

        infoPane.add(new JLabel(" IP Address"));
        ipAddText = new JTextField(11);
        ipAddText.setEditable(false);
        infoPane.add(ipAddText);

        infoPane.add(new JLabel(" Operating System"));
        osNameText = new JTextField(10);
        osNameText.setEditable(false);
        infoPane.add(osNameText);

        infoPane.add(new JLabel(" Version"));
        osVersionText = new JTextField(3);
        osVersionText.setEditable(false);
        infoPane.add(osVersionText);

        infoPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(3, 3, 3, 3), BorderFactory.createRaisedBevelBorder()));

        //Lay out the content pane.
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        contentPane.add(toolBarPane, BorderLayout.NORTH);
        contentPane.add(imageScrollPane, BorderLayout.CENTER);
        contentPane.add(infoPane, BorderLayout.SOUTH);
        //contentPane.add(toolBar2, BorderLayout.NORTH);

        //Build the third menu.
        menu = new JMenu(" Settings ");
        menu.setMnemonic(KeyEvent.VK_S);
        menu.getAccessibleContext().setAccessibleDescription(
                "To Set the  Server GUI ");
        menu.setToolTipText("Menu to Set the  Server GUI ");
        createActionsForThird(menu, toolBar1);

        menuBar.add(menu);


        //Build the forth menu.
        menu = new JMenu(" Help ");
        menu.setMnemonic(KeyEvent.VK_H);
        menu.getAccessibleContext().setAccessibleDescription(" HELP  ");
        menuBar.add(menu);

        // Forth Menu first Item

        JMenuItem menuItem = new JMenuItem(" About ",
                KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                " Help About the Product ");
        menuItem.setToolTipText(" Help About the Product ");
        menuItem.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {


                        JOptionPane.showMessageDialog(null,
                                "Guided By :                                 Submitted By :  \n\n"
                                + "Ms. Shipra Gupta                       Anshuman Singh Tomar(0406410013)\n"
                                + "                                                     Amit Sagar(0406440013)\n"
                                + "                                                     Kumar Gaurav(2506410120)\n\n\n",
                                "About Us ",
                                JOptionPane.PLAIN_MESSAGE);

                    }
                });
        menu.add(menuItem);


        /*
        try
        {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        SwingUtilities.updateComponentTreeUI(this);


        }
        catch (UnsupportedLookAndFeelException exc)
        {
        System.out.println("UnsupportedLookAndFeelException Error:" + exc);
        }
        catch (IllegalAccessException exc)
        {
        System.out.println("IllegalAccessException Error:" + exc);
        }
        catch (ClassNotFoundException exc)
        {
        System.out.println("ClassNotFoundException Error:" + exc);
        }
        catch (InstantiationException exc)
        {
        System.out.println("InstantiateException Error:" + exc);
        }

         */



        setVisible(true);

    }        //Still to be defined ..............................

    public void createActionsForFirst(JMenu mainMenu, JToolBar toolBar1, JToolBar toolBar2) {
        JButton button = null;
        JMenuItem menuItem = null;

        //*******
        //first button and menu item
        startSendImageAction = new AbstractAction("Start Sending Images",
                new ImageIcon("images/start_send_image.gif")) {

            public void actionPerformed(ActionEvent e) {

                //Still to write actions.......
                NWMServer.task = NWMServer.SEND_IMAGE; // 1 indicate SEND IMAGE

            }
        };
        button = toolBar1.add(startSendImageAction);
        button.setText(""); //an icon-only button

        // button.setIcon(null);   //temporary

        button.setToolTipText("Start Sending the Images ");
        menuItem = mainMenu.add(startSendImageAction);
        menuItem.setIcon(null); //arbitrarily chose not to use icon in menu
        menuItem.setMnemonic(KeyEvent.VK_I);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.ALT_MASK));
        menuItem.setToolTipText("Start Sending the Images ");

        startSendImageAction.setEnabled(false);  // at start the iages r already start

        //*********
        //second button and menu item
        stopSendImageAction = new AbstractAction("Stop Sending Images",
                new ImageIcon("images/stop_send_image.gif")) {

            public void actionPerformed(ActionEvent e) {

                //Still to write actions.......

                NWMServer.task = NWMServer.NOTHING;
                startSendImageAction.setEnabled(true);
            }
        };
        button = toolBar1.add(stopSendImageAction);
        button.setText(""); //an icon-only button
        button.setToolTipText("Stop Sending the Images ");
        menuItem = mainMenu.add(stopSendImageAction);
        menuItem.setIcon(null); //arbitrarily chose not to use icon in menu
        menuItem.setMnemonic(KeyEvent.VK_S);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_X, ActionEvent.ALT_MASK));
        menuItem.setToolTipText("Stop Sending the Images ");

        mainMenu.addSeparator();
        toolBar1.addSeparator();

        //*********
        //third Menu Item and 3 buttons in toolbar

        JMenu subMenu = new JMenu("Send Messages ");
        subMenu.setMnemonic(KeyEvent.VK_M);
        //****
        // new item in submenu and buttons in toolBar2
        sendWarningAction = new AbstractAction("Send Warning") {

            public void actionPerformed(ActionEvent e) {

                //Still to write actions.......

                NWMServer.task = NWMServer.SEND_WARNING;
            }
        };
        button = toolBar2.add(sendWarningAction);
        button.setText("Send Warning"); //an icon-only button
        button.setToolTipText("Click here to Send the Warning message ");
        button.setMnemonic(KeyEvent.VK_W);
        menuItem = subMenu.add(sendWarningAction);
        menuItem.setIcon(null); //arbitrarily chose not to use icon in menu
        menuItem.setMnemonic(KeyEvent.VK_W);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        menuItem.setToolTipText("Click here to Send the Warning message");

        warningText = new JTextField("Warning Message", 50);
        toolBar2.add(warningText);
        //****
        // second item in submenu and buttons in toolBar1
        sendMessageAction = new AbstractAction("Send MultiLine Message",
                new ImageIcon("images/multiline_image.gif")) {

            public void actionPerformed(ActionEvent e) {

                //Still to write actions.......
                // TextWriterFrame twf = new TextWriterFrame();
                NWMServer.task = NWMServer.SEND_MULTILINE_MESSAGE;
            }
        };
        button = toolBar1.add(sendMessageAction);
        button.setText(""); //an icon-only button
        button.setToolTipText("Click here to Send the MultiLine message to the Client");
        menuItem = subMenu.add(sendMessageAction);
        menuItem.setIcon(null); //arbitrarily chose not to use icon in menu
        menuItem.setMnemonic(KeyEvent.VK_L);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_L, ActionEvent.ALT_MASK));
        menuItem.setToolTipText("Click here to Send the MultiLine message to the Client");

        subMenu.addSeparator();
        //*****
        //third item in submenu and buttons in toolBar1
        sendFileAction = new AbstractAction("Send File",
                new ImageIcon("images/file_image.gif")) {

            public void actionPerformed(ActionEvent e) {

                //Still to write actions.......

                NWMServer.task = NWMServer.SEND_FILE;
            }
        };
        button = toolBar1.add(sendFileAction);
        button.setText(""); //an icon-only button
        button.setToolTipText("Click here to Send a File to the Client");
        menuItem = subMenu.add(sendFileAction);
        menuItem.setIcon(null); //arbitrarily chose not to use icon in menu
        menuItem.setMnemonic(KeyEvent.VK_F);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F, ActionEvent.ALT_MASK));
        menuItem.setToolTipText("Click here to Send a File to the Client");

        mainMenu.add(subMenu);
        mainMenu.addSeparator();
        toolBar1.addSeparator();
        //*******
        //forth Menu Item and 3 buttons in toolbar

        subMenu = new JMenu(" Actions ");
        subMenu.setMnemonic(KeyEvent.VK_A);

        // new item in submenu and buttons in toolBar2
       // keyBoardLockAction = new AbstractAction("Lock The Key Board",
              //  new ImageIcon("images/keyboard_lock.gif")) {

           // public void actionPerformed(ActionEvent e) {

                //Still to write actions.......
               // NWMServer.task = NWMServer.KEYBOARD_LOCK; // 3 indicate KEY BOARD LOCK

           // }
       // };
       // button = toolBar1.add(keyBoardLockAction);
        //button.setText(""); //an icon-only button
        //button.setToolTipText(" Lock the Key Board of the Client ");
        //menuItem = subMenu.add(keyBoardLockAction);
        //menuItem.setIcon(null); //arbitrarily chose not to use icon in menu
        menuItem.setMnemonic(KeyEvent.VK_K);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_K, ActionEvent.CTRL_MASK));
        menuItem.setToolTipText("Click here to Lock the key Board of the Client ");

        // second item in submenu and buttons in toolBar1
       // mouseLockAction = new AbstractAction("Lock The Mouse",
                //new ImageIcon("images/mouse_lock.gif")) {

           // public void actionPerformed(ActionEvent e) {

                //Still to write actions.......
                //NWMServer.task = NWMServer.MOUSE_LOCK; // 2 indicate Locking of the Mouse
           // }
       // };
        //button = toolBar1.add(mouseLockAction);
        //button.setText(""); //an icon-only button
       // button.setToolTipText("Lock the Mouse of the Client ");
       // menuItem = subMenu.add(mouseLockAction);
        //menuItem.setIcon(null); //arbitrarily chose not to use icon in menu
        menuItem.setMnemonic(KeyEvent.VK_M);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        menuItem.setToolTipText("Lock the Mouse of the Client ");



        // Thired item in submenu action but nothing for toolbar

       // menuItem = new JMenuItem("Both Mouse & KeyBoard Lock",
             //   KeyEvent.VK_B);
        //menuItem.setAccelerator(KeyStroke.getKeyStroke(
          //      KeyEvent.VK_B, ActionEvent.CTRL_MASK));
       // menuItem.getAccessibleContext().setAccessibleDescription(
         //       " Lock Both Key Board and the Mouse of the Client ");
        //menuItem.setToolTipText(" Lock Both Key Board and the Mouse of the Client ");
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // Still to set the Action......................
            }
        });






        subMenu.add(menuItem);
        subMenu.addSeparator();
        // toolBar1.addSeparator();

        //forth item in submenu and buttons in toolBar1
        shutDownAction = new AbstractAction(" Shut Down",
                new ImageIcon("images/shut_down.gif")) {

            public void actionPerformed(ActionEvent e) {

                //Still to write actions.......
                NWMServer.task = NWMServer.SHUT_DOWN;

            }
        };

        button = toolBar1.add(shutDownAction);
        button.setText(""); //an icon-only button
        button.setToolTipText(" Shut Down the  Clients machine ");
        menuItem = subMenu.add(shutDownAction);
        menuItem.setIcon(null); //arbitrarily chose not to use icon in menu
        menuItem.setMnemonic(KeyEvent.VK_D);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        menuItem.setToolTipText(" Shut Down the  Clients machine");

        mainMenu.add(subMenu);
        mainMenu.addSeparator();
        toolBar1.addSeparator();



        //fifth item in submenu client activity record

//
//        clientActivityRecord = new AbstractAction(" Records",
//                new ImageIcon("images/shut_down.gif")) {
//
//            public void actionPerformed(ActionEvent e) {
//
//                //Still to write actions.......
//                NWMServer.task = NWMServer.RECORDS;
//
//            }
//        };


       // button = toolBar1.add(clientActivityRecord);
       // button.setText(""); //an icon-only button
        //button.setToolTipText(" Client Activity Record ");
        //menuItem = subMenu.add(clientActivityRecord);
       // menuItem.setIcon(null); //arbitrarily chose not to use icon in menu
        menuItem.setMnemonic(KeyEvent.VK_E);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        menuItem.setToolTipText("Client Activity Record");

        mainMenu.add(subMenu);
        mainMenu.addSeparator();
        toolBar1.addSeparator();





//        menuItem = new JMenuItem("Client Activity Record",
//                KeyEvent.VK_A);
//        menuItem.setAccelerator(KeyStroke.getKeyStroke(
//                KeyEvent.VK_A, ActionEvent.CTRL_MASK));
//        menuItem.getAccessibleContext().setAccessibleDescription(
//                " Records the Client Activity ");
//        menuItem.setToolTipText(" Records the Client Activity ");
//        menuItem.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//                // Still to set the Action......................
//            }
//        });





        //************
        //fifth item in menu and buttons in toolBar1
        showClientDetailsAction = new AbstractAction(" Client Details...",
                new ImageIcon("images/client_detail_image.gif")) {

            public void actionPerformed(ActionEvent e) {

                //Still to write actions.......
                  /*
                ClientsDetailDisplay.textArea.setEditable(false);


                ClientsDetailDisplay.textArea.append("Client no");// \tComputer Name \tIP Address \tOperating System"+"\n\n");
                ClientsDetailDisplay.initialize(null);
                ClientsDetailDisplay.textArea.append("    Client no2");

                ClientsDetailDisplay.areaScrollPane.validate();
                 */
                ClientsDetailDisplay cdd = new ClientsDetailDisplay("Detail Information About All Client");
                NWMServer.task = NWMServer.SHOW_CLIENT_DETAIL;
            }
        };
        button = toolBar1.add(showClientDetailsAction);
        button.setText(""); //an icon-only button
        button.setToolTipText(" Show the Details about Client machine ");
        menuItem = mainMenu.add(showClientDetailsAction);
        menuItem.setIcon(null); //arbitrarily chose not to use icon in menu
        menuItem.setMnemonic(KeyEvent.VK_D);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_D, ActionEvent.ALT_MASK));
        menuItem.setToolTipText(" Show the Details about Client machine");

    }

    public void createActionsForSecond(JMenu mainMenu, JToolBar toolBar1) {
        JButton button = null;
        JMenuItem menuItem = null;
        //toolBar1.addSeparator();
        //************
        //first item in menu and buttons in toolBar1
        showClientPerformanceAction = new AbstractAction(" Performance Details of Clients",
                new ImageIcon("images/performance_client.gif")) {

            public void actionPerformed(ActionEvent e) {

                //Still to write actions.......

                PerformanceDetail pdl = new PerformanceDetail();
                NWMServer.watchingPerformance = true;

            }
        };
        button = toolBar1.add(showClientPerformanceAction);
        button.setText(""); //an icon-only button
        button.setToolTipText(" Show the Details about Clients performance ");
        menuItem = mainMenu.add(showClientPerformanceAction);
        menuItem.setIcon(null); //arbitrarily chose not to use icon in menu
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        menuItem.setToolTipText(" Show the Details about Clients Performance");

        mainMenu.addSeparator();
        //toolBar1.addSeparator();
        //************
        //Secnd item in menu and buttons in toolBar1
       // showTrafficAnalyzerAction = new AbstractAction(" Traffic Analyzer",
               // new ImageIcon("images/Performance_analyzer.gif")) {

           // public void actionPerformed(ActionEvent e) {
                //Still to write actions.......
                //   NWMServer.task = 99; // just testing
                //  NWMServer.task = NWMServer.DELAY_SETTING ;
          //  }
        //};
        //button = toolBar1.add(showTrafficAnalyzerAction);
        //button.setText(""); //an icon-only button
       // button.setToolTipText(" Show  the Traffic Analyzer ");
       // menuItem = mainMenu.add(showTrafficAnalyzerAction);
        //menuItem.setIcon(null); //arbitrarily chose not to use icon in menu
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        menuItem.setToolTipText(" Show you the Traffic Analyzer");

    }

    public void createActionsForThird(JMenu mainMenu, JToolBar toolBar1) {
        JButton button = null;
        JMenuItem menuItem = null;
        final JFrame sg = this;
        //*********
        //third Menu Item and 3 buttons in toolbar

        JMenu subMenu = new JMenu("Look And Feel  ");
        subMenu.setMnemonic(KeyEvent.VK_L);
        //****

        menuItem = new JMenuItem("Windows");
        menuItem.setMnemonic(KeyEvent.VK_W);
        menuItem.setToolTipText("Set Windows Look And Feel ");
        menuItem.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        // Still to set the Action......................
                        try {
                            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                            SwingUtilities.updateComponentTreeUI(sg);
                            /* For Windows Look And Feel */
                        } catch (UnsupportedLookAndFeelException exc) {
                            System.out.println("UnsupportedLookAndFeelException Error:" + exc);
                        } catch (IllegalAccessException exc) {
                            System.out.println("IllegalAccessException Error:" + exc);
                        } catch (ClassNotFoundException exc) {
                            System.out.println("ClassNotFoundException Error:" + exc);
                        } catch (InstantiationException exc) {
                            System.out.println("InstantiateException Error:" + exc);
                        }

                    }
                });
        subMenu.add(menuItem);
        subMenu.addSeparator();

        menuItem = new JMenuItem("Java");
        menuItem.setMnemonic(KeyEvent.VK_M);
        menuItem.setToolTipText("Set Java Look And Feel ");
        menuItem.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        // Still to set the Action......................
                        try {
                            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                            SwingUtilities.updateComponentTreeUI(sg);

                            /* For Windows Look And Feel */
                        } catch (UnsupportedLookAndFeelException exc) {
                            System.out.println("UnsupportedLookAndFeelException Error:" + exc);
                        } catch (IllegalAccessException exc) {
                            System.out.println("IllegalAccessException Error:" + exc);
                        } catch (ClassNotFoundException exc) {
                            System.out.println("ClassNotFoundException Error:" + exc);
                        } catch (InstantiationException exc) {
                            System.out.println("InstantiateException Error:" + exc);
                        }

                    }
                });
        subMenu.add(menuItem);
        subMenu.addSeparator();

        menuItem = new JMenuItem("Linux");
        menuItem.setMnemonic(KeyEvent.VK_F);
        menuItem.setToolTipText("Set Linux Look And Feel ");
        menuItem.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        // Still to set the Action......................
                        try {
                            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                            SwingUtilities.updateComponentTreeUI(sg);


                        } catch (UnsupportedLookAndFeelException exc) {
                            System.out.println("UnsupportedLookAndFeelException Error:" + exc);
                        } catch (IllegalAccessException exc) {
                            System.out.println("IllegalAccessException Error:" + exc);
                        } catch (ClassNotFoundException exc) {
                            System.out.println("ClassNotFoundException Error:" + exc);
                        } catch (InstantiationException exc) {
                            System.out.println("InstantiateException Error:" + exc);
                        }

                    }
                });
        subMenu.add(menuItem);

        mainMenu.add(subMenu);

//        showDelayDialogAction = new AbstractAction("Setting Delay ",
//                new ImageIcon("images/Setting.gif")) {
//
//            public void actionPerformed(ActionEvent e) {
//
//
//                NWMServer.task = NWMServer.DELAY_SETTING;
//
//            }
//        };
//        button = toolBar1.add(showDelayDialogAction);
//        button.setText(""); //an icon-only button
//        button.setToolTipText(" Show  the Delay Setting Dialog ");
//        menuItem = mainMenu.add(showDelayDialogAction);
//        menuItem.setIcon(null); //arbitrarily chose not to use icon in menu
//        menuItem.setMnemonic(KeyEvent.VK_S);
//        menuItem.setAccelerator(KeyStroke.getKeyStroke(
//                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
//        menuItem.setToolTipText(" Show you the Delay Setting Dialog");





    }
}
