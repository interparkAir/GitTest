package com.spring.www.VO;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mypage2")
public class Mypaage2VO {


	private String PR_PNR1;
	private String PR_CU_ID;
	private String PR_PNR_LOC1;
	public String getPR_PNR1() {
		return PR_PNR1;
	}
	public void setPR_PNR1(String pR_PNR1) {
		PR_PNR1 = pR_PNR1;
	}
	public String getPR_CU_ID() {
		return PR_CU_ID;
	}
	public void setPR_CU_ID(String pR_CU_ID) {
		PR_CU_ID = pR_CU_ID;
	}
	public String getPR_PNR_LOC1() {
		return PR_PNR_LOC1;
	}
	public void setPR_PNR_LOC1(String pR_PNR_LOC1) {
		PR_PNR_LOC1 = pR_PNR_LOC1;
	}
	
	
}
