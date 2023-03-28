package GUI.manager;

import GUI.HomePage;
<<<<<<< HEAD
import coding.BankManager;
import coding.customer.Customer;
=======
import Backend.manager.BankManager;
import Backend.customer.Customer;
>>>>>>> 63ed238 (update)

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author: Jun Zhu
 * @Date: 12/6/2022 5:08 PM
 * @Description: TODO
 */
public class CustomerAccountsDetailGUI extends JFrame implements ActionListener {

    private BankManager bankManager;
    private Customer customer;
    private JFrame frame;

    private JButton returnButton;
    private JButton logoutButton;

    public CustomerAccountsDetailGUI(BankManager bankManager, Customer customer){
        this.bankManager = bankManager;
        this.customer = customer;
        frame = new JFrame("Bank System");

        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JLabel titleLabel = new JLabel("Customer Account Detail ");
        titleLabel.setBounds(270,50,300,50);
        titleLabel.setFont(new java.awt.Font("Dialog", 1, 20));
        panel.add(titleLabel);

        frame.add(panel);

        placeComponents(panel);

        frame.setVisible(true);
    }

    private  void placeComponents(JPanel panel) {
        panel.setLayout(null);
        if (customer.getCheckingAccount()!=null){
            JLabel idLabel = new JLabel("Checking Account ID: "+ customer.getCheckingAccount().getId());
            JLabel balanceLabel = new JLabel("Checking Account Balance: "+ customer.getCheckingAccount().getBalance());
            idLabel.setBounds(200,200,250,50);
            balanceLabel.setBounds(200,250,250,50);
            panel.add(idLabel);
            panel.add(balanceLabel);
        }
        if (customer.getSavingAccount()!=null){
            JLabel idLabel = new JLabel("Saving Account ID: "+ customer.getSavingAccount().getId());
            JLabel balanceLabel = new JLabel("Saving Account Balance: "+ customer.getSavingAccount().getBalance());
            idLabel.setBounds(200,300,250,50);
            balanceLabel.setBounds(200,350,250,50);
            panel.add(idLabel);
            panel.add(balanceLabel);
        }
        if (customer.getSecurityAccount()!=null){
            JLabel idLabel = new JLabel("Security Account ID: "+ customer.getSecurityAccount().getId());
            JLabel balanceLabel = new JLabel("Security Account Balance: "+ customer.getSecurityAccount().getBalance());
            idLabel.setBounds(200,400,250,50);
            balanceLabel.setBounds(200,450,250,50);
            panel.add(idLabel);
            panel.add(balanceLabel);
        }

        returnButton = new JButton("Return");
        returnButton.setBounds(350,550,100,50);
        returnButton.addActionListener(this);
        panel.add(returnButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == returnButton){
            frame.dispose();
            new CustomerProfileGUI(this.bankManager, this.customer);
        }
        else if(e.getSource() == logoutButton){
            frame.dispose();
            new HomePage();
        }
    }
}
