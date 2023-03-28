package Backend.manager;

import Utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerDAO {

    private DBUtil db;

    public ManagerDAO(){
        db = new DBUtil();
    }

    public BankManager findManagerByIdPassword(String id, String password) throws SQLException {
        Object[] para = new Object[]{id,password};
        String sql = "select * from manager where id = ? and `password` = ?";
        ResultSet result = db.select(sql, para);
        if (result.next()){
            String name = result.getString("name");
            Integer phoneNum = result.getInt("phoneNum");
            return new BankManager(name,phoneNum,password,id);
        }
        return null;
    }

    public Boolean createNewManager(BankManager bankManager){
        Object[] para = new Object[]{bankManager.getName(),bankManager.getId(),bankManager.getPassword(),bankManager.getPhoneNum()};
        String sql = "insert into manager(name, id, password, phoneNum) values(?,?,?,?)";
        int update = db.update(sql, para);
        if (update==0){
            return false;
        }
        return true;
    }

}
