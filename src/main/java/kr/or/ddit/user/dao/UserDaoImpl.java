package kr.or.ddit.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

@Repository("userDao")
public class UserDaoImpl implements IUserDao {

	/**
	 * Method : gertAllUser 작성자 : Hansoo 변경이력 :
	 * 
	 * @return Method 설명 :전체 사용자 조회
	 */
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlsessionTemplate;

	public List<UserVo> gertAllUser() {
		List<UserVo> userList = sqlsessionTemplate.selectList("user.getAllUser"); // Ibatis에서
																					// queryForList와
																					// 동일함
		return userList;
	}

	public UserVo selectUser(String userId) {

		UserVo userVo = sqlsessionTemplate.selectOne("user.selectUser", userId); // Ibatis에서
																			// queryForObject와
																			// 비슷함
		return userVo;
	}

	/**
	 * Method : selectUserPagingList 작성자 : Hansoo 변경이력 :
	 * 
	 * @param pageVo
	 * @return Method 설명 :사용자 페이징 리스트 조회
	 */
	@Override
	public List<UserVo> selectUserPagingList(PageVo pageVo) {
		List<UserVo> list = sqlsessionTemplate.selectList("user.selectUserPagingList", pageVo); // Ibatis에서
																						// queryForObject와
																						// 비슷함
		return list;
	}

	/**
	 * Method : getUserCnt 작성자 : Hansoo 변경이력 :
	 * 
	 * @return Method 설명 : 전체 사용자 수 조회
	 */
	@Override
	public int getUserCnt() {
		int userCnt = sqlsessionTemplate.selectOne("user.getUserCnt");
		return userCnt;
	}

	@Override
	public int insertUser(UserVo vo) {

		int insertCnt = sqlsessionTemplate.insert("user.insertUser", vo);

		return insertCnt;
	}

	/**
	 * Method : deleteUser 작성자 : Hansoo 변경이력 :
	 * 
	 * @param userId
	 * @return Method 설명 : 사용자 삭제
	 */
	@Override
	public int deleteUser(String userId) {
		int deleteCnt = sqlsessionTemplate.delete("user.deleteUser", userId);
		return deleteCnt;
	}

	@Override
	public int updateUser(UserVo vo) {
		int updateCnt = sqlsessionTemplate.update("user.updateUser", vo);
		return updateCnt;
	}

	@Override
	public int updatePassforEncrypt(UserVo vo) {
		int updateCnt = sqlsessionTemplate.update("user.updatePassforEncrypt", vo);
		return updateCnt;
	}
}
