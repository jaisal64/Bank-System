package GUI;

import GUI.customer.LoginGUI;
import GUI.manager.ManagerLoginGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author: Jun Zhu
 * @Date: 12/1/2022 6:55 PM
 * @Description: TODO
 */
public class HomePage extends JFrame implements ActionListener {
    private JButton customerLoginButton;
    private JButton managerLoginButton;

    private JFrame frame;

    public HomePage(){

        frame = new JFrame("Bank System");
        // Setting the width and height of frame
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel panel = new JPanel();

        frame.add(panel);

        placeComponents(panel);

        frame.setVisible(true);
    }

    private  void placeComponents(JPanel panel) {


        panel.setLayout(null);


        JLabel userLabel = new JLabel("Welcome To Bank System");

        userLabel.setBounds(300,50,300,50);
        userLabel.setFont(new java.awt.Font("Dialog", 1, 20));
        panel.add(userLabel);


        customerLoginButton = new JButton("Customer Log in");
        customerLoginButton.setBounds(300, 500, 200, 50);
        customerLoginButton.addActionListener(this);
        panel.add(customerLoginButton);


        managerLoginButton = new JButton("Manager Log in");
        managerLoginButton.setBounds(300, 580, 200, 50);
        managerLoginButton.addActionListener(this);
        panel.add(managerLoginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == customerLoginButton){
            frame.dispose();
            new LoginGUI();
        }
        if (e.getSource() == managerLoginButton){
            frame.dispose();
            new ManagerLoginGUI();
        }
    }
}