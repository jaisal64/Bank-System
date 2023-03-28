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
 * @Date: 12/4/2022 9:03 PM
 * @Description: TODO
 */
public class WithdrawGUI extends JFrame implements ActionListener {

        private Customer customer;

        private JTextField amountText;

        private JButton returnButton;

        private JButton confirmButton;

        private JComboBox<String> comboBox;

        private JFrame frame;

<<<<<<< HEAD
=======
        private JButton checkBalanceButton;

>>>>>>> 63ed238 (update)
        public WithdrawGUI(Customer customer){
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
            JLabel typeLabel = new JLabel("Withdraw Account:");
            typeLabel.setBounds(150,300,100,50);
            panel.add(typeLabel);

            comboBox = new JComboBox<>();
            List<String> list = new ArrayList<>();
            if (customer.getCheckingAccount()!=null){
                list.add(AccountType.Checking_Type.getTypeName());
            }
            if (customer.getSavingAccount()!=null){
                list.add(AccountType.Saving_Type.getTypeName());
            }
            if (customer.getSecurityAccount()!=null){
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
                    double withdrawAmount = Double.parseDouble(amountInput);
                    String selectedItem = (String) comboBox.getSelectedItem();
                    if (selectedItem.equals(AccountType.Saving_Type.getTypeName())){
                        if (customer.getSavingAccount().getBalance()<withdrawAmount){
                            JOptionPane.showMessageDialog(frame,"Your Do Not Have Enough Money In Your Saving Account");
                            return;
                        }else if (customer.getSavingAccount().getBalance()<withdrawAmount+1){
                            JOptionPane.showMessageDialog(frame,"Your Do Not Have Enough Money, since you have to pay 1$ more fee!");
                            return;
                        } else{
                            try {
                                atm.withdraw(customer.getSavingAccount(),withdrawAmount);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }else if (selectedItem.equals(AccountType.Checking_Type.getTypeName())){
                        if (customer.getCheckingAccount().getBalance()<withdrawAmount){
                            JOptionPane.showMessageDialog(frame,"Your Do Not Have Enough Money In Your Checking Account");
                            return;
                        }else if (customer.getCheckingAccount().getBalance()<withdrawAmount+1){
                            JOptionPane.showMessageDialog(frame,"Your Do Not Have Enough Money, since you have to pay 1$ more fee!");
                            return;
                        }else{
                            try {
                                atm.withdraw(customer.getCheckingAccount(),withdrawAmount);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }else if (selectedItem.equals(AccountType.Security_Type.getTypeName())){
                        if (customer.getSecurityAccount().getBalance()<withdrawAmount){
                            JOptionPane.showMessageDialog(frame,"Your Do Not Have Enough Money In Your Security Account");
                            return;
                        }else if (customer.getSecurityAccount().getBalance()<withdrawAmount+1){
                            JOptionPane.showMessageDialog(frame,"Your Do Not Have Enough Money, since you have to pay 1$ more fee!");
                            return;
                        }else {
                            try {
                                atm.withdraw(customer.getSecurityAccount(),withdrawAmount);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(frame,"Withdraw Successful!");
                    amountText.setText("");
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
