package GUI.customer;

import GUI.HomePage;
import Utils.StaticData;
<<<<<<< HEAD
import coding.ATM;
import coding.customer.Customer;
=======
import Backend.ATM;
import Backend.customer.Customer;
>>>>>>> 63ed238 (update)

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Map;

/**
 * @Author: Jun Zhu
 * @Date: 12/1/2022 6:59 PM
 * @Description: TODO
 */
public class LoginGUI extends JFrame implements ActionListener {

    private JButton loginButton;
    private JButton registerButton;

    private JPasswordField passwordText;

    private JTextField idText;

    private JFrame frame;

    private JButton logoutButton;

    public LoginGUI(){

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


        JLabel titleLabel = new JLabel("Customer Login");

        titleLabel.setBounds(300,50,300,50);
        titleLabel.setFont(new java.awt.Font("Dialog", 1, 20));
        panel.add(titleLabel);


        JLabel idLabel = new JLabel("ID:");

        idLabel.setBounds(200,200,50,25);
        panel.add(idLabel);


        idText = new JTextField(20);
        idText.setBounds(300,200,165,25);
        panel.add(idText);


        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(200,300,80,25);
        panel.add(passwordLabel);


        passwordText = new JPasswordField(20);
        passwordText.setBounds(300,300,165,25);
        panel.add(passwordText);


        loginButton = new JButton("Login");
        loginButton.setBounds(200, 500, 100, 50);
        loginButton.addActionListener(this);
        panel.add(loginButton);


        registerButton = new JButton("Register");
        registerButton.setBounds(400, 500, 100, 50);
        registerButton.addActionListener(this);
        panel.add(registerButton);

        logoutButton = new JButton("Home Page");
        logoutButton.setBounds(300, 600, 100, 50);
        logoutButton.addActionListener(this);
        panel.add(logoutButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton){
            String id = this.idText.getText();
            String password = this.passwordText.getText();
            ATM atm = new ATM();
            try {
                Map<String, Object> login = atm.login(id, password);
                if (login.containsKey(StaticData.CUSTOMER)){
                    Customer customer = (Customer) login.get(StaticData.CUSTOMER);
                    frame.dispose();
                    new CustomerMainGUI(customer);
                }else{
                    JOptionPane.showMessageDialog(frame,login.get(StaticData.MESSAGE));
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == registerButton){
            frame.dispose();
            new RegisterGUI();
        }
        if (e.getSource() == logoutButton){
            frame.dispose();
            new HomePage();
        }
    }
}
