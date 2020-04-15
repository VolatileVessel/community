package com.lgs.springboot.demo.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GS
 * @date 2020/3/26
 **/
public class Test01 {
    private final static Logger LOGGER = LoggerFactory.getLogger(DatabaseUtil.class);

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/cdb?useUnicode=true&characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static final String SQL = "SELECT a.user_id ,a.user_account ,b.create_time,b.`status`,b.role_id   FROM `user_info` a  ,`user_role_related` b where a.user_id=b.user_id ;";// 数据库操作

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("can not load jdbc driver", e);
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            LOGGER.error("get connection failure", e);
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure", e);
            }
        }
    }
    public static   List<Map>  getUserInfoList() {
        List<Map> result = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pstmt  = null;
        ResultSet rs =null;
        try {
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Map map = new HashMap();
                map.put("role_id",rs.getString("role_id"));
                map.put("create_time",rs.getString("create_time"));
                map.put("user_account",rs.getString("user_account"));
                map.put("fcalcdt","113");
                result.add(map);
            }
        } catch (SQLException e) {
            LOGGER.error("getColumnNames failure", e);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    LOGGER.error("getColumnNames close pstem and connection failure", e);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {

        System.out.println(getUserInfoList());


}
}
