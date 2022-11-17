package board.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BoardDAOMyBatis {
	
	private final String NS = "board.model.BoardMapper";
	private String resource = "common/config/mybatis-config.xml";
	private SqlSession ses;
	
	public SqlSessionFactory getSessionFactory() {
		try {
			InputStream is = Resources.getResourceAsStream(resource);
			SqlSessionFactory fac = new SqlSessionFactoryBuilder().build(is);
			return fac;
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public int getTotalCount(String type, String keyword) {
		
		Map<String,String> map = new HashMap<>();
		map.put("findType", type);
		map.put("findKeyword", keyword);
		
		ses = getSessionFactory().openSession();
		int count = ses.selectOne(NS+".totalCount", map);
		if(ses!=null) ses.close();
		return count;
	}

	public int insertBoard(BoardVO vo) {
		ses = getSessionFactory().openSession(); //디폴트 수동 커밋
		int n = ses.insert(NS+".insertBoard",vo);
		// 수동커밋일때 트랜잭션 지정방법
		if(n>0) {
			ses.commit();
		} else {
			ses.rollback();
		}
		if(ses!=null) ses.close();
		return n;
	}

	public List<BoardVO> listBoard(int start, int end, String type, String keyword) {
		ses = getSessionFactory().openSession();
		
		Map<String,String> map = new HashMap<>();
		map.put("start", String.valueOf(start));
		map.put("end", end+"");
		map.put("findType", type);
		map.put("findKeyword", keyword);
		
		List<BoardVO> arrBoardVO = ses.selectList(NS+".listBoard",map);
		
		if(ses!= null) ses.close();
		return arrBoardVO;
	}
	
	public BoardVO viewBoard(int num) {
		try {
			ses = getSessionFactory().openSession();
			BoardVO vo = ses.selectOne(NS+".viewBoard", num);
			return vo;
		} finally {
			close();
		}
	}
	
	public void close() {
		if(ses!= null) ses.close();
	}

	public int deleteBoard(int num) {
		try {
			//매개변수에 true를 넘기면 auto commit된다
			ses = getSessionFactory().openSession(true);
			int n = ses.delete(NS+".deleteBoard", num);
			return n;
		} finally {
			close();
		}
		
	}

	public int updateBoard(BoardVO vo) {
		try {
			ses = getSessionFactory().openSession(true);
			int n = ses.update(NS+".updateBoard", vo);
			return n;
		} finally {
			close();
		}
	}
}
