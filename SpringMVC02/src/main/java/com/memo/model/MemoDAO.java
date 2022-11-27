package com.memo.model;

import java.util.List;

public interface MemoDAO {
	int insert(MemoVO memo);
	
	List<MemoVO> listMemo();
	
	MemoVO selectMemo(int idx);
	
	int deleteMemo(int idx);
	
	int updateMemo(MemoVO memo);
	
	int getTotalCount();
}
