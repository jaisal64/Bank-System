package GUI.customer;

<<<<<<< HEAD
import coding.ATM;
import coding.Enum.AccountType;
import coding.Enum.TransactionType;
import coding.customer.Customer;
import coding.stock.Stock;
import coding.transaction.TransactionHistory;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
=======
import Backend.ATM;
import Backend.Enum.TransactionType;
import Backend.customer.Customer;
import Backend.transaction.TransactionHistory;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
>>>>>>> 63ed238 (update)
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Jun Zhu
 * @Date: 12/8/2022 6:51 AM
 * @Description: TODO
 */
public class TransactionHistoryListGUI extends JFrame implements ActionListener {

    private Customer currentCustomer;
    private long accountId;
    private JFrame frame;

    private JButton myStockButton;
    private JButton returnButton;

    private JTable jTable;

    public TransactionHistoryListGUI(Customer customer, long accountId) throws SQLException {
        this.currentCustomer = customer;
        this.accountId = accountId;

        frame = new JFrame("Bank System");

        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel panel = new JPanel();

        JLabel titleLabel = new JLabel("Transaction History List");
        titleLabel.setBounds(270,50,300,50);
        titleLabel.setFont(new java.awt.Font("Dialog", 1, 20));
        panel.add(titleLabel);

        frame.add(panel);

        placeComponents(panel);


        frame.setVisible(true);
    }

    private  void placeComponents(JPanel panel) throws SQLException {
        panel.setLayout(null);
        ATM atm = new ATM();
        List<TransactionHistory> accountTransaction = atm.getAccountTransaction(accountId);
        Object[] columnTitle = {"Date","Type","Amount","SendID","ReceiverID"};
        Object[][] tableData = new Object[accountTransaction.size()][6];
        for (int i=0;i<accountTransaction.size();i++){
            TransactionHistory transactionHistory = accountTransaction.get(i);
            tableData[i] = new Object[]{transactionHistory.getDate(), TransactionType.getNameByIndex(transactionHistory.getTransactionType()),transactionHistory.getAmount(),transactionHistory.getSenderId(),
                    transactionHistory.getReceiverId()};
        }

        jTable = new JTable(tableData,columnTitle);
        // set the size of height and column of table
        TableColumn column = null;
        jTable.setRowHeight(40);
        int columnCount = jTable.getColumnCount();
        for(int i = 0; i < columnCount; i++)
        {
            column = jTable.getColumnModel().getColumn(i);
            column.setPreferredWidth(155);
        }

        // set the data of table displays at middle of table
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) jTable.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(renderer.CENTER);
        DefaultTableCellRenderer r=new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        jTable.setDefaultRenderer(Object.class,r);


        JScrollPane scroll = new JScrollPane(jTable);
        scroll.setBounds(0,200,800, 400);
        panel.add(scroll);

        myStockButton = new JButton("Check Your Stock");
        myStockButton.setBounds(150,650,200,50);
        myStockButton.addActionListener(this);
        panel.add(myStockButton);

        returnButton = new JButton("Return");
        returnButton.setBounds(450,650,100,50);
        returnButton.addActionListener(this);
        panel.add(returnButton);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myStockButton){

        }
        if(e.getSource() == returnButton){
            frame.dispose();
            new TransactionHistoryGUI(currentCustomer);
        }
    }
}
