package GUI.manager;

import GUI.HomePage;
import Utils.GUIUtils;
<<<<<<< HEAD
import coding.BankManager;
import coding.customer.Customer;
=======
import Backend.manager.BankManager;
import Backend.customer.Customer;
>>>>>>> 63ed238 (update)

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class CustomerLookupGUI extends JFrame implements ActionListener {

    private BankManager bankManager;
    private JFrame frame;

    private JTextField customerIdField;

    private JButton submitButton;
    private JButton returnButton;
    private JButton logoutButton;

    public CustomerLookupGUI(BankManager bankManager) throws SQLException {
        this.bankManager = bankManager;
        frame = GUIUtils.initializeFrameTemplate();
        placeComponents();
        frame.setVisible(true);
    }

    private void placeComponents() throws SQLException {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel customerIdLabel = new JLabel("Customer Id:");
        customerIdLabel.setBounds(150,100,100,50);
        panel.add(customerIdLabel);

        customerIdField = new JTextField(20);
        customerIdField.setBounds(300,100,100,50);
        panel.add(customerIdField);

        submitButton = new JButton("Search");
        submitButton.setBounds(450,100,100,50);
        submitButton.addActionListener(this);
        panel.add(submitButton);

        Object[] columnTitle;
        Object[][] tableData;
        List<Customer> customerList = bankManager.getCustomerService().getAllCustomers();
        columnTitle = new Object[]{"ID", "Name", "Phone Number"};
        tableData = new Object[customerList.size()][3];
        for (int i = 0; i < customerList.size(); i++) {
            tableData[i] = new Object[]{customerList.get(i).getId(), customerList.get(i).getName(),
                    customerList.get(i).getPhoneNum()};
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
        returnButton.setBounds(300, 700, 100, 50);
        returnButton.addActionListener(this);
        panel.add(returnButton);

        logoutButton = new JButton("Log Out");
        logoutButton.setBounds(400, 700, 100, 50);
        logoutButton.addActionListener(this);
        panel.add(logoutButton);

        frame.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submitButton){
            try {
                Customer customer = bankManager.getCustomerService().findCustomerById(customerIdField.getText());
                if(customer!=null){
                    frame.dispose();
                    new CustomerProfileGUI(this.bankManager, customer);
                }
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
