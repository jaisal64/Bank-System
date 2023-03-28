package Backend.manager;

import java.sql.SQLException;

public class ManagerService implements ManagerServiceInterface {

    private ManagerDAO managerDAO;

    public ManagerService(){
        this.managerDAO = new ManagerDAO();
    }

    public BankManager getManagerData(String id, String password) throws SQLException {
        BankManager bankManager = managerDAO.findManagerByIdPassword(id, password);
        return bankManager;
    }

    public boolean registerManager(BankManager bankManager) {
        return managerDAO.createNewManager(bankManager);
    }

}
