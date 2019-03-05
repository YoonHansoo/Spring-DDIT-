package kr.or.ddit.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256.SHA256_INFO;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.model.PageVo;

@RequestMapping("/user")
@Controller
public class UserController {
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	@Resource(name = "userService")
	private IUserService userService;

	/**
	 * Method : userAllList 작성자 : Hansoo 변경이력 :
	 * 
	 * @return Method 설명 :사용자 전체 리스트 조회
	 */
	@RequestMapping("/userAllList")
	public String userAllList(Model model) {

		List<UserVo> userList = userService.getAllUser();
		model.addAttribute("userList", userList);
		return "user/userAllList";

	}

	/**
	 * Method : userPagingList 작성자 : Hansoo 변경이력 :
	 * 
	 * @param page
	 * @param pageSize
	 * @param model
	 * @return Method 설명 :사용자 페이징 리스트 조회
	 */
	@RequestMapping("/userPagingList")
	public String userPagingList(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize, Model model) {

		PageVo pageVo = new PageVo(page, pageSize);
		Map<String, Object> resultMap = userService.selectUserPagingList(pageVo);
		int userCnt = (int) resultMap.get("userCnt");

		int lastPage = userCnt / pageSize + (userCnt % pageSize > 0 ? 1 : 0);

		model.addAllAttributes(resultMap); // Map 객체 안에 있는 속성들만 넘겨준다.
		// model.addAttribute("resultMap", resultMap);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("page", page);
		model.addAttribute("lastPage", lastPage);
		return "user/userPagingList";
	}

	@RequestMapping(path = "/user", method = RequestMethod.GET)
	public String user(@RequestParam("userId") String userId, Model model) {
		UserVo userVo = userService.selectUser(userId);
		model.addAttribute("userVo", userVo);

		return "user/user";
	}

	@RequestMapping("/profileImg")
	public void profileImg(HttpServletResponse resp, HttpServletRequest req, @RequestParam("userId") String userId)
			throws IOException {
		resp.setContentType("image");
		// resp.setContentType("application/octet-stream");

		// 2. 해당 사용자 아이디로 사용자 정보 조회(realFilename)
		UserVo userVo = userService.selectUser(userId);

		// 3-1 realFilename이 존재할 경우
		// 3-1-1. 해당 경로의 파일을 FileInputStream으로 읽는다.
		FileInputStream fis;
		if (userVo != null && userVo.getRealFilename() != null) { // 존재하지 않는 사용자
																	// 또는 이미지가
																	// 존재하지 않을 때
			fis = new FileInputStream(new File(userVo.getRealFilename()));
		}
		// 3-2.realFilename이 존재하지 않을 경우
		// 3-2-1. /upload/noimg.png (application.getRealPath())
		else {
			ServletContext application = req.getServletContext();
			String noimgPath = application.getRealPath("/upload/noimg.png");
			fis = new FileInputStream(new File(noimgPath));

		}

		// 4.FileInputStream을 response객체의 outputStream 객체에 write
		ServletOutputStream sos = resp.getOutputStream();
		byte[] buff = new byte[512];
		int len = 0;
		while ((len = fis.read(buff)) > -1) {
			sos.write(buff);

		}

		sos.flush();
		sos.close();
		fis.close();

	}

	/**
	 * Method : userForm 작성자 : Hansoo 변경이력 :
	 * 
	 * @return Method 설명 : 사용자 등록폼 요청
	 */
	@RequestMapping(path = "/userForm", method = RequestMethod.GET)
	public String userForm() {
		return "user/userForm";
	}

