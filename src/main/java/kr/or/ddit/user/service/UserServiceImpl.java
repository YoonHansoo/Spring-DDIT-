package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource(name = "userDao")
	private IUserDao userDao;

	public UserServiceImpl() {

	}

	/**
	 * Method : getAllUser 작성자 : Hansoo 변경이력 :
	 * 
	 * @return Method 설명 : 전체 사용자 정보 조회
	 */
	@Override
	public List<UserVo> getAllUser() {

		List<UserVo> userList = userDao.gertAllUser();

		return userList;
	}

	@Override
	public UserVo selectUser(String userId) {

		UserVo userVo = userDao.selectUser(userId);

		return userVo;
	}

	@Override
	public Map<String, Object> selectUserPagingList(PageVo pageVo) {

		Map<String, Object> resultMap = new HashMap<>();

		resultMap.put("userList", userDao.selectUserPagingList(pageVo));
		resultMap.put("userCnt", userDao.getUserCnt());

		return resultMap;
	}

	@Override
	public int insertUser(UserVo vo) {

		int insertCnt = userDao.insertUser(vo);

		return insertCnt;
	}

	@Override
	public int deleteUser(String id) {

		int deleteCnt = userDao.deleteUser(id);

		return deleteCnt;
	}

	@Override
	public int updateUser(UserVo vo) {

		int updateCnt = userDao.updateUser(vo);

		return updateCnt;
	}

	@Override
	public void EncryptPass() {

		List<UserVo> userList = userDao.gertAllUser();

		for (UserVo userVo : userList) {
			String pass = userVo.getPass();
			String encrytpPass = KISA_SHA256.encrypt(pass);
			userVo.setPass(encrytpPass);
			userDao.updatePassforEncrypt(userVo);
		}

	}

}
