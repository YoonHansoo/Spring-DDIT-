package kr.or.ddit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//NoFileException 예외가 발생할 경우
//500 응답코드가 아닌 HttpStatus.NOT_FOUND(404)응답 코드로 생성한다.
@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class NoFileException extends Exception {
	
}