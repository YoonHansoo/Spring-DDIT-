package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource(name="userDao")
	private IUserDao userDao;
	
	public UserServiceImpl() {
		
	}
	
	/**
	 * Method : getAllUser
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 정보 조회
	 */
	@Override
	public List<UserVo> getAllUser() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		List<UserVo> userList = userDao.gertAllUser(sqlSession);
		sqlSession.close();
		
		return userList;
	}

	@Override
	public UserVo selectUser(String userId) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		UserVo userVo = userDao.selectUser(sqlSession ,userId);
		sqlSession.close();
		
		return userVo;
	}

	@Override
	public Map<String,Object> selectUserPagingList(PageVo pageVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();

		
		Map<String,Object> resultMap = new HashMap<>();
		
		
		resultMap.put("userList",userDao.selectUserPagingList(sqlSession, pageVo));
		resultMap.put("userCnt",userDao.getUserCnt(sqlSession));
		
		sqlSession.close();
		return resultMap; 
	}

	@Override
	public int insertUser(UserVo vo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		int insertCnt = userDao.insertUser(sqlSession, vo);
		
		sqlSession.commit();
		sqlSession.close();
		
		return insertCnt;
	}

	@Override
	public int deleteUser(String id) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		int deleteCnt = userDao.deleteUser(sqlSession, id);
		
		sqlSession.commit();
		sqlSession.close();
		return deleteCnt;
	}

	@Override
	public int updateUser(UserVo vo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		int updateCnt = userDao.updateUser(sqlSession, vo);
		
		sqlSession.commit();
		sqlSession.close();
		return updateCnt;
	}

	@Override
	public void EncryptPass() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory .openSession();
		
		  List<UserVo> userList = userDao.gertAllUser(sqlSession);
		  
		  for(UserVo userVo : userList ) {
			  String pass = userVo.getPass();
			   String encrytpPass= KISA_SHA256.encrypt(pass);
			   userVo.setPass(encrytpPass);
				userDao.updatePassforEncrypt(sqlSession,userVo);
		  }
		  sqlSession.commit();
			sqlSession.close();
		  
	}


}
