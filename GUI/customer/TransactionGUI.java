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
 * @Date: 12/5/2022 4:35 AM
 * @Description: TODO
 */
public class TransactionGUI extends JFrame implements ActionListener {
    private Customer customer;

    private JTextField amountText;

    private JButton returnButton;

    private JButton confirmButton;

    private JComboBox<String> sendComboBox;

    private JFrame frame;

    private JTextField receiverField;

<<<<<<< HEAD
=======
    private JButton checkBalanceButton;

>>>>>>> 63ed238 (update)
    public TransactionGUI(Customer customer){
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
            sendComboBox.addItem(item);
        }
        sendComboBox.setBounds(300,200,100,50);
        panel.add(sendComboBox);

        JLabel receiveLabel = new JLabel("Receive Account:");
        receiveLabel.setBounds(150,300,100,50);
        panel.add(receiveLabel);

        receiverField = new JTextField(20);
        receiverField.setBounds(300,300,100,50);
        panel.add(receiverField);


        JLabel amountLabel = new JLabel("Transaction Amount:");
        amountLabel.setBounds(150,400,150,50);
        panel.add(amountLabel);

        amountText = new JTextField(8);
        amountText.setBounds(350,400,100,50);
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
        if(e.getSource()==returnButton){
            Customer updatedCustomer = null;
            try {
                updatedCustomer = atm.refreshCustomerData(customer);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            frame.dispose();
            new CustomerMainGUI(updatedCustomer);
        }else if (e.getSource()==confirmButton){
            String amount = amountText.getText();
            if (!CommonUtil.isDouble(amount)){
                JOptionPane.showMessageDialog(frame,"Invalid Amount Input!");
                return;
            }
            String receiverIdField = receiverField.getText();
            if (!CommonUtil.isDouble(receiverIdField)){
                JOptionPane.showMessageDialog(frame,"Invalid Receiver ID Input!");
                return;
            }
            int receiverId = Integer.parseInt(receiverIdField);
            double amountValue = Double.parseDouble(amount);
            try {
                if (!atm.searchAccountById(receiverId)){
                    JOptionPane.showMessageDialog(frame,"Can Not find this receiver Account!");
                    return;
                }
                String selectedAccount = (String) sendComboBox.getSelectedItem();
                if ((selectedAccount.equals(AccountType.Saving_Type.getTypeName()) && customer.getSavingAccount().getBalance() < amountValue)||
                        (selectedAccount.equals(AccountType.Checking_Type.getTypeName()) && customer.getCheckingAccount().getBalance()<amountValue)||
                        (selectedAccount.equals(AccountType.Security_Type.getTypeName()) && customer.getSecurityAccount().getBalance() < amountValue)) {
                    JOptionPane.showMessageDialog(frame,"Your Account does not have enough balance!");
                }else {
                    if (selectedAccount.equals(AccountType.Saving_Type.getTypeName())){
                        atm.transaction(customer.getSavingAccount().getId(),amountValue,receiverId);
                    }else if (selectedAccount.equals(AccountType.Checking_Type.getTypeName())){
                        atm.transaction(customer.getCheckingAccount().getId(),amountValue,receiverId);
                    }else if (selectedAccount.equals(AccountType.Security_Type.getTypeName())){
                        atm.transaction(customer.getSecurityAccount().getId(),amountValue,receiverId);
                    }
                    customer = atm.refreshCustomerData(customer);
                    JOptionPane.showMessageDialog(frame,"Transaction Successful!");
                    amountText.setText("");
                    receiverField.setText("");
                }

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
<<<<<<< HEAD
=======
        }else if(e.getSource() == checkBalanceButton){
            new CheckAccountBalance(customer);
>>>>>>> 63ed238 (update)
        }
    }
}
