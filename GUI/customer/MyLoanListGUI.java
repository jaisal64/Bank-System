package GUI.customer;

<<<<<<< HEAD
import coding.ATM;
import coding.currency.Currency;
import coding.customer.Customer;
import coding.loan.Loan;
=======
import Backend.ATM;
import Backend.customer.Customer;
import Backend.loan.Loan;
>>>>>>> 63ed238 (update)

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Jun Zhu
 * @Date: 12/12/2022 7:21 PM
 * @Description: TODO
 */
public class MyLoanListGUI extends JFrame implements ActionListener {

    private Customer currentCustomer;
    private JFrame frame;
    private JButton returnButton;

    JButton buyButton = new JButton("Return Loan");

    private JTable jTable;

    private DefaultTableModel model;


    public MyLoanListGUI(Customer customer) throws SQLException {
        this.currentCustomer = customer;
        frame = new JFrame("Bank System");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JLabel titleLabel = new JLabel("Your Loan History List");
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
        List<Loan> customerLoan = atm.getCustomerLoan(currentCustomer.getId());
        Object[] columnTitle = {"Loan ID","Collateral Name","Price","Date","Button"};
        Object[][] tableData = new Object[customerLoan.size()][5];
        for (int i=0;i<customerLoan.size();i++){
            tableData[i] = new Object[]{customerLoan.get(i).getId(),customerLoan.get(i).getCollateral().getName(),customerLoan.get(i).getCollateral().getPrice(),
                    customerLoan.get(i).getDate(),null};
        }
        model = new DefaultTableModel(tableData,columnTitle);
        jTable = new JTable(model);

        // set the size of height and column of table
        TableColumn column = null;
        jTable.setRowHeight(40);
        int columnCount = jTable.getColumnCount();
        for(int i = 0; i < columnCount; i++)
        {
            column = jTable.getColumnModel().getColumn(i);
            column.setPreferredWidth(195);
        }

        ActionPanelEditorRenderer er = new ActionPanelEditorRenderer();
        column = jTable.getColumnModel().getColumn(4);
        column.setCellRenderer(er);
        column.setCellEditor(er);


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

        returnButton = new JButton("Return");
        returnButton.setBounds(450,650,100,50);
        returnButton.addActionListener(this);
        panel.add(returnButton);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == returnButton){
            frame.dispose();
            try {
                new LoanListGUI(currentCustomer);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    class ActionPanelEditorRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {
        JPanel panel = new JPanel();

        public ActionPanelEditorRenderer() {
            super();
            panel.add(buyButton);
            buyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    // TODO Auto-generated method stub
                    ATM atm = new ATM();
                    int i = jTable.getSelectedRow();
                    int id = (int) model.getValueAt(i, 0);
                    double price = (double) model.getValueAt(i,2);
                    if (currentCustomer.getCheckingAccount().getBalance() < price){
                        JOptionPane.showMessageDialog(frame,"Your checking account does not have enough money to pay the loan!");
                        return;
                    }
                    try {
                        atm.returnLoan(currentCustomer,id,price);
                        JOptionPane.showMessageDialog(frame,"Return Successful!");
                        frame.dispose();
                        new MyLoanListGUI(currentCustomer);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                }
            });
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return panel;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return panel;
        }
    }
}