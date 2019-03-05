package kr.or.ddit.lprod.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.user.UserController;
import kr.or.ddit.util.model.PageVo;

@Controller
@RequestMapping("/lprod")
public class LprodAllListController {
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name="lprodService")
	private ILprodService lprodService;
	
	
	@RequestMapping(path="/lprodList",method=RequestMethod.GET)
	public String lprodList(@RequestParam(name="page", defaultValue="1") int page,
			 				@RequestParam(name="pageSize", defaultValue="10") int pageSize, Model model){
		
		PageVo pageVo = new PageVo(page, pageSize);
		
		Map<String, Object> map = lprodService.selectLprodPagingList(pageVo);
		
		List<LprodVo> lprodList = (List<LprodVo>) map.get("lprodList");
		int lprodCnt= (int) map.get("lprodCnt");

		//마지막 페이지 값을 구함
		int lastPage = (lprodCnt+(pageSize-1))/pageSize; 
		
		model.addAttribute("lprodList", lprodList);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("page", page);
		
		return "lprod/lprodList";
	}
	
	@RequestMapping(name="/lprodList",method=RequestMethod.POST)
	public String lprodList(){
		
		return "";
	}
	
	
}
