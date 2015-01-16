package com.spring.www.daoImpl;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.www.VO.Mypaage2VO;
import com.spring.www.VO.MypageVO;
import com.spring.www.VO.airDbVO;
import com.spring.www.dao.airDao;

@Repository("airInfoDao")
public class airDaoImpl implements airDao{

	
	@Autowired
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession){ 
		this.sqlSession = sqlSession; 
		} 
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<MypageVO> selectAirDb(String pnr) {
		List<MypageVO> airList = sqlSession.selectList("com.spring.www.dao.airDao.getAirInfo", pnr);
		return airList;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Mypaage2VO> selectAirDb2(String pnr) {
		// TODO Auto-generated method stub
		List<Mypaage2VO> airList = sqlSession.selectList("com.spring.www.dao.airDao.getAirInfo2", pnr);
		return airList;
	}

}
