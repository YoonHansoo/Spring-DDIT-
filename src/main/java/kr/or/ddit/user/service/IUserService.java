package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

public interface IUserService {

	/**
	 * Method : getAllUser
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 정보 조회
	 */
	public List<UserVo> getAllUser();
	
	public UserVo selectUser(String userId);
	
	
	Map<String,Object> selectUserPagingList(PageVo pageVo);
	
	/**
	 * Method : insertUser
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @param vo
	 * @return
	 * Method 설명 :사용자 등록
	 */
	int insertUser(UserVo vo);
	
	int deleteUser(String id);
	
	/**
	 * Method : updateUser
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @param vo
	 * @return
	 * Method 설명 :사용자 수정
	 */
	int updateUser(UserVo vo);
	
	/**
	 * Method : EncryptPass
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * Method 설명 : 모든 사용자의 비밀번호를 암호화
	 */
	void EncryptPass();
}
