package GUI.customer;

import Backend.customer.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author: Jun Zhu
 * @Date: 12/14/2022 7:45 AM
 * @Description: TODO
 */
public class CheckAccountBalance  extends JFrame implements ActionListener {
    private Customer customer;
    private JFrame frame;

    private JButton returnButton;

    public CheckAccountBalance(Customer customer){
        this.customer = customer;
        frame = new JFrame("Bank System");

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JLabel titleLabel = new JLabel("Customer Account Detail ");
        titleLabel.setBounds(100,50,300,50);
        titleLabel.setFont(new java.awt.Font("Dialog", 1, 15));
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
            idLabel.setBounds(100,100,200,50);
            balanceLabel.setBounds(100,150,300,50);
            panel.add(idLabel);
            panel.add(balanceLabel);
        }
        if (customer.getSavingAccount()!=null){
            JLabel idLabel = new JLabel("Saving Account ID: "+ customer.getSavingAccount().getId());
            JLabel balanceLabel = new JLabel("Saving Account Balance: "+ customer.getSavingAccount().getBalance());
            idLabel.setBounds(100,200,200,50);
            balanceLabel.setBounds(100,250,300,50);
            panel.add(idLabel);
            panel.add(balanceLabel);
        }
        if (customer.getSecurityAccount()!=null){
            JLabel idLabel = new JLabel("Security Account ID: "+ customer.getSecurityAccount().getId());
            JLabel balanceLabel = new JLabel("Security Account Balance: "+ customer.getSecurityAccount().getBalance());
            idLabel.setBounds(100,300,200,50);
            balanceLabel.setBounds(100,350,300,50);
            panel.add(idLabel);
            panel.add(balanceLabel);
        }

        returnButton = new JButton("Close");
        returnButton.setBounds(175,400,100,50);
        returnButton.addActionListener(this);
        panel.add(returnButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == returnButton){
            frame.dispose();
        }
    }
}
