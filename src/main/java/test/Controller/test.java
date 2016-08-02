package test.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test {
	public static void main(String[] args) throws SQLException {
		long a =0;
		long b =0;
		a=System.currentTimeMillis();
		Statement pstmt = null;
		ResultSet rs=null;
		double sumborrow=0.0;
		double sumloan=0.0;
		double summon=0.0;
		Connection conn = PublicUtil.getConnection();
		try{
			StringBuffer tempdate=new StringBuffer();
			tempdate.append("select * from  GLS_TEMP where  dc_ind='0'");
			rs=PublicUtil.query(tempdate.toString());
			pstmt=conn.createStatement();
			while(rs.next()){
				System.out.println(rs.getString("br_no"));
				StringBuffer termsql=new StringBuffer();
				termsql.append(" where br_no='"+rs.getString("br_no")+"' and acc_hrt='"+rs.getString("acc_hrt")+"' and cur_no='"+rs.getString("cur_no")+"' and acc_code='"+rs.getString("acc_code")+"'");
				if(rs.getString("roll_ind").equals("Y")){
					sumborrow=PublicUtil.sum(rs.getDouble("mdr_amt"), rs.getDouble("lastdr_bal"));
					sumloan=PublicUtil.sum(rs.getDouble("mcr_amt"), rs.getDouble("lastcr_bal"));
					summon=PublicUtil.sub(sumborrow, sumloan);
					if(summon>0){
						
						StringBuffer sql=new StringBuffer();
						sql.append("update GLS_TEMP set sumborrow='"+summon+"'");
						sql.append(termsql);
						pstmt.addBatch(sql.toString());
						
					}else{
						
						StringBuffer sql=new StringBuffer();
						sql.append("update GLS_TEMP set sumloan='"+-summon+"'");
						sql.append(termsql);
						
						pstmt.addBatch(sql.toString());
					}
					
				}else{
					
					StringBuffer sql=new StringBuffer();
					sql.append("update GLS_TEMP set sumborrow='"+rs.getDouble("mdr_amt")+"',sumloan='"+rs.getDouble("mcr_amt")+"'");
					sql.append(termsql);
					pstmt.addBatch(sql.toString());
				}
				
				
			}
			pstmt.executeBatch();
			conn.commit();
			b=System.currentTimeMillis();
		}catch(Exception e){
			
			e.printStackTrace();
		}
		System.out.println(b-a);
		System.out.println("end");
	}



}
