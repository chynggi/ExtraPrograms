package mymarket.sample.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import mymarket.common.dao.AbstractDAO;

@Repository("sampleDAO")
public class SampleDAO extends AbstractDAO {

	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) {
		return (List<Map<String, Object>>) selectList("sample.selectBoardList", map);
	}

	public void insertBoard(Map<String, Object> map) {
		insert("sample.insertBoard", map);

	}

	public void updateHitCnt(Map<String, Object> map) throws Exception {
		update("sample.updateHitCnt", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("sample.selectBoardDetail", map);
	}

	public void updateBoard(Map<String, Object> map) throws Exception {
		update("sample.updateBoard", map);

		
	}

	public void deleteBoard(Map<String, Object> map) throws Exception{
		update("sample.deleteBoard", map);
		
	}

	public void insertFile(Map<String, Object> map) {
		insert("sample.insertFile", map);
		
	}

	public List<Map<String, Object>> selectFileList(Map<String, Object> map) {
		return (List<Map<String, Object>>)selectList("sample.selectFileList", map);
	}

	public void deleteFileList(Map<String, Object> map) {
		update("sample.deleteFileList", map);		
	}

	public void updateFile(Map<String, Object> map) {
		update("sample.updateFile", map);		
	}

}
