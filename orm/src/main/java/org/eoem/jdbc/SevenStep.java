package org.eoem.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SevenStep {
    public static List<Map<String,Object>> queryForList(){
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
        
        try {
            // 1 加载JDBC驱动
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            String url = "jdbc:oracle:thin:@localhost:1521:ORACLEDB";
            
            String user = "trainer";
            String password = "trainer";
            
            // 2 获取数据库连接
            connection = DriverManager.getConnection(url,user,password);
            
            String sql = "select * from table1 where user_id = ? ";
            // 3 创建sql语句对象（每一个Statement为一次数据库执行请求）
            stmt = connection.prepareStatement(sql);
            
            // 4 设置传入参数
            stmt.setString(1, "zhangsan");
            
            // 5 执行SQL语句
            rs = stmt.executeQuery();
            
            // 6 处理查询结果（将查询结果转换成List<Map>格式）
            ResultSetMetaData rsmd = rs.getMetaData();
            int num = rsmd.getColumnCount();
            
            while(rs.next()){
                Map map = new HashMap();
                for(int i = 0;i < num;i++){
                    String columnName = rsmd.getColumnName(i+1);
                    map.put(columnName,rs.getString(columnName));
                }
                resultList.add(map);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7 释放相关资源（关闭Connection，关闭Statement，关闭ResultSet）
            try {
                //关闭结果集
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                //关闭执行
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return resultList;
    }
}
