package kr.or.ddit.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

public interface IUserDao {
	
	/**
	 * Method : gertAllUser
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @return
	 * Method 설명 :전체 사용자 조회
	 */
	List<UserVo> gertAllUser(SqlSession sqlSession);
	
	
	/**
	 * Method : selectUser
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 :특정 사용자 조회
	 */
	 UserVo selectUser(SqlSession sqlSession, String userId);
	
	List<UserVo> selectUserPagingList(SqlSession sqlSession, PageVo pageVo);

	/**
	 * Method : getUserCnt
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 수 조회
	 */
	int getUserCnt(SqlSession sqlSession);
	
	int insertUser(SqlSession sqlSession, UserVo vo);
	
	/**
	 * Method : deleteUser
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 :유저 아이디 삭제
	 */
	int deleteUser(SqlSession sqlSession, String userId);
	
	/**
	 * Method : updateUser
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @param vo
	 * @return
	 * Method 설명 :사용자 수정
	 */
	int updateUser(SqlSession sqlSession, UserVo vo);
	
	int updatePassforEncrypt(SqlSession sqlSession, UserVo vo);
}
