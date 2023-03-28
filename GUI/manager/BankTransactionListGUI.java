package GUI.manager;

import GUI.HomePage;
import Utils.GUIUtils;
<<<<<<< HEAD
import coding.BankManager;
import coding.transaction.TransactionHistory;
=======
import Backend.manager.BankManager;
import Backend.transaction.TransactionHistory;
>>>>>>> 63ed238 (update)

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class BankTransactionListGUI extends JFrame implements ActionListener {

    private BankManager bankManager;
    private JFrame frame;

    private JTextField transactionDateField;
    private JButton submitButton;
    private JButton returnButton;
    private JButton logoutButton;

    public BankTransactionListGUI(BankManager bankManager) throws SQLException {
        this.bankManager = bankManager;
        frame = GUIUtils.initializeFrameTemplate();
        placeComponents("");
        frame.setVisible(true);
    }

    public BankTransactionListGUI(BankManager bankManager, String date) throws SQLException {
        this.bankManager = bankManager;
        frame = GUIUtils.initializeFrameTemplate();
        placeComponents(date);
        frame.setVisible(true);
    }

    private void placeComponents(String date) throws SQLException {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel dateLabel = new JLabel("Transaction Date:");
        dateLabel.setBounds(150,100,150,50);
        panel.add(dateLabel);

        transactionDateField = new JTextField(20);
        transactionDateField.setBounds(350,100,100,50);
        panel.add(transactionDateField);

        submitButton = new JButton("Submit");
        submitButton.setBounds(500,100,100,50);
        submitButton.addActionListener(this);
        panel.add(submitButton);
        Object[] columnTitle;
        Object[][] tableData;
        if(!date.isEmpty()) {
            List<TransactionHistory> transactionList = bankManager.getTransactionService().getTransactionsByDate(date);
            columnTitle = new Object[]{"ID", "Amount", "Sender ID", "Receiver ID", "Date", "Type"};
            tableData = new Object[transactionList.size()][6];
            for (int i = 0; i < transactionList.size(); i++) {
                tableData[i] = new Object[]{transactionList.get(i).getId(), transactionList.get(i).getAmount(),
                        transactionList.get(i).getSenderId(), transactionList.get(i).getReceiverId(),
                        transactionList.get(i).getDate(), transactionList.get(i).getTransactionType()};
            }
        }
        else{
            List<TransactionHistory> transactionList = bankManager.getTransactionService().getAllTransactions();
            columnTitle = new Object[]{"ID", "Amount", "Sender ID", "Receiver ID", "Date", "Type"};
            tableData = new Object[transactionList.size()][6];
            for (int i = 0; i < transactionList.size(); i++) {
                tableData[i] = new Object[]{transactionList.get(i).getId(), transactionList.get(i).getAmount(),
                        transactionList.get(i).getSenderId(), transactionList.get(i).getReceiverId(),
                        transactionList.get(i).getDate(), transactionList.get(i).getTransactionType()};
            }
        }

        JTable jTable = new JTable(tableData, columnTitle);

        // set the size of height and column of table
        TableColumn column = null;
        jTable.setRowHeight(40);
        int columnCount = jTable.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            column = jTable.getColumnModel().getColumn(i);
            column.setPreferredWidth(260);
        }

        // set the data of table displays at middle of table
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) jTable.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(renderer.CENTER);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        jTable.setDefaultRenderer(Object.class, r);


        JScrollPane scroll = new JScrollPane(jTable);
        scroll.setBounds(0, 200, 800, 400);
        panel.add(scroll);

        returnButton = new JButton("Return");
        returnButton.setBounds(300, 600, 100, 50);
        returnButton.addActionListener(this);
        panel.add(returnButton);

        logoutButton = new JButton("Log Out");
        logoutButton.setBounds(400, 600, 100, 50);
        logoutButton.addActionListener(this);
        panel.add(logoutButton);

        frame.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submitButton){
            frame.dispose();
            try {
                frame.dispose();
                new BankTransactionListGUI(this.bankManager,transactionDateField.getText());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        else if (e.getSource()==returnButton){
            frame.dispose();
            new ManagerMainGUI(this.bankManager);
        }
        else if (e.getSource()==logoutButton){
            frame.dispose();
            new HomePage();
        }
    }
}
