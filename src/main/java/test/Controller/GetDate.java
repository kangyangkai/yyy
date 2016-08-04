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
			// 创建临时表语句
			StringBuffer createtable = new StringBuffer();
			createtable.append(
					"CREATE TABLE gls_temp ( BR_NO VARCHAR2(20), ACC_CODE VARCHAR2(8), CYC_TYPE VARCHAR2(4), CHG_DATE NUMBER(8), CUR_NO VARCHAR2(2), ACC_HRT VARCHAR2(6), TX_DATE NUMBER(8), DC_IND VARCHAR2(1), UP_ACC_HRT VARCHAR2(6), DR_BAL NUMBER(24,2), CR_BAL NUMBER(24,2), LDD_BAL NUMBER(24,2), LCD_BAL NUMBER(24,2), RDD_CNT NUMBER(10), RCD_CNT NUMBER(10), RDD_AMT NUMBER(24,2), RCD_AMT NUMBER(24,2), CDD_CNT NUMBER(10), CCD_CNT NUMBER(10), CDD_AMT NUMBER(24,2), CCD_AMT NUMBER(24,2), TDDR_BAL NUMBER(24,2), TDCR_BAL NUMBER(24,2), TDDR_CNT NUMBER(10), TDCR_CNT NUMBER(10), TDDR_AMT NUMBER(24,2), TDCR_AMT NUMBER(24,2), MDR_BAL NUMBER(24,2), MCR_BAL NUMBER(24,2), MDR_CNT NUMBER(10), MCR_CNT NUMBER(10), MDR_AMT NUMBER(24,2), MCR_AMT NUMBER(24,2), QDR_BAL NUMBER(24,2), QCR_BAL NUMBER(24,2), QDR_CNT NUMBER(10), QCR_CNT NUMBER(10), QDR_AMT NUMBER(24,2), QCR_AMT NUMBER(24,2), YDR_BAL NUMBER(24,2), YCR_BAL NUMBER(24,2), YDR_CNT NUMBER(10), YCR_CNT NUMBER(10), YDR_AMT NUMBER(24,2), YCR_AMT NUMBER(24,2), CYC_NO NUMBER(8) ); ");

			insertdata();

			// 向临时表中插入查询出的新增的所有数据
