package foreinsickit;

/*

steps:
------
1->javac server.java
2->javac client.java
3->java server
4->java client(on another dos prompt)

---------------
You should get the output "Hi! ASL plz?" on the client application,
as soon as you run it!

*/

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class client extends Frame implements ActionListener, Runnable
{
        Image Icon = Toolkit.getDefaultToolkit().getImage("hi.gif") ;
	Socket s;
	BufferedReader br;
	BufferedWriter bw;
	TextField text;
        Button sendBut, exitBut;
	List list;

        public client(String st)
	{
                super(st);
                setSize(300, 130);
		setIconImage(Icon);
		setLocation(300,0);
                setResizable(false);
                setBackground(new Color(192, 192, 192));
		this.setLayout(new GridLayout(2, 1));

                Panel panels[] = new Panel[2];
                panels[0] = new Panel();
                panels[1] = new Panel();
                panels[0].setLayout(new BorderLayout());
                panels[1].setLayout(new FlowLayout(FlowLayout.LEFT));

                sendBut = new Button("Send");
                exitBut = new Button("Exit");

                sendBut.addActionListener(this);
                exitBut.addActionListener(this);

                list = new List();
		text = new TextField(25);

                panels[0].add(list);
                panels[1].add(text);
                panels[1].add(sendBut);
                panels[1].add(exitBut);     


                add(panels[0]);
                add(panels[1]);

		setVisible(true);

                try
                {
                        /* Assuming that this application is run on single
                        machine I've used the default ip i.e., 127.0.0.1. If
                        you want to use it on 2 different machines use the
                        ip that is assigned to the machine on which server
                        applicatin is residing*/

                        s = new Socket("127.0.0.1", 1053);
                        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                        bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			Thread th;
			th = new Thread(this);
			th.start();
			
		}catch(Exception e){}
		
	}

        public static void main(String arg[])
	{
                // create an object instance of the class
                // by sending the title as parameter
                new client("Client Application");
		
	}

    private client() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    

        public void run()
	{
                while (true)
		{
			try
                        {
                                list.addItem(br.readLine());
			}catch (Exception h){}
		}
	}
	

        public void actionPerformed(ActionEvent ae)
	{
                 if(ae.getSource().equals(exitBut))
			 System.exit(0);
		 else
                 {
                        try
                        {
                                bw.write(text.getText());
                                bw.newLine();
                                bw.flush();
                                text.setText("");
                        }catch(Exception m){}
		 }
				  
	}
          {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new client().setVisible(true);
            }
        });
    }
	
}
