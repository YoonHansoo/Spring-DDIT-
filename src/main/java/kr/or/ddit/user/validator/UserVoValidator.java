package kr.or.ddit.user.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.user.model.UserVo;

public class UserVoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserVo.class.isAssignableFrom(clazz); //해당  Vo 객체에 할당하는 것이 가능한 지? 
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserVo userVo = (UserVo)target;
		
		//비밀번호가 8자리 이상이어야 한다.
		if(userVo.getPass().length()<8){
			errors.rejectValue("pass","passlen");//에러코드 설정
		}
		//사용자 아이디 검증(빈값이면 안된다)
	    if(userVo.getUserId().equals("")){
	        errors.rejectValue("userId", "required"); //required:은 error.properties와 이름이 같아야한다.
	    }
		//사용자 아이디는 6자리 이상이어야 합니다.
		if(userVo.getUserId().length()<6){
			errors.rejectValue("userId", "userIdLen");
		}
	}
}
