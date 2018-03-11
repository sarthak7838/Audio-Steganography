/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package remotescreencapture;

/**
 *
 * @author Administrator
 */
public class Timer extends Thread {

   
    protected int m_rate = 100;   
    private int m_length;   
    private int m_elapsed;

    public Timer(int length) {      
        m_length = length;       
        m_elapsed = 0;
    }

    /** Resets the timer back to zero */
    public synchronized void reset() {
        m_elapsed = 0;
    }

    
    public void run() {        
        for (;;) {
            // Put the timer to sleep
            try {
                Thread.sleep(m_rate);
            } catch (InterruptedException ioe) {
                continue;
            }

            // Use 'synchronized' to prevent conflicts
            synchronized (this) {
                // Increment time remaining
                m_elapsed += m_rate;

                // Check to see if the time has been exceeded
                if (m_elapsed > m_length) {
                    // Trigger a timeout
                    timeout();
                }
            }

        }
    }

    // Override this to provide custom functionality
    public void timeout() {
        System.err.println("Network timeout occurred.... terminating");
        System.exit(1);
    }
}
