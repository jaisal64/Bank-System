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
 * @Date: 12/5/2022 4:05 AM
 * @Description: TODO
 */
public class TransferGUI extends JFrame implements ActionListener {

        private Customer customer;

        private JTextField amountText;

        private JButton returnButton;

        private JButton confirmButton;

        private JComboBox<String> sendComboBox;

        private JFrame frame;

        private JComboBox<String> receiveComboBox;

<<<<<<< HEAD
=======
        private JButton checkBalanceButton;

>>>>>>> 63ed238 (update)
        public TransferGUI(Customer customer){
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
            JLabel sendLabel = new JLabel("Send Account:");
            sendLabel.setBounds(150,200,100,50);
            panel.add(sendLabel);

            sendComboBox = new JComboBox<>();
            List<String> list = new ArrayList<>();
            List<String> list1 = new ArrayList<>();
            if (customer.getCheckingAccount()!=null){
                list.add(AccountType.Checking_Type.getTypeName());
                list1.add(AccountType.Checking_Type.getTypeName());
            }
            if (customer.getSavingAccount()!=null){
                list.add(AccountType.Saving_Type.getTypeName());
                list1.add(AccountType.Saving_Type.getTypeName());
            }
            if (customer.getSecurityAccount()!=null){
                list.add(AccountType.Security_Type.getTypeName());
                list1.add(AccountType.Security_Type.getTypeName());
            }
            for (String item : list)
            {
                sendComboBox.addItem(item);
            }
            sendComboBox.setBounds(300,200,100,50);
            panel.add(sendComboBox);

            JLabel receiveLabel = new JLabel("Receive Account:");
            receiveLabel.setBounds(150,300,100,50);
            panel.add(receiveLabel);
            receiveComboBox = new JComboBox<>();
            for (String item : list1)
            {
                receiveComboBox.addItem(item);
            }
            receiveComboBox.setBounds(300,300,100,50);
            panel.add(receiveComboBox);

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
<<<<<<< HEAD
            returnButton.setBounds(500,500,100,50);
            returnButton.addActionListener(this);
            panel.add(returnButton);

=======
            returnButton.setBounds(400,500,100,50);
            returnButton.addActionListener(this);
            panel.add(returnButton);

            checkBalanceButton = new JButton("Check Account Balance ");
            checkBalanceButton.setBounds(275,600,200,50);
            checkBalanceButton.addActionListener(this);
            panel.add(checkBalanceButton);

>>>>>>> 63ed238 (update)
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            ATM atm = new ATM();
            if (e.getSource()==confirmButton){
                String amountInput = amountText.getText();
                if (!CommonUtil.isDouble(amountInput)){
                    JOptionPane.showMessageDialog(frame,"Invalid Deposit Amount Input!");
                }else{
                    String receiveSelectedItem = (String) receiveComboBox.getSelectedItem();
                    String sendSelectItem = (String) sendComboBox.getSelectedItem();
                    if (receiveSelectedItem.equals(sendSelectItem)){
                        JOptionPane.showMessageDialog(frame,"You can not transfer to the same account !");
                        return;
                    }
                    double amount = Double.parseDouble(amountInput);
                    if (sendSelectItem.equals(AccountType.Saving_Type.getTypeName())){
                        if (customer.getSavingAccount().getBalance()<amount){
                            JOptionPane.showMessageDialog(frame,"Your Do Not Have Enough Money In Your Saving Account");
                            return;
                        }else{
                            if (receiveSelectedItem.equals(AccountType.Checking_Type.getTypeName())){
                                try {
                                    atm.transfer(customer.getSavingAccount().getId(),customer.getCheckingAccount().getId(),amount);
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }else if (receiveSelectedItem.equals(AccountType.Security_Type.getTypeName())){
                                try {
                                    atm.transfer(customer.getSavingAccount().getId(),customer.getSecurityAccount().getId(),amount);
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        }
                    }else if (sendSelectItem.equals(AccountType.Checking_Type.getTypeName())){
                        if (customer.getCheckingAccount().getBalance()<amount){
                            JOptionPane.showMessageDialog(frame,"Your Do Not Have Enough Money In Your Checking Account");
                            return;
                        }else{
                            if (receiveSelectedItem.equals(AccountType.Saving_Type.getTypeName())){
                                try {
                                    atm.transfer(customer.getCheckingAccount().getId(),customer.getSavingAccount().getId(),amount);
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }else if (receiveSelectedItem.equals(AccountType.Security_Type.getTypeName())){
                                try {
                                    atm.transfer(customer.getCheckingAccount().getId(),customer.getSecurityAccount().getId(),amount);
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        }
                    }else if (sendSelectItem.equals(AccountType.Security_Type.getTypeName())){
                        if (customer.getSecurityAccount().getBalance()<amount){
                            JOptionPane.showMessageDialog(frame,"Your Do Not Have Enough Money In Your Security Account");
                            return;
                        }else {
                            if (receiveSelectedItem.equals(AccountType.Checking_Type.getTypeName())){
                                try {
                                    atm.transfer(customer.getSecurityAccount().getId(),customer.getCheckingAccount().getId(),amount);
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }else if (receiveSelectedItem.equals(AccountType.Saving_Type.getTypeName())){
                                try {
                                    atm.transfer(customer.getSecurityAccount().getId(),customer.getSavingAccount().getId(),amount);
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        }
                    }
                    try {
                        customer = atm.refreshCustomerData(customer);
                        JOptionPane.showMessageDialog(frame,"Transfer Successful!");
                        amountText.setText("");
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }else if(e.getSource() == returnButton){
                Customer updatedCustomer = null;
                try {
                    updatedCustomer = atm.refreshCustomerData(customer);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
                new CustomerMainGUI(updatedCustomer);
<<<<<<< HEAD
=======
            }else if(e.getSource() == checkBalanceButton){
                new CheckAccountBalance(customer);
>>>>>>> 63ed238 (update)
            }
        }
}
