package GUI.customer;

import Utils.CommonUtil;
<<<<<<< HEAD
import coding.ATM;
import coding.currency.Currency;
import coding.customer.Customer;
import coding.stock.Stock;
=======
import Backend.ATM;
import Backend.currency.Currency;
import Backend.customer.Customer;
>>>>>>> 63ed238 (update)

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * @Author: Jun Zhu
 * @Date: 12/10/2022 6:48 PM
 * @Description: TODO
 */
public class BuyingCurrencyGUI extends JFrame implements ActionListener {

    private JButton confirmButton;
    private JButton returnButton;

    private JTextField amountText;

    private JFrame frame;

    private Customer customer;
    private Currency currency;

    public BuyingCurrencyGUI(Customer customer, Currency currency){

        this.customer = customer;
        this.currency = currency;
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
        JLabel titleLabel = new JLabel("Buying Currency");
        titleLabel.setBounds(300,50,300,50);
        titleLabel.setFont(new java.awt.Font("Dialog", 1, 20));
        panel.add(titleLabel);


        JLabel idLabel = new JLabel("Currency ID: " + currency.getId());
        idLabel.setBounds(300,200,200,50);
        panel.add(idLabel);
        JLabel nameLabel = new JLabel("Currency Name: " + currency.getName());
        nameLabel.setBounds(300,300,200,50);
        panel.add(nameLabel);
        JLabel priceLabel = new JLabel("Currency Rate: " + currency.getRate());
        priceLabel.setBounds(300,400,200,50);
        panel.add(priceLabel);

        JLabel amountLabel = new JLabel("Buying Amount:");
        amountLabel.setBounds(150,500,100,50);
        panel.add(amountLabel);

        amountText = new JTextField(8);
        amountText.setBounds(300,500,165,50);
        panel.add(amountText);

        confirmButton = new JButton("Confirm");
        confirmButton.setBounds(200, 600, 100, 50);
        confirmButton.addActionListener(this);
        panel.add(confirmButton);


        returnButton = new JButton("Return");
        returnButton.setBounds(400, 600, 100, 50);
        returnButton.addActionListener(this);
        panel.add(returnButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnButton){
            frame.dispose();
            try {
<<<<<<< HEAD
                new StockListGUI(customer);
=======
                new CurrencyListGUI(customer);
>>>>>>> 63ed238 (update)
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource()==confirmButton){
            if (!CommonUtil.isNumeric(amountText.getText())){
                JOptionPane.showMessageDialog(frame,"Invalid buying amount input!");
                return;
            }
            int amount = Integer.parseInt(amountText.getText());
            if (amount * currency.getRate() > customer.getSavingAccount().getBalance()){
                JOptionPane.showMessageDialog(frame,"You do not have enough balance in your saving account!");
                return;
            }
            ATM atm = new ATM();
            try {
                currency.setAmount(amount);
                atm.buyCurrency(currency,customer.getSavingAccount());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(frame,"Buying Currency Successful!");
            Customer updatedCustomer = null;
            try {
                updatedCustomer = atm.refreshCustomerData(customer);
                frame.dispose();
                new CurrencyListGUI(updatedCustomer);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
}
