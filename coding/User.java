package coding;

import java.util.UUID;

/**
 * @Author: Jun Zhu
 * @Date: 12/1/2022 7:36 PM
 * @Description: TODO
 */
public class User {
    String name;
    Integer phoneNum;
    String password;
    String id;

    public User(String name, Integer phoneNum, String password, String id) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.password = password;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Integer phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
