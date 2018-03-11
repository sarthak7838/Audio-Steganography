/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package remotescreencapture;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

class ConnectionHandler implements Runnable {

    private Socket socket;

    public ConnectionHandler(Socket socket) {
        this.socket = socket;
        Thread t = new Thread(this);
        t.start();

    }

    public void run() {
        try {

            // Read a message sent by client application
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message Received: " + message);
            JOptionPane.showMessageDialog(null, message);
            // Send a response information to the client application
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject("Hi...");
            ois.close();
            oos.close();
            socket.close();
            System.out.println("Waiting for client message...");

        } catch (IOException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }

    }
}
