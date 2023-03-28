package GUI.customer;

import Utils.CommonUtil;
<<<<<<< HEAD
import coding.ATM;
import coding.Enum.AccountType;
import coding.customer.Customer;
=======
import Backend.ATM;
import Backend.Enum.AccountType;
import Backend.customer.Customer;
>>>>>>> 63ed238 (update)


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jun Zhu
 * @Date: 12/4/2022 5:38 PM
 * @Description: TODO
 */
public class CreateNewAccountGUI extends JFrame implements ActionListener {
        private Customer customer;

        private JTextField amountText;

        private JButton returnButton;

        private JButton confirmButton;
        private JFrame frame;

        private JComboBox<String> comboBox;

        public CreateNewAccountGUI(Customer customer){
            this.customer = customer;
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
            JLabel typeLabel = new JLabel("Create Account:");
            typeLabel.setBounds(150,300,100,50);
            panel.add(typeLabel);
            comboBox = new JComboBox<String>();
            List<String> list = new ArrayList<>();
            if (customer.getCheckingAccount()==null){
                list.add(AccountType.Checking_Type.getTypeName());
            }
            if (customer.getSavingAccount()==null){
                list.add(AccountType.Saving_Type.getTypeName());
            }
            if (customer.getSecurityAccount()==null){
              list.add(AccountType.Security_Type.getTypeName());
            }
            for (String item : list)
            {
                comboBox.addItem(item);
            }
            comboBox.setBounds(300,300,100,50);
            panel.add(comboBox);

            JLabel amountLabel = new JLabel("Deposit Amount:");
            amountLabel.setBounds(150,400,100,50);
            panel.add(amountLabel);

            amountText = new JTextField(8);
            amountText.setBounds(300,400,165,50);
            panel.add(amountText);

            confirmButton = new JButton("Confirm");
            confirmButton.setBounds(200,500,100,50);
            confirmButton.addActionListener(this);
            panel.add(confirmButton);

            returnButton = new JButton("Return");
            returnButton.setBounds(500,500,100,50);
            returnButton.addActionListener(this);
            panel.add(returnButton);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==confirmButton){
                String amountInput = amountText.getText();
                if (!CommonUtil.isDouble(amountInput)){
                    JOptionPane.showMessageDialog(frame,"Invalid Deposit Amount Input!");
                }else{
                    String selectedItem = (String) comboBox.getSelectedItem();
<<<<<<< HEAD
=======
                    double depositAmount = Double.parseDouble(amountInput);
>>>>>>> 63ed238 (update)
                    if (selectedItem.equals(AccountType.Security_Type.getTypeName()) &&
                            (customer.getSavingAccount()== null || customer.getSavingAccount().getBalance()<5000)
                            && (customer.getCheckingAccount()== null || customer.getCheckingAccount().getBalance()<5000)){
                        JOptionPane.showMessageDialog(frame,"You can not open a security account, Only when you have more than 5000$ balance in one of your checking or saving card!");
                        amountText.setText("");
                        return;
                    }
<<<<<<< HEAD
                    double depositAmount = Double.parseDouble(amountInput);
                    if (depositAmount<1000){
=======

                    if ((selectedItem.equals(AccountType.Security_Type.getTypeName()) && depositAmount<1000)){
>>>>>>> 63ed238 (update)
                        JOptionPane.showMessageDialog(frame,"At lease deposit more than 1000$ when you create a security account!");
                        return;
                    }
                    ATM atm = new ATM();
                    try {
                        atm.createNewAccount(customer.getId(),AccountType.getIndexByName(selectedItem),depositAmount);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        Customer updatedCustomer = atm.refreshCustomerData(customer);
                        JOptionPane.showMessageDialog(frame,"Creating New Account Successful!");
                        frame.dispose();
                        new CustomerMainGUI(updatedCustomer);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }else if(e.getSource() == returnButton){
                frame.dispose();
                new CustomerMainGUI(customer);
            }
        }
}