//			StringBuffer sql = new StringBuffer();
//			sql.append("insert into  gls_temp ");
//			sql.append(
//					"select c.opn_br_no,  c.acc_hrt,  c.cur_no, c.acc_code ,c.dc_ind1,c.up_acc_hrt,c.ROLL_IND,c.sumrdd_cnt,c.sumrcd_cnt,c.sumrdd_amt,c.sumrcd_amt,");
//			sql.append("nvl(c.sumb+d.DR_BAL,0) sumborrow,");
//			sql.append("nvl(c.suml+d.CR_BAL,0) sumloan,");
//			sql.append(
//					"nvl(d.dr_bal,0) lastdr_bal,nvl(d.cr_bal,0) lastcr_bal,nvl(d.tddr_cnt+c.sumrdd_cnt,0) tddr_cnt,nvl(d.tdcr_cnt+c.sumrcd_cnt,0) tdcr_cnt,nvl(d.tddr_amt+c.sumrdd_amt,0) tddr_amt,nvl(d.tdcr_amt+c.sumrcd_amt,0) tdcr_amt,nvl(d.mdr_cnt+c.sumrdd_cnt,0) mdr_cnt1,nvl(d.mcr_cnt+c.sumrcd_cnt,0) mcr_cnt1,nvl(d.mdr_amt+c.sumrdd_amt,0) mdr_amt1,nvl(d.mcr_amt+c.sumrcd_amt,0) mcr_amt1,nvl(d.qdr_cnt+c.sumrdd_cnt,0) qdr_cnt,nvl(d.qcr_cnt+c.sumrcd_cnt,0) qcr_cnt,nvl(d.qdr_amt+c.sumrdd_amt,0) qdr_amt,nvl(d.qcr_amt+c.sumrcd_amt,0) qcr_amt,nvl(d.ydr_cnt+c.sumrdd_cnt,0) ydr_cnt,nvl(d.ycr_cnt+c.sumrcd_cnt,0) ycr_cnt,nvl(d.ydr_amt+c.sumrdd_amt,0) ydr_amt,nvl(d.ycr_amt+c.sumrcd_amt,0) ycr_amt from ");
//			sql.append(
//					"(select  a.opn_br_no,  a.acc_hrt,  a.cur_no, a.acc_code ,b.dc_ind as dc_ind1,b.up_acc_hrt,b.ROLL_IND,");
//			sql.append("count(case when a.dc_ind='1' then 1  end ) sumrdd_cnt,");
//			sql.append("count(case when a.dc_ind='2' then 1  end)  sumrcd_cnt,");
//			sql.append("nvl(sum(case when a.dc_ind='1' then amt end ) ,0) sumrdd_amt,");
//			sql.append("nvl(sum(case when a.dc_ind='2' then amt end ) ,0) sumrcd_amt,");
//			sql.append(
//					"case when b.dc_ind='1' then  -nvl(sum(case when a.dc_ind='2' then amt end ) ,0)+nvl(sum(case when a.dc_ind='1' then amt end ) ,0) end sumb,");
//			sql.append(
//					"case when b.dc_ind='2' then  -nvl(sum(case when a.dc_ind='1' then amt end ) ,0)+nvl(sum(case when a.dc_ind='2' then amt end ) ,0) end suml ");
//			sql.append("from  GLS_DC_LOG  a   ,GLS_COM_ITEM  b");
//			sql.append("where  a.acc_hrt=b.acc_hrt ");
//			sql.append("GROUP BY a.opn_br_no,  a.acc_hrt,  a.cur_no, a.acc_code ,b.dc_ind, b.up_acc_hrt,b.ROLL_IND ");
//			sql.append(
//					"ORDER BY opn_br_no , acc_hrt ASC) c left join GL_SUB d on(c.opn_br_no=d.br_no and c.acc_hrt=d.acc_hrt and c.cur_no=d.cur_no and c.acc_code=d.acc_code)order by c.opn_br_no,c.acc_hrt ");
//
//			// 判断gl_sub表中是否有该条数据记录，有则更新，无则插入
//			StringBuffer sqlin = new StringBuffer();
//			sqlin.append(
//					"merge into GLS_GL_SUB_KYK  a using GLS_TEMP b on(a.br_no=b.br_no and a.acc_hrt=b.acc_hrt and a.acc_code=b.acc_code and a.cur_no=b.cur_no) ");
//			sqlin.append("when matched then ");
//			sqlin.append(
//					"update set a.dr_bal=b.sumborrow,a.cr_bal=b.sumloan,a.ldd_bal=b.lastdr_bal,a.lcd_bal=b.lastcr_bal,a.rdd_amt=b.mdr_amt,a.rcd_amt=b.mcr_amt,a.rdd_cnt=b.mdr_cnt,a.rcd_cnt=b.mcr_cnt ");
//			sqlin.append("when not matched then ");
//			sqlin.append(
//					"insert(a.br_no,a.acc_code,a.cyc_type,a.cur_no,a.acc_hrt,a.tx_date,a.dc_ind,a.up_acc_hrt,a.dr_bal,a.cr_bal,a.ldd_bal,a.lcd_bal,a.rdd_amt,a.rcd_amt,a.rdd_cnt,a.rcd_cnt) values ");
//			sqlin.append(
//					"(b.br_no,b.acc_code,'0',b.cur_no,b.acc_hrt,'',b.dc_ind,b.up_acc_hrt,b.sumborrow, b.sumloan,b.lastdr_bal,b.lastcr_bal, b.mdr_amt, b.mcr_amt, b.mdr_cnt, b.mcr_cnt)");

			PublicUtil.query(createtable.toString());
