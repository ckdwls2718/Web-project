package com.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.mapper.BoardMapper;
import com.board.model.BoardVO;
import com.board.model.PagingVO;

@Service("boardServiceImpl")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public int insertBoard(BoardVO board) {
		return this.boardMapper.insertBoard(board);
	}

	@Override
	public List<BoardVO> selectBoardAll(Map<String, Integer> map) {		
		return this.boardMapper.selectBoardAll(map);
	}

	@Override
	public List<BoardVO> selectBoardAllPaging(PagingVO paging) {
		return this.boardMapper.selectBoardAllPaging(paging);
	}

	@Override
	public List<BoardVO> findBoard(PagingVO paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalCount() {
		return 0;
	}

	@Override
	public int getTotalCount(PagingVO paging) {
		return this.boardMapper.getTotalCount(paging);
	}

	@Override
	public BoardVO selectBoardByIdx(Integer num) {
		
		return this.boardMapper.selectBoardByIdx(num);
	}

	@Override
	public int updateReadnum(Integer num) {		
		return this.boardMapper.updateReadnum(num);
	}

	@Override
	public String selectPwd(Integer idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteBoard(Integer idx) {
		return this.boardMapper.deleteBoard(idx);
	}

	@Override
	public int updateBoard(BoardVO board) {
		return this.boardMapper.updateBoard(board);
	}

	@Override
	public int rewriteBoard(BoardVO board) {
		
		//[1] 부모글(원글)의 글번호로 부모글의 refer, lev, sunbun 가져오기
		BoardVO parent=this.selectRefLevSunbun(board.getNum());
		
		//[2] 기존에 달린 답변글들이 있다면 insert하기 전에 기존 답변글들의 sunbun을 하나씩 증가시키자.
		int n = this.updateSunbun(parent);
		
		//[3] 내가 쓴 답변글을 insert 한다
		board.setRefer(parent.getRefer());
		board.setLev(parent.getLev()+1);
		board.setSunbun(parent.getSunbun()+1);
		return this.boardMapper.rewriteBoard(board);
	}

	@Override
	public BoardVO selectRefLevSunbun(int idx) {
		return this.boardMapper.selectRefLevSunbun(idx);
	}

	@Override
	public int updateSunbun(BoardVO parent) {
		return this.boardMapper.updateSunbun(parent);
	}

}
