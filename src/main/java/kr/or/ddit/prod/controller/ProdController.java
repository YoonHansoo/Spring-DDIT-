package kr.or.ddit.prod.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.user.UserController;

@Controller
@RequestMapping("/prod")
public class ProdController {
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	@Resource(name="prodService")
	private IProdService prodService;
	    
	
	@RequestMapping(path="/prod", method=RequestMethod.GET)
	public String prod(@RequestParam(name="lprodGu")String lprod_gu, Model model){
	
		List<ProdVo> prodList;
		prodList = prodService.selectProdInfo(lprod_gu);		
		model.addAttribute("prodList", prodList);
		
		return "prod/prodList";
	}

}
