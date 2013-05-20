package com.test.dao;

import com.test.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Author:胡荆陵
 * Date: 12-12-15
 * User Dao 接口实现类
 */
public class UserDaoImpl implements UserDao {

    public void save(User user) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("org.h2.Driver");
            String url = "jdbc:h2:D:/h2db/jms-test01";
            String username = "sa";
            String password = null;
            connection = DriverManager.getConnection(url,username,password);
            String sql = "insert into tbl_user (name,password) values(?,?)";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,user.getName());
            pstmt.setString(2,user.getPassword());
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (pstmt != null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
