package com.test.h2;

import com.test.entity.User;
import org.junit.Test;

import java.sql.*;

/**
 * Author:胡荆陵
 * Date: 2013-5-20
 * 创建 h2 数据库的 user 表
 */
public class TestH2 {

    private static final String DDL_DROP = "drop table if exists tbl_user";
    private static final String DDL_CREATE = "create table tbl_user(id int auto_increment primary key, name varchar(255), password varchar(255))";
    private static final String DML_QUERY = "SELECT * FROM TBL_USER";

    @Test
    public void init(){
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("org.h2.Driver");
            String url = "jdbc:h2:D:/h2db/jms-test01";
            String username = "sa";
            String password = null;
            connection = DriverManager.getConnection(url, username, password);
            pstmt = connection.prepareStatement(DDL_DROP);
            pstmt.execute();
            pstmt = connection.prepareStatement(DDL_CREATE);
            pstmt.execute();
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

    @Test
    public void query(){
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            Class.forName("org.h2.Driver");
            String url = "jdbc:h2:D:/h2db/jms-test01";
            String username = "sa";
            String password = null;
            connection = DriverManager.getConnection(url, username, password);
            pstmt = connection.prepareStatement(DML_QUERY);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                String pass = rs.getString("password");
                User user = new User();
                user.setName(name);
                user.setPassword(pass);
                System.out.println(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
