package kr.or.ddit.lprod.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

@Repository("lprodDao")
public class LprodDaoImpl implements ILprodDao {

	@Override
	public List<LprodVo> getAllLprod() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		
		SqlSession openSession = sqlSessionFactory.openSession();
		List<LprodVo> lprodList = openSession.selectList("lprod.getAllLprod");
		openSession.close();
		return lprodList;
	}

	@Override
	public int getLprodCnt() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		int  userCnt = openSession.selectOne("lprod.getLprodCnt");
		openSession.close();
		return userCnt;
	}

	@Override
	public List<LprodVo> selectLprodPagingList(PageVo pageVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		List<LprodVo>  list = openSession.selectList("lprod.selectLprodPagingList",pageVo);
		openSession.close();
		return list;
	}

	

}
