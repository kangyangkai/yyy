package test.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.spi.DirStateFactory.Result;

public class GetDate {
	static double sumborrow = 0.0;
	static double sumloan = 0.0;

	static double rdd_amt = 0.0;
	static double rcd_amt = 0.0;

	// static StringBuffer exsql;

	// 获得确定的插入的数据
	public ResultSet getAllDate() throws SQLException {

		ResultSet rs = null;
		try {
			String sql = "select  a.opn_br_no,  a.acc_hrt,  a.cur_no, a.acc_code ,b.dc_ind as dc_ind1, count(case when a.dc_ind='1' then 1  end ) mdr_cnt, count(case when a.dc_ind='2' then 1 end)  mcr_dnt,nvl(sum(case when a.dc_ind='1' then amt end ) ,0) mdr_amt,nvl(sum(case when a.dc_ind='2' then amt end ),0) mcr_amt, case when b.dc_ind='1' then  -nvl(sum(case when a.dc_ind='2' then amt end ),0)+nvl(sum(case when a.dc_ind='1' then amt end ) ,0) end sumborrow,case when b.dc_ind='2' then  -nvl(sum(case when a.dc_ind='1' then amt end ) ,0)+nvl(sum(case when a.dc_ind='2' then amt end ),0)end sumloan from  GLS_DC_LOG  a   ,GLS_COM_ITEM  b where  a.acc_hrt=b.acc_hrt  GROUP BY a.opn_br_no,  a.acc_hrt,  a.cur_no, a.acc_code ,b.dc_ind ORDER BY opn_br_no , acc_hrt ASC";
			rs = PublicUtil.query(sql);
			getAmt(rs);

			// pstmt.addBatch();
			// pstmt.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();

		}

		return rs;

	}

