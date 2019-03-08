package kr.or.ddit.ranger.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.ranger.model.RangerVo;

public interface IRangerDao {
	
	/**
	 * Method : getRangers
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 레인저스 조회
	 */
	List<String> getRangers();
	
	/**
	 * Method : getRanger
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @param index
	 * @return
	 * Method 설명 :listIndex에 해당하는 레인저 이름을 반환
	 */
	String getRanger(int listindex);

	
	/**
	 * Method : getRangerDb
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @return
	 * Method 설명 :db를 통한 레인저스 전체 조회
	 */
	List<Map<String, String>> getRangerDb();
	
	/**
	 * Method : getRanger
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @param id
	 * @return
	 * Method 설명 :레인져 아이디로 레인져 정보 조회
	 */
	Map<String, String> getRanger(String id);
	
	/**
	 * Method : insertRanger
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @param map
	 * @return
	 * Method 설명 : 신규 레인져 등록  
	 */
	int insertRanger(Map<String, String> map);
	
	/**
	 * Method : deleteRanger
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @return
	 * Method 설명 : 레인져 삭제
	 */    
	int deleteRanger(String id);
	
	/**
	 * Method : DeleteRangerDept
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @param id
	 * @return
	 * Method 설명 : 레인저 소속 삭제
	 */ 
	
	int DeleteRangerDept(String id);
}
