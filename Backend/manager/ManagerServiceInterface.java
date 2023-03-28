package Backend.manager;

import java.sql.SQLException;

/**
 * @Author: Jun Zhu
 * @Date: 12/14/2022 6:05 AM
 * @Description: TODO
 */
public interface ManagerServiceInterface {
    public BankManager getManagerData(String id, String password) throws SQLException ;
    public boolean registerManager(BankManager bankManager);
}
