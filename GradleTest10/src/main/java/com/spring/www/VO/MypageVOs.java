package com.spring.www.VO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MypageVOs {

	@JsonProperty("mypage")
	private List<MypageVO> mypageList;

	public List<MypageVO> getMypageList() {
		return mypageList;
	}

	public void setMypageList(List<MypageVO> mypageList) {
		this.mypageList = mypageList;
	}
}
