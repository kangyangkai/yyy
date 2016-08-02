package test.Controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PublicUtil {
	
	 static Connection conn = null;
	
	public static  Connection getConnection() throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.2.3:1521:ORCL";// 数据库连接，oracle代表链接的是oracle数据库；thin:@MyDbComputerNameOrIP代表的是数据库所在的IP地址（可以保留thin:）；1521代表链接数据库的端口号；ORCL代表的是数据库名称

			String UserName = "glsdb";// 数据库用户登陆名 ( 也有说是 schema 名字的 )

			String Password = "glsdb";// 密码

			conn = DriverManager.getConnection(url, UserName, Password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 加入oracle的驱动，“”里面是驱动的路径
		return conn;
	}
	
	//查询sql
	public static   ResultSet  query(String sql){
		ResultSet result=null;
		try {
			getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			result= ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  result ;
	}
	
//	跟新表数据（表名，sql）
//	public static ResultSet update(String tab,String  sql){
//		StringBuffer updatesql=new StringBuffer();
//		updatesql.append("update table "+tab+" set "+sql+"");
//		
//		ResultSet result=null;
//		try {
//			getConnection();
//			PreparedStatement ps=conn.prepareStatement(updatesql.toString());
//			result= ps.executeQuery();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return  result ;
//
//	}
	
	/**
     * double 相加
     * @param d1
     * @param d2
     * @return
     */
	 public static double sum(double d1,double d2){
	        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
	        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
	        return bd1.add(bd2).doubleValue();
	    }
	
	 /**
	     * double 相减
	     * @param d1
	     * @param d2
	     * @return
	     */
	    public static double sub(double d1,double d2){
	        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
	        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
	        return bd1.subtract(bd2).doubleValue();
	    }
	
	
	
	
	
}
