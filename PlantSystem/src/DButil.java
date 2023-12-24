package comm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DButil {

    private PreparedStatement state;
    private String url;
    private String user;
    private String password;
    private String classdriver;

    public DButil(){
    	//考虑写个配置文件，调用本地的配置文件就可以不用修改这里
        url = "jdbc:sqlserver://localhost:1433;databaseName=plant;trustServerCertificate=true;autoReconnect=true;";
        user = "zy";
        password = "123456";
        classdriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    }

    /**
     * 连接数据库
     * @return
     * @throws Exception
     */
    public Connection getConnection() throws Exception {
        Class.forName(classdriver);
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println("连接成功");
        return conn;
    }
    /**
     * 关闭连接
     * @param conn
     * @throws SQLException
     */
    public void close(Connection conn) throws SQLException {
        if(state != null)state.close();
        conn.close();
        System.out.println("连接关闭");
    }

    /**
     * 执行 SQL 更新语句
     * @param sql 要执行的 SQL 语句
     * @param parelist SQL 语句中的参数列表
     * @param conn 数据库连接对象
     * @return 执行是否成功
     * @throws SQLException
     */
    public boolean excute(String sql, List<Object> parelist, Connection conn) throws SQLException {
        state = conn.prepareStatement(sql);
        for(int i = 0 ; i < parelist.size() ; i++){
            state.setObject(i+1, parelist.get(i));
        }
        int resnum = state.executeUpdate();

        if(resnum > 0) return true;
        else return false;
    }

    /**
     * 执行 SQL 查询语句，将结果封装成 List<Map<String, String>> 返回
     * @param sql SQL 查询语句
     * @param parelist 查询语句中的参数列表
     * @param conn 数据库连接对象
     * @return 包含查询结果的列表
     * @throws SQLException
     */
    public List<Map<String, String>> excutequery(String sql, List<Object> parelist, Connection conn) throws SQLException {
        List<Map<String, String>> list = new ArrayList<>();  // 用于存储查询结果的列表
        state = conn.prepareStatement(sql);    // 预编译 SQL 语句
        if(parelist != null){
            for(int i = 0 ; i < parelist.size() ; i++){
                state.setObject(i+1, parelist.get(i));   // 设置参数值
            }
        }
        ResultSet set = state.executeQuery();  // 执行查询语句
        ResultSetMetaData meta = state.getMetaData(); // 获取结果集元数据信息
        while(set.next()){
            Map<String, String> map = new HashMap<>();
            for(int i = 1 ; i <= meta.getColumnCount(); i++ ){
                String key = meta.getColumnName(i);
                map.put(key, set.getString(key));
            }
            list.add(map);
        }
        System.out.println("查询完成");
        return list;
    }
    
    
}