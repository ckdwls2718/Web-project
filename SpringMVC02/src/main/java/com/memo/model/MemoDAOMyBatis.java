package com.memo.model;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
//영속성 계층
@Repository
public class MemoDAOMyBatis implements MemoDAO {
	
	private final String NS="com.memo.model.MemoMapper";
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate session;

	@Override
	public int insert(MemoVO memo) {
		int n = session.insert(NS+".insertMemo",memo);
		return n;
	}

	@Override
	public List<MemoVO> listMemo() {
		return session.selectList(NS+".listMemo");
	}

	@Override
	public int deleteMemo(int idx) {
		return session.delete(NS+".deleteMemo", idx);
	}
	
	
	@Override
	public int updateMemo(MemoVO memo) {
		return session.update(NS+".updateMemo", memo);
	}

	@Override
	public int getTotalCount() {
		
		return session.selectOne(NS+".getTotalCount");
	}

	@Override
	public MemoVO selectMemo(int idx) {
		return session.selectOne(NS+".selectMemo", idx);
	}
	
	
}
