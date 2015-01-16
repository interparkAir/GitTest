package com.spring.www.dao;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.spring.www.VO.Mypaage2VO;
import com.spring.www.VO.MypageVO;
import com.spring.www.VO.airDbVO;

public interface airDao {

	
	@SuppressWarnings("rawtypes")
	List<MypageVO> selectAirDb(String pnr);	
	
	@SuppressWarnings("rawtypes")
	List<Mypaage2VO> selectAirDb2(String pnr);	
	
}
