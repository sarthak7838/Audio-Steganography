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

public class server extends Frame implements ActionListener, Runnable
{
        Image Icon = Toolkit.getDefaultToolkit().getImage("hi.gif");
        ServerSocket ss;
        Socket s;
	BufferedReader br;
	BufferedWriter bw;
	TextField text;
        Button sendBut, exitBut;
        List list;

        public server(String m) // class constructor
	{
                super(m);
                setSize(300, 130);
                setLocation(0,0);
                setIconImage(Icon);
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
                list.addItem("Server up & Listening on port plz wait...");

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
                        ss = new ServerSocket(1053);//some port number, better be above 1000
                        s = ss.accept();
                        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                        bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                        bw.write("Hi! ASL plz??");
                        bw.newLine();
                        bw.flush();
                        Thread th;
                        th = new Thread(this);
                        th.start();
			 
			
		}catch(Exception e){}

	}

    server() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

        public void run()
	{
                while (true)
		{
                        try                       
                        {
                                list.addItem(br.readLine());
                        }catch (Exception e){}
		}
	}

        public static void main(String arg[])
	{
                // create an object instance
                // by sending the title as a parameter
                new server("Server Applicaton");
	}
	
        public void actionPerformed(ActionEvent ae)
	{
                 if (ae.getSource().equals(exitBut))
			 System.exit(0);
                 else
                 {
                        try
                        {
                                bw.write(text.getText());
                                bw.newLine();bw.flush();
                                text.setText("");
                        }catch(Exception x){}
		 }
				  
	}
	
}
