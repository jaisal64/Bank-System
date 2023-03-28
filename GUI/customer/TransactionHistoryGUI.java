package GUI.customer;

<<<<<<< HEAD
import coding.Enum.AccountType;
import coding.account.CheckingAccount;
import coding.account.SavingAccount;
import coding.account.SecurityAccount;
import coding.customer.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
=======
import Backend.Enum.AccountType;
import Backend.customer.Customer;

import javax.swing.*;
>>>>>>> 63ed238 (update)
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jun Zhu
 * @Date: 12/7/2022 4:09 PM
 * @Description: TODO
 */
public class TransactionHistoryGUI extends JFrame implements ActionListener {

    private Customer customer;

    private JButton returnButton;

    private JButton confirmButton;

    private JComboBox<String> comboBox;

    private JFrame frame;


    public TransactionHistoryGUI(Customer customer)  {
            this.customer = customer;

            frame = new JFrame("Bank System");

            frame.setSize(800, 800);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            JPanel panel = new JPanel();

            JLabel titleLabel = new JLabel("Transaction History");
            titleLabel.setBounds(270,50,300,50);
            titleLabel.setFont(new java.awt.Font("Dialog", 1, 20));
            panel.add(titleLabel);

            frame.add(panel);

            placeComponents(panel);

            frame.setVisible(true);
        }

        private  void placeComponents(JPanel panel) {
            panel.setLayout(null);
            JLabel typeLabel = new JLabel("Select Account Type:");
            typeLabel.setBounds(150,300,200,50);
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
            comboBox.setBounds(400,300,150,50);
            panel.add(comboBox);


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
            String selectedItem = (String) comboBox.getSelectedItem();
                if (selectedItem.equals(AccountType.Saving_Type.getTypeName())){
                    frame.dispose();
                    try {
                        new TransactionHistoryListGUI(customer,customer.getSavingAccount().getId());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }else if (selectedItem.equals(AccountType.Checking_Type.getTypeName())){
                    frame.dispose();
                    try {
                        new TransactionHistoryListGUI(customer,customer.getCheckingAccount().getId());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }else if (selectedItem.equals(AccountType.Security_Type.getTypeName())){
                    frame.dispose();
                    try {
                        new TransactionHistoryListGUI(customer,customer.getSecurityAccount().getId());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
        }
        if (e.getSource()==returnButton){
            frame.dispose();
            new CustomerMainGUI(customer);
        }
    }
}
