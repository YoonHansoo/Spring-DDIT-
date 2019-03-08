package kr.or.ddit.mvc;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.ranger.model.RangerVo;
import kr.or.ddit.ranger.service.IRangerService;

@RequestMapping("/ajax")
@Controller
public class AjaxController {
	private Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
	@Resource(name="rangerService")
	private IRangerService rangerService;
	
	@ModelAttribute("rangerList")
	public List<String> getRanger(){
		return rangerService.getRangers();
	}
	
	
	//beanNameViewResolver
	//1.viewName : jsonView
	//2.ModelAndView("jsonView)
	
	@RequestMapping("/ajaxView")
	public String ajaxView(){
		return "mvc/ajaxView";
	}
	
	
	@RequestMapping("/jsonView")
	public String jsonView(){
		return "jsonView";
	}
	
	@RequestMapping("/modelAndView")
	public ModelAndView modelAndView(){
		return new ModelAndView("jsonView");
	}

	@RequestMapping(path="/responseBody", produces={"application/json"}) //json으로 보낸다.
	@ResponseBody
	public List<String> responseBody(){
		return rangerService.getRangers();
	}
	
	@RequestMapping(path="/requestBody", produces={"application/json"})  //RequestMapping에 있으므로 path가 /requestBody 이며 application/json일 경우에 이 메소드를 실행
	@ResponseBody
	//public List<String> requestBody(@RequestBody RangerVo rangerVo){ //json문자열로 받은 파라미터를 이 객체에 맵핑해라
	public RangerVo requestBody(@RequestBody RangerVo rangerVo){ //json문자열로 받은 파라미터를 이 객체에 맵핑해라
		
		logger.debug("=====rangerVo {}", rangerVo);
		
		rangerVo.setUserId(rangerVo.getUserId()+"_server");
		rangerVo.setBirth(new Date());
		return rangerVo;
	}
	
	
	
}
