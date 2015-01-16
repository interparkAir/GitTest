package com.spring.www.controller;

import java.util.List;

import javax.annotation.Resource;








import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.www.VO.Mypaage2VO;
import com.spring.www.VO.MypageVO;
import com.spring.www.dao.airDao;

//@RequestMapping(value="mypage")
@Controller
public class HomeController {
	
	static final Logger log = LoggerFactory.getLogger(HomeController.class);



	@Resource(name="airInfoDao")
	private airDao airDao;
	
	@RequestMapping(value="/mypage/Test", method = RequestMethod.GET)
	public ModelAndView home(Model model){
		String name = "yns1020";
		System.out.println("여기까진 오나??");
		log.info("sadfasdf");
		log.info("airDao.selectAirDb()==>>"+airDao.selectAirDb("9015-2415"));
		ModelAndView view = new ModelAndView();
		view.addObject("airInfo", airDao.selectAirDb("9015-2415"));
		view.addObject("title", "테스트");
		view.setViewName("home");
		
		
		return view;
	}
	
	
	@RequestMapping(value="/main/Test", method = RequestMethod.GET, produces={"text/html","application/xml","application/json"} )
	public @ResponseBody ModelAndView homeXML(Model model){
		String name = "yns1020";
		System.out.println("여기까진 오나??");
		log.info("sadfasdf");
		log.info("airDao.selectAirDb()==>>"+airDao.selectAirDb("9015-2415"));
		
		List<MypageVO> mypage = airDao.selectAirDb("9015-2415");
		
		List<Mypaage2VO> mypage2 = airDao.selectAirDb2("9015-2415");
		
		
		ModelAndView view = new ModelAndView();
		view.addObject("mypage2", mypage2);
		view.addObject("mypage", airDao.selectAirDb("9015-2415"));
		view.addObject("title", "테스트");
		view.setViewName("Test");
		
		
		return view;
	}
}
