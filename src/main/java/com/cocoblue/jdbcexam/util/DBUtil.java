package com.cocoblue.jdbcexam.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    public static Connection getConnection(){
        return getConnection("jdbc:mysql://(DB 주소)/(DB 명)", "DB 계정", "DB 비밀번호");
    }

    public static Connection getConnection(String dbURL, String dbId, String dbPassword){
       try {
            // 8.0.21 버전에 맞게 Class 구성
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbURL, dbId, dbPassword);

            return conn;
        } catch(Exception e) {
            throw new RuntimeException("Connection Error");
        }
    }
}
