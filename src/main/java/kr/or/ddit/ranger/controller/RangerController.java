package kr.or.ddit.ranger.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.ranger.model.RangerVo;
import kr.or.ddit.ranger.service.IRangerService;

@SessionAttributes({"boardGb","boardGb2"}) //@ModelAttribute의 값을 세션에 저장해서 매번 실행하지 않게 한다.
@RequestMapping("/ranger")
@Controller
public class RangerController {
	
	private Logger logger = LoggerFactory.getLogger(RangerController.class);
		
	
	@Resource(name="rangerService")
	private IRangerService rangerService;
	
	//@ModelAttribute가 적용된 메소드는 
	//@RequestMapping이 적용된 메소드가 실행되기 전에 
	//먼저 실행되고 리턴하는 객체를 model객체의 속성으로 넣어준다.
	
	@ModelAttribute("boardGb")
	public List<String> boardGn(){
		logger.debug("boardGn");
		
		List<String> boardGbList = new ArrayList<>();
		boardGbList.add("공지사항");
		boardGbList.add("Q&A");
		boardGbList.add("경조사");
		boardGbList.add("영업사원게시판");
		
	
		return boardGbList;
	}
	
	@ModelAttribute("boardGb2")
	public List<String> boardGn2(){
		logger.debug("boardGn2");
		
		List<String> boardGbList = new ArrayList<>();
		boardGbList.add("공지사항");
		boardGbList.add("Q&A");
		boardGbList.add("경123123조사");
		boardGbList.add("영업사원게시판1231231231");
		
	
		return boardGbList;
	}
	/**
	 * Method : getRangers
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 레인저스 리스트를 조회
	 */
	//localhost/ranger/getRangers 라고 요청 시 밑의 메서드에서 요청을 처리
	@RequestMapping("/getRangers")
	public String getRangers(Model model) {
		
		List<String> rangers = rangerService.getRangers();
		
		//request.setAttribute("rangers", ranger); 기존 방식
		model.addAttribute("rangers", rangers);
		
		return "ranger/rangerList";
	}
	
	/**
	 * Method : getRangersMav
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @return
	 * Method 설명 :레인저스 전체 정보를 ModelAndView 객체를 통해 리턴
	 */
	@RequestMapping(path="/getRangersMav")
	public ModelAndView getRangersMav(){
		List<String> rangers = rangerService.getRangers();
		
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("rangers", rangers);
		//viewName
		mav.setViewName("ranger/rangerList");
		return mav;
	}
	
	
	/*
	 * //localhost/ranger/getRanger?listIndex=2  라고 요청 시 밑의 메서드에서 요청을 처리
		@RequestMapping("/getRanger")
		public String getRanger(HttpServletRequest req,Model model) {
			int listIndex = Integer.parseInt(req.getParameter("listIndex"));
			String ranger = rangerService.getRanger(listIndex);
			
			model.addAttribute("ranger", ranger);
			
			return "ranger/ranger";
		}
		*/
	
	//localhost/ranger/getRanger?listIndex=2  라고 요청 시 밑의 메서드에서 요청을 처리
	//vo객체에 파라미터 명과 동일한 이름 필드가 존재하면
	//파라미터를 해당 필드에 바인딩 시켜준다.  
	//또한 커맨드 객체(Vo객체)는 model에 속성을 자동으로 추가한다. -4번
	@RequestMapping("/getRanger")
	public String getRanger(@ModelAttribute("rVo")RangerVo rangerVo ,Model model) {
														
		String ranger = rangerService.getRanger(rangerVo.getListIndex());
		
		model.addAttribute("ranger", ranger);
		//model.addAllAttributes("rangerVo",rangerVo); 이것이 생략되어있음(4번)
		
		return "ranger/ranger";
	}
	
	
	//PathVariable : url의 일부 값을 변수로 치환해서  개발자가 해당 값을 사용하기 쉽게 받을 수 있는 @
	// localhost/ranger/pathVariable/{UserId}
	
	@RequestMapping(path="/pathVariable/{userId}")
	public String pathVariable(@PathVariable("userId")String userId){
		
		logger.debug("userId : {} ", userId);
		
		return "ranger/ranger";
	}
	
	
	//클라이언트가 요청시 보내는 header정보를 쉽게 사용할 수 있도록 값을 받을 수 있다.
	//localhost/ranger/requestHeader
	@RequestMapping(path="/requestHeader")
	public String requestHeader(@RequestHeader("Accept") String acceptHeader){
		logger.debug("acceptHeader : {}", acceptHeader);
		return "ranger/ranger";
	}

	@RequestMapping(path="/getRangerParam")
	public String getRangerparam(@RequestParam(name="listIndex", defaultValue="0") int listIndex, Model model){  //@RequestParam() name과 valuss는 같은거
		logger.debug("listIndex {}", listIndex);
		
		String ranger = rangerService.getRanger(listIndex);
		model.addAttribute("ranger", ranger);
		return "ranger/ranger";
	}
}
