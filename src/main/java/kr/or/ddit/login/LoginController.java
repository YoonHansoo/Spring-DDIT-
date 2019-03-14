package kr.or.ddit.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;

@Controller
public class LoginController {
	@Resource(name="userService")
		private IUserService userService;
	/**
	 * Method : loginView
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @return
	 * Method 설명 : 로그인 화면 요청 처리
	 */
	//method 속성이 설정되어 있지 않으면 모든 method에 대해 처리
	@RequestMapping(path={"/login"}, method={RequestMethod.GET})
	public String loginView(){
		return "login/login";
	}
	
	
	//파라미터 pass와 userVo속성 pass가 바인딩됨
	@RequestMapping(path={"/login"}, method={RequestMethod.POST})
	public String loginProecess(UserVo userVo, HttpSession session){
	
		UserVo dbUservo = userService.selectUser(userVo.getUserId());
		

		//정상 로그인 한 여부
		if(dbUservo.getUserId().equals(userVo.getUserId()) && dbUservo.getPass().equals(KISA_SHA256.encrypt(userVo.getPass()))){
			
			session.setAttribute("userVo", dbUservo);
			return "main";
		}
		else
		return "login/login";
	}
	
	
}
