package Utils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @Author: Jun Zhu
 * @Date: 12/2/2022 12:38 AM
 * @Description: TODO
 */
public class DBUtil {
    //Connection Information
    private static String driverName;
    private static String url;
    private static String username;
    private static String password;

    //Register the driver, use a static block and register it only once
    static {
        //Initialize connection information
        Properties properties = new Properties();
<<<<<<< HEAD
        try {
            properties.load(new FileReader("src/db.properties"));
            driverName = properties.getProperty("driverName");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

        } catch (IOException e) {
            e.printStackTrace();
        }
=======


//        try {
//            properties.load(new FileReader("./db.properties"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        driverName = properties.getProperty("driverName");
//        url = properties.getProperty("url");
//        username = properties.getProperty("username");
//        password = properties.getProperty("password");
        driverName="com.mysql.cj.jdbc.Driver";
        url="jdbc:mysql://bank-system.ci4wghm6x78v.us-east-1.rds.amazonaws.com:3306/bank_system";
        username="admin";
        password="password";

>>>>>>> 63ed238 (update)
        //1、Register the driver
        try {
            //Register the driver by reflection
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //jdbc object
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    //obtain connection
    public void getConnection() {
        try {
            //2、Establishing a connection
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Update operation: add, delete, change
    public int update(String sql, Object[] objs) {
        int i = 0;
        try {
            getConnection();
            //3、Creating sql objects
            preparedStatement = connection.prepareStatement(sql);
            for (int j = 0; j < objs.length; j++) {
                preparedStatement.setObject(j + 1, objs[j]);
            }
            //4、Execute sql and return the number of rows changed
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    //Query operation
    public ResultSet select(String sql, Object[] objs) {
        try {
            getConnection();
            //3、Creating sql objects
            preparedStatement = connection.prepareStatement(sql);
            for (int j = 0; j < objs.length; j++) {
                preparedStatement.setObject(j + 1, objs[j]);
            }
            //4、Execute the sql and return the set from the query
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    //Disconnection
    public void closeConnection() {
        //5、Disconnection
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