	@RequestMapping(path = "/userForm", method = RequestMethod.POST)
	public String userForm(UserVo userVo, @RequestPart("profile") MultipartFile profile, HttpSession session,
			Model model) throws IllegalStateException, IOException {

		UserVo duplicateUserVo = userService.selectUser(userVo.getUserId());

		// 중복체크 통과(신규등록)
		if (duplicateUserVo == null) {
			// 사용자 첨부파일을 업로드 한 경우
			if (profile.getSize() > 0) {
				String realFilename = "d:\\picture\\" + UUID.randomUUID().toString();
				String filename = profile.getOriginalFilename();
				profile.transferTo(new File(realFilename));
				userVo.setFilename(filename);
				userVo.setRealFilename(realFilename);
				
				//사용자 비밀번호 암호화 로직
				userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
				
			}
			int insertCnt = 0;
			try {
				insertCnt = userService.insertUser(userVo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 정상 입력(성공)
			if (insertCnt == 1) {
				session.setAttribute("msg", "정상 등록 되었습니다.");
				return "redirect:/user/userPagingList"; // contextpath 작업 필요
			} else {
				return "user/userForm";
			}

		}
		// 중복체크 실패
		else {
			model.addAttribute("msg", "중복체크에 실패 했습니다.");
			return "user/userForm";
		}
	}

	/**
	 * Method : userModifyForm 작성자 : Hansoo 변경이력 :
	 * 
	 * @param userId
	 * @param model
	 * @return Method 설명 : 유저 업데이트 페이지 조회
	 */
	@RequestMapping(path = "/userModifyForm", method = RequestMethod.GET)
	public String userModifyForm(@RequestParam(name = "userId") String userId, Model model) {
		UserVo userVo = userService.selectUser(userId);
		logger.debug("logger{}: ", userVo);
		model.addAttribute("userVo", userVo);
		return "user/userModify";
	}

	/**
	 * Method : userModifyForm
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @param profile
	 * @param userVo
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 * Method 설명 : 유저 업데이트 반영
	 */
	@RequestMapping(path="/userModifyForm", method=RequestMethod.POST)
	public String userModifyForm(@RequestPart("profile")MultipartFile profile
								   ,UserVo userVo, Model model
								   ,HttpServletRequest request
								   ,HttpServletResponse response
								   ,RedirectAttributes redirectAttributes) throws IllegalStateException, IOException{
		
		if(profile.getSize()>0){
			String filename=profile.getOriginalFilename();
			String realFilename = "d:\\picture\\"+UUID.randomUUID().toString();
			  userVo.setFilename(filename);
			  userVo.setRealFilename(realFilename);
			  
			  profile.transferTo(new File(realFilename));
		}
		
		//비밀번호 수정요청
		//사용자가 값을 입력하지 않은 경우 -> 기존 비밀번호를 유지
		if(userVo.getPass().equals("")){
			UserVo userVoForPass = userService.selectUser(userVo.getUserId());
			userVo.setPass(userVoForPass.getPass());
		}
		
		//사용자가 비밀번호를 신규 등록한 경우
		else{
			userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
		}
		
		int updateCnt = 0;
		try{
			updateCnt = userService.updateUser(userVo);
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//정상입력
		if(updateCnt==1){
			//redirect 파라미터를 보내는 방법
			//1.url로 작성
			//return "redirect:/user/user?userId="+userVo.getUserId();
			//2.model 객체의 속성 : 저장된 속성을 자동으로 파라미터 변환
			//model.addAttribute("userId",userVo.getUserId());
			//return "redirect:/user/user";
			//3.RedirectAttributes(ra) 객체를 이용
			//ra.addAttribute("userId",userVo.getUserId());
			//return "redirect:/user/user";
			
		//request.getSession().setAttribute("msg", "정상적으로 수정되었습니다.");
		redirectAttributes.addAttribute("userId", userVo.getUserId());
		//model.addAttribute("userId",userVo.getUserId()); 
		redirectAttributes.addFlashAttribute("msg", "정상적으로 수정되었습니다.");
		return "redirect:"+request.getContextPath()+"/user/user";
		}
		
		//입력실패
		else{
			UserVo userVo_dupl = userService.selectUser(userVo.getUserId());
			model.addAttribute("userVo", userVo_dupl);
			model.addAttribute("msg", "수정에 실패하였습니다.");
			return "/user/userModify";
		}
	}

}
