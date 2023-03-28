package GUI.customer;

import Utils.CommonUtil;
<<<<<<< HEAD
import coding.ATM;
import coding.customer.Customer;
import coding.stock.Stock;
=======
import Backend.ATM;
import Backend.customer.Customer;
import Backend.stock.Stock;
>>>>>>> 63ed238 (update)

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * @Author: Jun Zhu
 * @Date: 12/7/2022 11:13 AM
 * @Description: TODO
 */
public class BuyingStockGUI extends JFrame implements ActionListener {

    private JButton confirmButton;
    private JButton returnButton;

    private JTextField amountText;

    private JFrame frame;

    private Customer customer;
    private Stock stock;

    public BuyingStockGUI(Customer customer, Stock stock){

        this.customer = customer;
        this.stock = stock;
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


        JLabel titleLabel = new JLabel("Buying Stock");
        titleLabel.setBounds(300,50,300,50);
        titleLabel.setFont(new java.awt.Font("Dialog", 1, 20));
        panel.add(titleLabel);


        JLabel idLabel = new JLabel("Stock ID: " + stock.getId());
        idLabel.setBounds(300,200,200,50);
        panel.add(idLabel);
        JLabel nameLabel = new JLabel("Stock Name: " + stock.getName());
        nameLabel.setBounds(300,300,200,50);
        panel.add(nameLabel);
        JLabel priceLabel = new JLabel("Stock Price: " + stock.getPrice());
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
                new StockListGUI(customer);
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
            if (amount * stock.getPrice() > customer.getSecurityAccount().getBalance()){
                JOptionPane.showMessageDialog(frame,"You do not have enough balance in your security account!");
                return;
            }
            ATM atm = new ATM();
            try {
                atm.buyStock(customer.getSecurityAccount(),stock,amount);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(frame,"Buying Stock Successful!");
            Customer updatedCustomer = null;
            try {
                updatedCustomer = atm.refreshCustomerData(customer);
                frame.dispose();
                new StockListGUI(updatedCustomer);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
}
