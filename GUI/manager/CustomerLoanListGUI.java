package GUI.manager;

<<<<<<< HEAD
import GUI.customer.LoanListGUI;
import Utils.GUIUtils;
import coding.ATM;
import coding.BankManager;
import coding.customer.Customer;
import coding.loan.Loan;
=======
import Utils.GUIUtils;
import Backend.manager.BankManager;
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
public class CustomerLoanListGUI extends JFrame implements ActionListener {

    private BankManager bankManager;
    private JFrame frame;
    private JButton returnButton;

    JButton buyButton = new JButton("Return Loan");

    private JTable jTable;

    private DefaultTableModel model;


    public CustomerLoanListGUI(BankManager bankManager) throws SQLException {
        this.bankManager = bankManager;
        frame = GUIUtils.initializeFrameTemplate();
        placeComponents();
        frame.setVisible(true);
    }

    private  void placeComponents() throws SQLException {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Loans");
        titleLabel.setBounds(270,50,300,50);
        titleLabel.setFont(new Font("Dialog", 1, 20));
        panel.add(titleLabel);

        List<Loan> customerLoan = bankManager.getLoanService().getAllLoans();
        Object[] columnTitle = {"Loan ID","Collateral Name","Price","Date"};
        Object[][] tableData = new Object[customerLoan.size()][5];
        for (int i=0;i<customerLoan.size();i++){
            tableData[i] = new Object[]{customerLoan.get(i).getId(),customerLoan.get(i).getCollateral().getName(),customerLoan.get(i).getCollateral().getPrice(),
                    customerLoan.get(i).getDate()};
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
        returnButton.setBounds(3000,650,100,50);
        returnButton.addActionListener(this);
        panel.add(returnButton);

        frame.add(panel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == returnButton){
            frame.dispose();
            new ManagerMainGUI(this.bankManager);
        }
    }
}