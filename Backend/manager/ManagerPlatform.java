package Backend.manager;

import Utils.StaticData;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ManagerPlatform {

    private ManagerService managerService;

    public ManagerPlatform(){
        this.managerService = new ManagerService();
    }

    public Map<String,Object> login(String id, String password) throws SQLException {
        HashMap<String, Object> result = new HashMap<>();
        BankManager login = managerService.getManagerData(id, password);
        if (login!=null){
            result.put(StaticData.MANAGER,login);
        }else{
            result.put(StaticData.MESSAGE,StaticData.LOGIN_FAIL);
        }
        return result;
    }

}
