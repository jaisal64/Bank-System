package Utils;

import javax.swing.*;

public class GUIUtils {

    public static JFrame initializeFrameTemplate(){
        JFrame frame = new JFrame("Bank System");

        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return frame;
    }

}
