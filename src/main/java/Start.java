import model.Model;
import view.AppFrame;

import javax.swing.*;

/**
 * This is where my program starts
 *
 * @author ET
 * @version 25/09/2020 15:08
 */
public class Start {
    /**
     * Standard start code to run a Swing based GUI application.
     * Basically, this creates a thread in which the application runs.
     *
     * @param args parameters passed to the program when it is started
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AppFrame(new Model()));
    }
}
