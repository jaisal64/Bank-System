package GUI.customer;

import Utils.CommonUtil;
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
 * @Date: 12/2/2022 2:03 AM
 * @Description: TODO
 */
public class RegisterGUI extends JFrame implements ActionListener {
    private JButton cancelButton;
    private JButton registerButton;

    private JPasswordField passwordText;

    private JTextField idText;
    private JTextField nameText;
    private JTextField phoneNumText;

    private JFrame frame;

    public RegisterGUI(){
        frame = new JFrame("Bank System");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);

        placeComponents(panel);

        frame.setVisible(true);
    }

    private  void placeComponents(JPanel panel) {


        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Customer Register");

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

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(200,400,80,25);
        panel.add(nameLabel);


        nameText = new JTextField(20);
        nameText.setBounds(300,400,165,25);
        panel.add(nameText);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(200,500,120,25);
        panel.add(phoneLabel);


        phoneNumText = new JTextField(20);
        phoneNumText.setBounds(350,500,165,25);
        panel.add(phoneNumText);


        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 600, 100, 50);
        cancelButton.addActionListener(this);
        panel.add(cancelButton);


        registerButton = new JButton("Register");
        registerButton.setBounds(400, 600, 100, 50);
        registerButton.addActionListener(this);
        panel.add(registerButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton){
            this.dispose();
            new LoginGUI();
        }
        if (e.getSource() == registerButton){
            if (phoneNumText.getText().isEmpty() || nameText.getText().isEmpty() || idText.getText().isEmpty() || passwordText.getText().isEmpty()){
                JOptionPane.showMessageDialog(frame,"Your can not enter an empty value!");
                return;
            }
            if (!CommonUtil.isNumeric(phoneNumText.getText())){
                JOptionPane.showMessageDialog(frame,"Invalid Phone Number!");
                return;
            }
            ATM atm = new ATM();
            Customer newCustomer = new Customer(nameText.getText(),Integer.parseInt(phoneNumText.getText()),passwordText.getText(),idText.getText());
            try {
                Map<String, String> register = atm.register(newCustomer);
                //unsuccessful create a new customer
                if (register.containsKey(StaticData.MESSAGE)){
                    JOptionPane.showMessageDialog(frame,register.get(StaticData.MESSAGE));

                }else{
                    JOptionPane.showMessageDialog(frame,"Register successful!");
                    frame.dispose();
                    new LoginGUI();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    }

}
