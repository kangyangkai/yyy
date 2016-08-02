package test.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			//创建临时表语句
			StringBuffer createtable = new StringBuffer();
			createtable.append("CREATE TABLE gls_temp ( BR_NO VARCHAR2(20), acc_hrt VARCHAR2(8), cur_no VARCHAR2(4), acc_code varchar2(8), dc_ind varchar2(4),up_acc_hrt varchar2(12),roll_ind varchar2(4),mdr_cnt varchar2(20),mcr_cnt varchar2(20),mdr_amt number(20,2),mcr_amt number(20,2),sumborrow number(20,2),sumloan number(20,2))");
			
			
			//向临时表中插入查询出的新增的所有数据
			StringBuffer sql = new StringBuffer();
			sql.append("insert into  gls_temp ");
			sql.append("select c.opn_br_no,  c.acc_hrt,  c.cur_no, c.acc_code ,c.dc_ind1,c.up_acc_hrt,c.ROLL_IND,c.mdr_cnt,c.mcr_cnt,c.mdr_amt,c.mcr_amt,");
			sql.append("nvl(c.sumb+d.DR_BAL,0) sumborrow,");
			sql.append("nvl(c.suml+d.CR_BAL,0) sumloan,");
			sql.append("nvl(d.dr_bal,0) lastdr_bal,nvl(d.cr_bal,0) lastcr_bal from ");
			sql.append("(select  a.opn_br_no,  a.acc_hrt,  a.cur_no, a.acc_code ,b.dc_ind as dc_ind1,b.up_acc_hrt,b.ROLL_IND,");
			sql.append("count(case when a.dc_ind='1' then 1  end ) mdr_cnt,");
			sql.append("count(case when a.dc_ind='2' then 1  end)  mcr_cnt,");
			sql.append("nvl(sum(case when a.dc_ind='1' then amt end ) ,0) mdr_amt,");
			sql.append("nvl(sum(case when a.dc_ind='2' then amt end ) ,0) mcr_amt,");
			sql.append("case when b.dc_ind='1' then  -nvl(sum(case when a.dc_ind='2' then amt end ) ,0)+nvl(sum(case when a.dc_ind='1' then amt end ) ,0) end sumb,");
			sql.append("case when b.dc_ind='2' then  -nvl(sum(case when a.dc_ind='1' then amt end ) ,0)+nvl(sum(case when a.dc_ind='2' then amt end ) ,0) end suml ");
			sql.append("from  GLS_DC_LOG  a   ,GLS_COM_ITEM  b");
			sql.append("where  a.acc_hrt=b.acc_hrt ");
			sql.append("GROUP BY a.opn_br_no,  a.acc_hrt,  a.cur_no, a.acc_code ,b.dc_ind, b.up_acc_hrt,b.ROLL_IND ");
			sql.append("ORDER BY opn_br_no , acc_hrt ASC) c left join GL_SUB d on(c.opn_br_no=d.br_no and c.acc_hrt=d.acc_hrt and c.cur_no=d.cur_no and c.acc_code=d.acc_code)order by c.opn_br_no,c.acc_hrt ");
			
			
			
			//判断gl_sub表中是否有该条数据记录，有则更新，无则插入
			StringBuffer sqlin = new StringBuffer();
			sqlin.append("merge into GLS_GL_SUB_KYK  a using GLS_TEMP b on(a.br_no=b.br_no and a.acc_hrt=b.acc_hrt and a.acc_code=b.acc_code and a.cur_no=b.cur_no) ");
			sqlin.append("when matched then ");
			sqlin.append("update set a.dr_bal=b.sumborrow,a.cr_bal=b.sumloan,a.ldd_bal=b.lastdr_bal,a.lcd_bal=b.lastcr_bal,a.rdd_amt=b.mdr_amt,a.rcd_amt=b.mcr_amt,a.rdd_cnt=b.mdr_cnt,a.rcd_cnt=b.mcr_cnt ");
			sqlin.append("when not matched then ");
			sqlin.append("insert(a.br_no,a.acc_code,a.cyc_type,a.cur_no,a.acc_hrt,a.tx_date,a.dc_ind,a.up_acc_hrt,a.dr_bal,a.cr_bal,a.ldd_bal,a.lcd_bal,a.rdd_amt,a.rcd_amt,a.rdd_cnt,a.rcd_cnt) values ");
			sqlin.append("(b.br_no,b.acc_code,'0',b.cur_no,b.acc_hrt,'',b.dc_ind,b.up_acc_hrt,b.sumborrow, b.sumloan,b.lastdr_bal,b.lastcr_bal, b.mdr_amt, b.mcr_amt, b.mdr_cnt, b.mcr_cnt)");

			PublicUtil.query(createtable.toString());
			PublicUtil.query(sql.toString());
			PublicUtil.query(sqlin.toString());
			dellRoll_type();//处理临时表轧差科目
			dellfirst_date();//处理旬，月，季，年数据。

			// pstmt.addBatch();
			// pstmt.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;

	}

	// 处理轧差数据
	public void dellRoll_type() throws SQLException {
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
		
	
	public void dellfirst_date() throws SQLException{
		String date=aaa.getDate();
		Connection conn = PublicUtil.getConnection();
		
		
		
		
		
		
		
	}
		
		
		
//		while (rs.next()) {
//			int rdd_cnt = 0;
//			int rcd_cnt = 0;
//			;
//			StringBuffer exsql = new StringBuffer();
//			// String acc_dc_ind = rs.getString("dc_ind1");
//			// String roll_ind = rs.getString("roll_ind");
//			// String sqljie = " select sum(amt) amt ,count(*) num from
//			// GLS_DC_LOG where opn_br_no='" + rs.getString("opn_br_no")
//			// + "' and acc_hrt='" + rs.getString("acc_hrt") + "' and cur_no='"
//			// + rs.getString("cur_no")
//			// + "' and acc_code='" + rs.getString("acc_code") + "' and
//			// dc_ind='1' ";
//			// ResultSet rs1 = PublicUtil.query(sqljie);
//			// if (rs1.next()) {
//			sumborrow = rs.getDouble("mdr_amt");
//			rdd_cnt = rs.getInt("mdr_cnt");
//			// } else {
//			sumborrow = rs.getDouble("mcr_amt");
//			rdd_cnt = rs.getInt("mcr_cnt");
//			// }
//			// String sqldai = "select sum(amt) amt,count(*) num from GLS_DC_LOG
//			// where opn_br_no='" + rs.getString("opn_br_no")
//			// + "' and acc_hrt='" + rs.getString("acc_hrt") + "' and cur_no='"
//			// + rs.getString("cur_no")
//			// + "' and acc_code='" + rs.getString("acc_code") + "' and
//			// dc_ind='2' ";
//			// ResultSet rs2 = PublicUtil.query(sqldai);
//			// if (rs2.next()) {
//			// sumloan = rs2.getDouble(1);
//			// rcd_cnt=rs2.getInt(2);
//			// } else {
//			// sumloan = 0;
//			// rcd_cnt=0;
//			// }
//			double lastdr_bal = 0.0;
//			double lastcr_bal = 0.0;
//			int tddr_cnt = 0;
//			int tdcr_cnt = 0;
//			double tddr_amt = 0.0;
//			double tdcr_amt = 0.0;
//			double tddr_bal = 0.0;
//			double tdcr_bal = 0.0;
//
//			String lastdata = "select dr_bal,cr_bal,tddr_bal,tdcr_bal,tddr_cnt,tdcr_cnt,tddr_amt,tdcr_amt from  gl_sub where br_no='"
//					+ rs.getString("opn_br_no") + "' and  acc_hrt='" + rs.getString("acc_hrt") + "' and cur_no='"
//					+ rs.getString("cur_no") + "' and acc_code='" + rs.getString("acc_code") + "'";
//			// String lastdai = "select
//			// dr_bal,tddr_bal,tdcr_bal,tddr_cnt,tdcr_cnt,tddr_amt,tdcr_amt from
//			// gl_sub where br_no='"
//			// + rs.getString("opn_br_no") + "' and acc_hrt='" +
//			// rs.getString("acc_hrt") + "' and cur_no='"
//			// + rs.getString("cur_no") + "' and acc_code='" +
//			// rs.getString("acc_code") + "'";
//			ResultSet lastdatars = PublicUtil.query(lastdata);// 上次接
//			// ResultSet lastdairs = PublicUtil.query(lastdai);// 上次贷
//
//			if (lastdatars.next()) { // gl_sub有该部门，科目数据
//				exsql.append("update  gls_gl_sub_kyk  set ");
//				date = rs.getString("zz_tx_date");
//
//				System.out.println(date);
//				if (aaa.isFirstLast(date)) { // 当前日期是旬初
//					lastdr_bal = lastdatars.getDouble(1);
//					lastcr_bal = lastdatars.getDouble(2);
//					tddr_bal = lastdatars.getDouble(3);
//					tdcr_bal = lastdatars.getDouble(4);
//					tddr_cnt = lastdatars.getInt("tddr_cnt") + rdd_cnt;
//					tddr_amt = PublicUtil.sub(lastdatars.getDouble("tddr_amt"), sumborrow);
//
//					tdcr_cnt = lastdatars.getInt("tdcr_cnt") + rcd_cnt;
//					tdcr_amt = PublicUtil.sub(lastdatars.getDouble("tdcr_amt"), sumloan);
//
//					exsql.append("tddr_bal='" + lastdr_bal + "',tdcr_bal='" + lastcr_bal + "',tddr_cnt='" + 0
//							+ "',tdcr_cnt='" + 0 + "',tddr_amt='" + 0 + "',tdcr_amt='" + 0 + "',");
//
//				} else {// 当前日期不是旬初
//
//					exsql.append("tddr_cnt='" + tddr_cnt + "',tdcr_cnt='" + tdcr_cnt + "',tddr_amt='" + tddr_amt
//							+ "',tdcr_amt='" + tdcr_amt + "',");
//
//				}
//
//				if (acc_dc_ind.equals("1")) {
//					rdd_amt = PublicUtil.sub(sumborrow, sumloan);
//					exsql.append(
//							"dr_bal ='" + rdd_amt + "' ,ldd_bal='" + lastdr_bal + "',lcd_bal='" + lastcr_bal + "'");
//				} else if (acc_dc_ind.equals("2")) {
//					rcd_amt = PublicUtil.sub(sumloan, sumborrow);
//					exsql.append(
//							"cr_bal ='" + rcd_amt + "'  ,ldd_bal='" + lastdr_bal + "',lcd_bal='" + lastcr_bal + "'");
//				} else {
//					if (roll_ind.equals("Y")) {
//
//						double ajie = PublicUtil.sum(lastdr_bal, sumborrow);
//						double bdai = PublicUtil.sum(lastcr_bal, sumloan);
//						double c = PublicUtil.sub(ajie, bdai);
//
//						if (c >= 0) {
//							// 将c存入当前借方余额
//							exsql.append(
//									"dr_bal ='" + c + "',ldd_bal='" + lastdr_bal + "',lcd_bal='" + lastcr_bal + "'");
//						} else {
//							// 将c存入当前贷方余额
//
//							exsql.append(
//									"cr_bal ='" + c + "' ,ldd_bal='" + lastdr_bal + "',lcd_bal='" + lastcr_bal + "'");
//						}
//					} else {
//						// 将计算的当日借方发生额，当日贷方发生额更新至gl_sub
//
//						exsql.append("dr_bal ='" + sumborrow + "',cr_bal='" + sumloan + "' ,ldd_bal='" + lastdr_bal
//								+ "',lcd_bal='" + lastcr_bal + "' ");
//					}
//				}
//
//				exsql.append(" where br_no='" + rs.getString("opn_br_no") + "' and  acc_hrt='" + rs.getString("acc_hrt")
//						+ "' and cur_no='" + rs.getString("cur_no") + "' and acc_code='" + rs.getString("acc_code")
//						+ "'");
//				System.out.println(exsql);
//
//			} else { // gl_sub没有数据
//				exsql.append("insert into  gls_gl_sub_kyk(  ");
//
//				date = rs.getString("zz_tx_date");
//
//				System.out.println(date);
//
//			}
//
//			pstmt = conn.prepareStatement(exsql.toString());
//			pstmt.addBatch();
//
//			pstmt.executeBatch();
//		}

		// map.put("", value);

	}
	//


