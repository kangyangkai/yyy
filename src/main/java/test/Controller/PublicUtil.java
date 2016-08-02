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
			String url = "jdbc:oracle:thin:@192.168.2.3:1521:ORCL";// ���ݿ����ӣ�oracle�������ӵ���oracle���ݿ⣻thin:@MyDbComputerNameOrIP����������ݿ����ڵ�IP��ַ�����Ա���thin:����1521�����������ݿ�Ķ˿ںţ�ORCL����������ݿ�����

			String UserName = "glsdb";// ���ݿ��û���½�� ( Ҳ��˵�� schema ���ֵ� )

			String Password = "glsdb";// ����

			conn = DriverManager.getConnection(url, UserName, Password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // ����oracle������������������������·��
		return conn;
	}
	
	//��ѯsql
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
	
//	���±����ݣ�������sql��
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
     * double ���
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
	     * double ���
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