	// 获得借方，贷方当前余额
	public void getAmt(ResultSet rs) throws SQLException {
		PreparedStatement pstmt = null;
		Connection conn = PublicUtil.getConnection();
		String date = null;
		double amt = 0.0;
		HashMap<String, Object> map = new HashMap<String, Object>();
		while (rs.next()) {
			int rdd_cnt=0;
			int rcd_cnt=0;;
			StringBuffer exsql = new StringBuffer();
//			String acc_dc_ind = rs.getString("dc_ind1");
//			String roll_ind = rs.getString("roll_ind");
//			String sqljie = " select sum(amt) amt ,count(*) num from  GLS_DC_LOG where opn_br_no='" + rs.getString("opn_br_no")
//					+ "' and  acc_hrt='" + rs.getString("acc_hrt") + "' and cur_no='" + rs.getString("cur_no")
//					+ "' and acc_code='" + rs.getString("acc_code") + "' and dc_ind='1' ";
//			ResultSet rs1 = PublicUtil.query(sqljie);
//			if (rs1.next()) {
				sumborrow = rs.getDouble("mdr_amt");
				rdd_cnt=rs.getInt("mdr_cnt");
//			} else {
				sumborrow = rs.getDouble("mcr_amt");
				rdd_cnt=rs.getInt("mcr_cnt");
//			}
//			String sqldai = "select  sum(amt) amt,count(*) num  from  GLS_DC_LOG where opn_br_no='" + rs.getString("opn_br_no")
//					+ "' and  acc_hrt='" + rs.getString("acc_hrt") + "' and cur_no='" + rs.getString("cur_no")
//					+ "' and acc_code='" + rs.getString("acc_code") + "' and dc_ind='2'  ";
//			ResultSet rs2 = PublicUtil.query(sqldai);
//			if (rs2.next()) {
//				sumloan = rs2.getDouble(1);
//				rcd_cnt=rs2.getInt(2);
//			} else {
//				sumloan = 0;
//				rcd_cnt=0;
//			}
			double lastdr_bal = 0.0;
			double lastcr_bal = 0.0;
			int tddr_cnt = 0;
			int tdcr_cnt = 0;
			double tddr_amt = 0.0;
			double tdcr_amt = 0.0;
			double tddr_bal = 0.0;
			double tdcr_bal = 0.0;

			String lastdata = "select dr_bal,cr_bal,tddr_bal,tdcr_bal,tddr_cnt,tdcr_cnt,tddr_amt,tdcr_amt from  gl_sub where br_no='"
					+ rs.getString("opn_br_no") + "' and  acc_hrt='" + rs.getString("acc_hrt") + "' and cur_no='"
					+ rs.getString("cur_no") + "' and acc_code='" + rs.getString("acc_code") + "'";
//			String lastdai = "select dr_bal,tddr_bal,tdcr_bal,tddr_cnt,tdcr_cnt,tddr_amt,tdcr_amt from  gl_sub where br_no='"
//					+ rs.getString("opn_br_no") + "' and  acc_hrt='" + rs.getString("acc_hrt") + "' and cur_no='"
//					+ rs.getString("cur_no") + "' and acc_code='" + rs.getString("acc_code") + "'";
			ResultSet lastdatars = PublicUtil.query(lastdata);// 上次接
//			ResultSet lastdairs = PublicUtil.query(lastdai);// 上次贷
			
			if(lastdatars.next()) {		//gl_sub有该部门，科目数据
				exsql.append("update  gls_gl_sub_kyk  set ");
				date = rs.getString("zz_tx_date");
				
				System.out.println(date);
				if (aaa.isFirstLast(date)) {			//当前日期是旬初
					lastdr_bal = lastdatars.getDouble(1);
					lastcr_bal = lastdatars.getDouble(2);
					tddr_bal=lastdatars.getDouble(3);
					tdcr_bal=lastdatars.getDouble(4);
					tddr_cnt = lastdatars.getInt("tddr_cnt") + rdd_cnt;
					tddr_amt=PublicUtil.sub(lastdatars.getDouble("tddr_amt"), sumborrow);
					
					tdcr_cnt = lastdatars.getInt("tdcr_cnt") + rcd_cnt;
					tdcr_amt = PublicUtil.sub(lastdatars.getDouble("tdcr_amt"), sumloan);
					
					exsql.append("tddr_bal='" + lastdr_bal + "',tdcr_bal='" + lastcr_bal + "',tddr_cnt='" + 0
							+ "',tdcr_cnt='" + 0 + "',tddr_amt='" + 0 + "',tdcr_amt='" + 0 + "',");
					
					

				}else{//当前日期不是旬初
					
					
					exsql.append("tddr_cnt='" + tddr_cnt+ "',tdcr_cnt='" + tdcr_cnt + "',tddr_amt='" + tddr_amt + "',tdcr_amt='" + tdcr_amt+"',");
					
					
					
				}
				
				if (acc_dc_ind.equals("1")) {
					rdd_amt = PublicUtil.sub(sumborrow, sumloan);
					exsql.append("dr_bal ='" + rdd_amt + "' ,ldd_bal='" + lastdr_bal + "',lcd_bal='" + lastcr_bal + "'");
				} else if (acc_dc_ind.equals("2")) {
					rcd_amt = PublicUtil.sub(sumloan, sumborrow);
					exsql.append("cr_bal ='" + rcd_amt + "'  ,ldd_bal='" + lastdr_bal + "',lcd_bal='" + lastcr_bal + "'");
				} else {
					if (roll_ind.equals("Y")) {

						double ajie = PublicUtil.sum(lastdr_bal, sumborrow);
						double bdai = PublicUtil.sum(lastcr_bal, sumloan);
						double c = PublicUtil.sub(ajie, bdai);

						if (c >= 0) {
							// 将c存入当前借方余额
							exsql.append("dr_bal ='" + c + "',ldd_bal='" + lastdr_bal + "',lcd_bal='" + lastcr_bal + "'");
						} else {
							// 将c存入当前贷方余额

							exsql.append("cr_bal ='" + c + "' ,ldd_bal='" + lastdr_bal + "',lcd_bal='" + lastcr_bal + "'");
						}
					} else {
						// 将计算的当日借方发生额，当日贷方发生额更新至gl_sub

						exsql.append("dr_bal ='" + sumborrow + "',cr_bal='" + sumloan + "' ,ldd_bal='" + lastdr_bal
								+ "',lcd_bal='" + lastcr_bal + "' ");
					}
				}
				
				exsql.append(" where br_no='" + rs.getString("opn_br_no") + "' and  acc_hrt='" + rs.getString("acc_hrt")+ "' and cur_no='" + rs.getString("cur_no") + "' and acc_code='" + rs.getString("acc_code") + "'");
				System.out.println(exsql);
				
			}else{  //gl_sub没有数据
				exsql.append("insert into  gls_gl_sub_kyk(  ");
				
				date = rs.getString("zz_tx_date");
				
				System.out.println(date);
				
				
				
				
				
			}

			
			

			pstmt = conn.prepareStatement(exsql.toString());
			pstmt.addBatch();
			
			pstmt.executeBatch();
		}

		// map.put("", value);
		

	}
	//

}
