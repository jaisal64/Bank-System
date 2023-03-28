package GUI.customer;

<<<<<<< HEAD
import coding.account.CheckingAccount;
import coding.account.SavingAccount;
import coding.account.SecurityAccount;
import coding.customer.Customer;
=======
import Backend.account.CheckingAccount;
import Backend.account.SavingAccount;
import Backend.account.SecurityAccount;
import Backend.customer.Customer;
>>>>>>> 63ed238 (update)

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * @Author: Jun Zhu
 * @Date: 12/2/2022 2:09 AM
 * @Description: TODO
 */
public class CustomerMainGUI extends JFrame implements ActionListener {
    private Customer currentCustomer;

    private JButton depositButton;
    private JButton withdrawButton;
    private JButton transferButton;
    private JButton loanButton;

    private JButton transactionButton;

    private JButton newAccountButton;
    private JButton checkAccountButton;
    private JButton stockButton;
    private JFrame frame;

    private JButton tHButton;

    private JButton logoutButton;

    private JButton currencyButton;

    public CustomerMainGUI(Customer customer){
        this.currentCustomer = customer;

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

        JLabel titleLabel = new JLabel("Hello "+currentCustomer.getName());
        titleLabel.setBounds(300,50,300,50);
        titleLabel.setFont(new java.awt.Font("Dialog", 1, 20));
        panel.add(titleLabel);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(150, 300, 100, 50);
        depositButton.addActionListener(this);
        panel.add(depositButton);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(300, 300, 100, 50);
        withdrawButton.addActionListener(this);
        panel.add(withdrawButton);

        transactionButton = new JButton("Transaction");
        transactionButton.setBounds(450, 300, 150, 50);
        transactionButton.addActionListener(this);
        panel.add(transactionButton);

        transferButton = new JButton("Transfer");
        transferButton.setBounds(150, 400, 100, 50);
        transferButton.addActionListener(this);
        panel.add(transferButton);



        stockButton = new JButton("Stock");
        stockButton.setBounds(300, 400, 100, 50);
        stockButton.addActionListener(this);
        panel.add(stockButton);

        loanButton = new JButton("Loan");
        loanButton.setBounds(450, 400, 100, 50);
        loanButton.addActionListener(this);
        panel.add(loanButton);

        tHButton = new JButton("Check Transaction History");
        tHButton.setBounds(100, 500, 300, 50);
        tHButton.addActionListener(this);
        panel.add(tHButton);

        currencyButton = new JButton("Currency");
        currencyButton.setBounds(450, 500, 100, 50);
        currencyButton.addActionListener(this);
        panel.add(currencyButton);

        newAccountButton = new JButton("Create New Account");
        newAccountButton.setBounds(100, 600, 250, 50);
        newAccountButton.addActionListener(this);
        panel.add(newAccountButton);

        checkAccountButton = new JButton("Check Account Detail");
        checkAccountButton.setBounds(400, 600, 250, 50);
        checkAccountButton.addActionListener(this);
        panel.add(checkAccountButton);

        logoutButton = new JButton("Log Out");
        logoutButton.setBounds(325, 700, 100, 50);
        logoutButton.addActionListener(this);
        panel.add(logoutButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SecurityAccount securityAccount = currentCustomer.getSecurityAccount();
        CheckingAccount checkingAccount = currentCustomer.getCheckingAccount();
        SavingAccount savingAccount = currentCustomer.getSavingAccount();
        if (e.getSource()==loanButton){
            if (checkingAccount==null){
                JOptionPane.showMessageDialog(frame,"You can not take a loan, until you create a checking account!");
            }else{
                frame.dispose();
                try {
                    new LoanListGUI(currentCustomer);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }else if (e.getSource()==depositButton){
            if (securityAccount==null && checkingAccount == null && savingAccount == null){
                JOptionPane.showMessageDialog(frame,"You can not deposit money, until you create an account!");
            }else{
                frame.dispose();
                new DepositGUI(currentCustomer);
            }
        }else if (e.getSource()==withdrawButton){
            if (securityAccount==null && checkingAccount == null && savingAccount == null){
                JOptionPane.showMessageDialog(frame,"You can not withdraw money, until you create an account!");
            }else {
                frame.dispose();
                new WithdrawGUI(currentCustomer);
            }
        }else if (e.getSource()==transferButton){
            if (securityAccount==null && checkingAccount == null && savingAccount == null){
                JOptionPane.showMessageDialog(frame,"You can not transfer money, until you create an account!");
            }else {
                frame.dispose();
                new TransferGUI(currentCustomer);
            }
        }else if (e.getSource()==newAccountButton){
            frame.dispose();
            new CreateNewAccountGUI(currentCustomer);
        }else if (e.getSource() == transactionButton){
            if (securityAccount==null && checkingAccount == null && savingAccount == null){
                JOptionPane.showMessageDialog(frame,"You can not transact money, until you create an account!");
            }else {
                frame.dispose();
                new TransactionGUI(currentCustomer);
            }
        }else  if (e.getSource()==checkAccountButton){
            if (securityAccount==null && checkingAccount == null && savingAccount == null){
                JOptionPane.showMessageDialog(frame,"You can not check your account detail, until you create an account!");
            }else {
                frame.dispose();
                new AccountDetailGUI(currentCustomer);
            }
        }else if (e.getSource()==stockButton){
            if (currentCustomer.getSecurityAccount()==null){
                JOptionPane.showMessageDialog(frame,"You can not enter stock market, until you have a security account!");
                return;
            }
            frame.dispose();
            try {
                new StockListGUI(currentCustomer);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }else if (e.getSource()==logoutButton){
            frame.dispose();
            new LoginGUI();
        }else if (e.getSource()==tHButton){
            if (securityAccount==null && checkingAccount == null && savingAccount == null){
                JOptionPane.showMessageDialog(frame,"You can not check transaction history, until you create an account!");
            }else {
                frame.dispose();
                new TransactionHistoryGUI(currentCustomer);
            }
        }else if (e.getSource() == currencyButton){
            if (currentCustomer.getSavingAccount()==null){
                JOptionPane.showMessageDialog(frame,"You can not enter currency market, until you have a saving account!");
            }else{
                frame.dispose();
                try {
                    new CurrencyListGUI(currentCustomer);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

    }
}
