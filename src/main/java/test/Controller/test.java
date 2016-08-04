package test.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test {
	public static void main(String[] args) throws SQLException {
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
}
//b=System.currentTimeMillis();
