package com.dao.common.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
    // 定义数据库连接的相关属性常量
    private final static String DRIVERCLASS = "oracle.jdbc.driver.OracleDriver";
    private final static String URL = "jdbc:oracle:thin:@133.37.94.203:1521:itsm";

    private final static String USERNAME = "itsm_report";
    private final static String PASSWORD = "report";

    public static Connection getConn() {
        try {
            Connection con = null;
            Class.forName(DRIVERCLASS);
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return con;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeAll(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