//			PublicUtil.query(sql.toString());
//			PublicUtil.query(sqlin.toString());
			dellRoll_type();// 处理临时表轧差科目
			updategl_sub();// 处理旬，月，季，年数据。

			// pstmt.addBatch();
			// pstmt.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;

	}

	private void updategl_sub() {
		// TODO Auto-generated method stub
		StringBuffer updata=new StringBuffer();
		updata.append("merge into GLS_GL_SUB_KYK  a using GLS_TEMP b on(a.br_no=b.br_no and a.acc_hrt=b.acc_hrt and a.acc_code=b.acc_code and a.cur_no=b.cur_no)");
		updata.append("when matched then ");
		//if()
		updata.append("update set a.dr_bal=b.dr_bal,a.cr_bal=b.cr_bal,a.ldd_bal=b.ldd_bal,a.lcd_bal=b.lcd_bal,a.rdd_cnt=b.rdd_cnt,a.rcd_cnt=b.rcd_cnt,a.rdd_amt=b.rdd_amt,a.rcd_amt=b.rcd_amt,");
		updata.append("a.cdd_cnt=b.cdd_cnt,a.ccd_b.ccd_cnt,a.cdd_amt=b.cdd_amt,a.ccd_amt=b.ccd_amt,");
		updata.append("a.tddr_bal=b.tddr_bal,a.tdcr_bal=b.tdcr_bal,a.tddr_cnt=b.tddr_cnt,a.tdcr_cnt=b.tdcr_cnt,a.tddr_amt=b.tddr_amt,a.tdcr_amt=b.tdcr_amt,");
		updata.append("a.mdr_bal=b.mdr_bal,a.mcr_bal=b.mcr_bal,a.mdr_cnt=b.mdr_cnt,a.mcr_cnt=b.mcr_cnt,a.mdr_amt=b.mdr_amt,a.mcr_amt=b.mcr_amt,");
		updata.append("a.qdr_bal=b.qdr_bal,a.qcr_bal=b.qcr_bal,a.qdr_cnt=b.qdr_cnt,a.qcr_cnt=b.qcr_cnt,a.qdr_amt=b.qdr_amt,a.qcr_amt=b.qcr_amt,");
		updata.append("a.ydr_bal=b.ydr_bal,a.ycr_bal=b.ycr_bal,a.ydr_cnt=b.ydr_cnt,a.ycr_cnt=b.ycr_cnt,a.ydr_amt=b.ydr_amt,a.ycr_amt=b.ycr_amt,");
		updata.append("a.cyc_no=b.cyc_no ");
		updata.append("when not matched then ");
		updata.append("insert into GLS_GL_SUB_KYK select * from GLS_TEMP");
		
		
	}

	private void insertdata() throws SQLException {
		// TODO Auto-generated method stub
		long a=System.currentTimeMillis();
		Statement pstmt = null;
		Connection conn = PublicUtil.getConnection();
		pstmt=conn.createStatement();
		int i=0;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select c.opn_br_no, c.zz_tx_date,nvl(d.tx_date,0) chg_date, c.acc_hrt,  c.cur_no, c.acc_code ,c.dc_ind1,c.up_acc_hrt,c.ROLL_IND,c.sumrdd_cnt,c.sumrcd_cnt,c.sumrdd_amt,c.sumrcd_amt,nvl(d.tddr_bal,0) tddr_bal,nvl(d.tdcr_bal,0) tdcr_bal,nvl(d.mdr_bal,0) mdr_bal,nvl(d.mcr_bal,0) mcr_bal,nvl(d.qdr_bal,0) qdr_bal,nvl(d.qcr_bal,0) qcr_bal,nvl(d.ydr_bal,0) ydr_bal,nvl(d.ycr_bal,0) ycr_bal,");
		sql.append("nvl(c.sumb+d.DR_BAL,0) sumborrow,");
		sql.append("nvl(c.suml+d.CR_BAL,0) sumloan,");
		sql.append(
				"nvl(d.dr_bal,0) lastdr_bal,nvl(d.cr_bal,0) lastcr_bal,nvl(d.tddr_cnt+c.sumrdd_cnt,0) tddr_cnt,nvl(d.tdcr_cnt+c.sumrcd_cnt,0) tdcr_cnt,nvl(d.tddr_amt+c.sumrdd_amt,0) tddr_amt,nvl(d.tdcr_amt+c.sumrcd_amt,0) tdcr_amt,nvl(d.mdr_cnt+c.sumrdd_cnt,0) mdr_cnt1,nvl(d.mcr_cnt+c.sumrcd_cnt,0) mcr_cnt1,nvl(d.mdr_amt+c.sumrdd_amt,0) mdr_amt1,nvl(d.mcr_amt+c.sumrcd_amt,0) mcr_amt1,nvl(d.qdr_cnt+c.sumrdd_cnt,0) qdr_cnt,nvl(d.qcr_cnt+c.sumrcd_cnt,0) qcr_cnt,nvl(d.qdr_amt+c.sumrdd_amt,0) qdr_amt,nvl(d.qcr_amt+c.sumrcd_amt,0) qcr_amt,nvl(d.ydr_cnt+c.sumrdd_cnt,0) ydr_cnt,nvl(d.ycr_cnt+c.sumrcd_cnt,0) ycr_cnt,nvl(d.ydr_amt+c.sumrdd_amt,0) ydr_amt,nvl(d.ycr_amt+c.sumrcd_amt,0) ycr_amt from ");
		sql.append(
				"(select  a.opn_br_no,  a.acc_hrt,  a.cur_no, a.acc_code ,b.dc_ind as dc_ind1,b.up_acc_hrt,b.ROLL_IND,a.zz_tx_date,");
		sql.append("count(case when a.dc_ind='1' then 1  end ) sumrdd_cnt,");
		sql.append("count(case when a.dc_ind='2' then 1  end)  sumrcd_cnt,");
		sql.append("nvl(sum(case when a.dc_ind='1' then amt end ) ,0) sumrdd_amt,");
		sql.append("nvl(sum(case when a.dc_ind='2' then amt end ) ,0) sumrcd_amt,");
		sql.append(
				"case when b.dc_ind='1' then  -nvl(sum(case when a.dc_ind='2' then amt end ) ,0)+nvl(sum(case when a.dc_ind='1' then amt end ) ,0) end sumb,");
		sql.append(
				"case when b.dc_ind='2' then  -nvl(sum(case when a.dc_ind='1' then amt end ) ,0)+nvl(sum(case when a.dc_ind='2' then amt end ) ,0) end suml ");
		sql.append("from  GLS_DC_LOG  a   ,GLS_COM_ITEM  b ");
		sql.append("where  a.acc_hrt=b.acc_hrt ");
		sql.append("GROUP BY a.opn_br_no,  a.acc_hrt,  a.cur_no, a.acc_code ,b.dc_ind, b.up_acc_hrt,b.ROLL_IND,a.zz_tx_date ");
		sql.append(
				"ORDER BY opn_br_no , acc_hrt ASC) c left join GL_SUB d on(c.opn_br_no=d.br_no and c.acc_hrt=d.acc_hrt and c.cur_no=d.cur_no and c.acc_code=d.acc_code)order by c.opn_br_no,c.acc_hrt ");

		
		rs = PublicUtil.query(sql.toString());
		while (rs.next()) {
			i++;
			StringBuffer sqlupdate = new StringBuffer();
			sqlupdate.append("insert into GLS_TEMP (BR_NO, ACC_CODE, CYC_TYPE, CHG_DATE, CUR_NO, ACC_HRT, TX_DATE, DC_IND, UP_ACC_HRT, DR_BAL, CR_BAL, LDD_BAL, LCD_BAL, RDD_CNT, RCD_CNT, RDD_AMT, RCD_AMT, CDD_CNT, CCD_CNT, CDD_AMT, CCD_AMT, "
					+ "TDDR_BAL, TDCR_BAL, TDDR_CNT, TDCR_CNT, TDDR_AMT, TDCR_AMT, MDR_BAL, MCR_BAL, MDR_CNT, MCR_CNT, MDR_AMT, MCR_AMT, QDR_BAL, QCR_BAL, QDR_CNT,"
					+ " QCR_CNT, QDR_AMT, QCR_AMT, YDR_BAL, YCR_BAL, YDR_CNT, YCR_CNT, YDR_AMT, YCR_AMT, CYC_NO)  values (");
			sqlupdate.append("'"+rs.getString("opn_br_no")+"','"+rs.getString("acc_code")+"','0','"+rs.getString("chg_date")+"','"+rs.getString("cur_no")+"','"+rs.getString("acc_hrt")+"','"+rs.getString("zz_tx_date")+"','"+rs.getString("dc_ind1")+"','"+rs.getString("up_acc_hrt")+"',"+rs.getString("sumborrow")+","+rs.getString("sumloan")+","+rs.getString("lastdr_bal")+","+rs.getString("lastcr_bal")+",'"+rs.getString("sumrdd_cnt")+"','"+rs.getString("sumrcd_cnt")+"',"+rs.getString("sumrdd_amt")+","+rs.getString("sumrcd_amt")+",0,0,0,0,");
			String date = rs.getString("zz_tx_date");
			if (aaa.isFirstLast(date)) {
				sqlupdate.append(""+rs.getString("lastdr_bal")+","+rs.getString("lastcr_bal")+",0,0,0,0,");

			} else {

				sqlupdate.append(""+rs.getString("tddr_bal")+","+rs.getString("tdcr_bal")+","+rs.getString("tddr_cnt")+","+rs.getString("tdcr_cnt")+","+rs.getString("tddr_amt")+","+rs.getString("tdcr_amt")+",");

			}

			if (aaa.isFirstDayOfMonth(date)) {

				sqlupdate.append(""+rs.getString("lastdr_bal")+","+rs.getString("lastcr_bal")+",0,0,0,0,");

			} else {

				sqlupdate.append(""+rs.getString("mdr_bal")+","+rs.getString("mcr_bal")+","+rs.getString("mdr_cnt1")+","+rs.getString("mcr_cnt1")+","+rs.getString("mdr_amt1")+","+rs.getString("mcr_amt1")+",");


			}
			if (aaa.isFirstQuarter(date)) {

				sqlupdate.append(""+rs.getString("lastdr_bal")+","+rs.getString("lastcr_bal")+",0,0,0,0,");

			} else {
				sqlupdate.append(""+rs.getString("qdr_bal")+","+rs.getString("qcr_bal")+","+rs.getString("qdr_cnt")+","+rs.getString("qcr_cnt")+","+rs.getString("qdr_amt")+","+rs.getString("qcr_amt")+",");

			}
			if (aaa.isFirstYear(date)) {

				sqlupdate.append(""+rs.getString("lastdr_bal")+","+rs.getString("lastcr_bal")+",0,0,0,0,");

			} else {

				sqlupdate.append(""+rs.getString("ydr_bal")+","+rs.getString("ycr_bal")+","+rs.getString("ydr_cnt")+","+rs.getString("ycr_cnt")+","+rs.getString("ydr_amt")+","+rs.getString("ycr_amt")+",");

			}
			sqlupdate.append("0)");
				pstmt.addBatch(sqlupdate.toString());
				
				System.out.println(i);
				if(i%1000==0){
					pstmt.executeBatch();
					conn.commit();
					pstmt.clearBatch();
					
					
				}
				
			
				
			}
			
			pstmt.executeBatch();
			conn.commit();
			long b=System.currentTimeMillis();
			System.out.println(b-a);
	}

	// 处理轧差数据
	private void dellRoll_type() throws SQLException {
		long a = 0;
		long b = 0;
		a = System.currentTimeMillis();
		Statement pstmt = null;
		ResultSet rs = null;
		double sumborrow = 0.0;
		double sumloan = 0.0;
		double summon = 0.0;
		Connection conn = PublicUtil.getConnection();
		try {
			StringBuffer tempdate = new StringBuffer();
			tempdate.append("select * from  GLS_TEMP where  dc_ind='0'");
			rs = PublicUtil.query(tempdate.toString());
			pstmt = conn.createStatement();
			while (rs.next()) {
				System.out.println(rs.getString("br_no"));
				StringBuffer termsql = new StringBuffer();
				termsql.append(" where br_no='" + rs.getString("br_no") + "' and acc_hrt='" + rs.getString("acc_hrt")
						+ "' and cur_no='" + rs.getString("cur_no") + "' and acc_code='" + rs.getString("acc_code")
						+ "'");
				if (rs.getString("roll_ind").equals("Y")) {
					sumborrow = PublicUtil.sum(rs.getDouble("mdr_amt"), rs.getDouble("lastdr_bal"));
					sumloan = PublicUtil.sum(rs.getDouble("mcr_amt"), rs.getDouble("lastcr_bal"));
					summon = PublicUtil.sub(sumborrow, sumloan);
					if (summon > 0) {

						StringBuffer sql = new StringBuffer();
						sql.append("update GLS_TEMP set sumborrow='" + summon + "'");
						sql.append(termsql);
						pstmt.addBatch(sql.toString());

					} else {

						StringBuffer sql = new StringBuffer();
						sql.append("update GLS_TEMP set sumloan='" + Math.abs(sumloan) + "'");
						sql.append(termsql);

						pstmt.addBatch(sql.toString());
					}

				} else {

					StringBuffer sql = new StringBuffer();
					sql.append("update GLS_TEMP set sumborrow='" + rs.getDouble("mdr_amt") + "',sumloan='"
							+ rs.getDouble("mcr_amt") + "'");
					sql.append(termsql);
					pstmt.addBatch(sql.toString());
				}

			}
			pstmt.executeBatch();
			conn.commit();
			b = System.currentTimeMillis();
		} catch (Exception e) {

			e.printStackTrace();
		}
		System.out.println(b - a);
		System.out.println("end");

	}

	private void dellfirst_date() throws SQLException {
		long a = System.currentTimeMillis();
		int i = 0;
		Statement pstmt = null;
		Connection conn = PublicUtil.getConnection();
		pstmt = conn.createStatement();
		String date = aaa.getDate();
		String sql = "select * from  GLS_TEMP ";
		ResultSet rs = PublicUtil.query(sql);
		while (rs.next()) {
			i++;

			StringBuffer sqlwhere = new StringBuffer();
			sqlwhere.append(" where br_no='" + rs.getString("br_no") + "'and acc_hrt='" + rs.getString("acc_hrt")
					+ "'and acc_code='" + rs.getString("acc_code") + "'and cur_no='" + rs.getString("cur_no") + "'");
			StringBuffer sqlupdate = new StringBuffer();
			sqlupdate.append("update GLS_TEMP set ");
			if (aaa.isFirstLast(date)) {
				sqlupdate.append("tddr_bal='" + rs.getString("dr_bal") + "',tdcr_bal='" + rs.getString("cr_bal")
						+ "',tddr_cnt='0',tdcr_cnt='0',tddr_amt='0',tdcr_amt='0' ,");

			} else {

				sqlupdate.append("tddr_cnt='" + rs.getString("tddr_cnt") + "',tdcr_cnt='" + rs.getString("tdcr_cnt")
						+ "',tddr_amt='" + rs.getString("tddr_amt") + "',tdcr_amt='" + rs.getString("tdcr_amt") + "',");

			}

			if (aaa.isFirstDayOfMonth(date)) {

				sqlupdate.append("mdr_bal1='" + rs.getString("dr_bal") + "',mcr_bal1='" + rs.getString("cr_bal")
						+ "',mdr_cnt1='0',mcr_cnt1='0',mdr_amt1='0',mcr_amt1='0' ,");

			} else {

				sqlupdate.append("mdr_cnt1='" + rs.getString("mdr_cnt1") + "',mcr_cnt1='" + rs.getString("mcr_cnt1")
						+ "',mdr_amt1='" + rs.getString("mdr_amt1") + "',mcr_amt1='" + rs.getString("mcr_amt1") + "',");

			}
			if (aaa.isFirstQuarter(date)) {

				sqlupdate.append("qdr_bal='" + rs.getString("dr_bal") + "',qcr_bal='" + rs.getString("cr_bal")
						+ "',qdr_cnt='0',qcr_cnt='0',qdr_amt='0',qcr_amt='0' ,");

			} else {
				sqlupdate.append("qdr_cnt='" + rs.getString("qdr_cnt") + "',qcr_cnt='" + rs.getString("qcr_cnt")
						+ "',qdr_amt='" + rs.getString("qdr_amt") + "',qcr_amt='" + rs.getString("qcr_amt") + "',");

			}
			if (aaa.isFirstYear(date)) {

				sqlupdate.append("ydr_bal='" + rs.getString("dr_bal") + "',ycr_bal='" + rs.getString("cr_bal")
						+ "',ydr_cnt='0',ycr_cnt='0',ydr_amt='0',ycr_amt='0' ");

			} else {

				sqlupdate.append("ydr_cnt='" + rs.getString("ydr_cnt") + "',ycr_cnt='" + rs.getString("ycr_cnt")
						+ "',ydr_amt='" + rs.getString("ydr_amt") + "',ycr_amt='" + rs.getString("ycr_amt") + "'");

			}
			sqlupdate.append(sqlwhere);
			pstmt.addBatch(sqlupdate.toString());
			if (i % 1000 == 0) {
				System.out.println(i);
				pstmt.executeBatch();
				conn.commit();
				pstmt.clearBatch();
				i = 0;

			}

		}
		pstmt.executeBatch();
		conn.commit();
		long b = System.currentTimeMillis();
		System.out.println(b - a);
	}

}
