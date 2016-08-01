package test.Controller;

import java.sql.Connection;
import java.sql.SQLException;

public class test {
	static Connection con = null;

	
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		GetDate g = new GetDate() ;
		g.getAllDate();
		
//		GetData.getDepartmentList();
		
		
	}



}
