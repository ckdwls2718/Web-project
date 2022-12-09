package com.board.model;

import lombok.Data;

/*
 * 페이징 처리 및 검색 기능을 모듈화하여
 * 재사용할 수 있도록 하자
 * 
 * */

@Data
public class PagingVO {
	// 페이징 처리 관련 프로퍼티
	private int cpage; // 현재 보여줄 페이지 번호
	private int pageSize = 5; // 한페이지당 보여줄 목록 갯수
	private int totalCount; // 총 게시글 수
	private int pageCount; // 페이지 수

	// DB에서 레코드를 끊어오기 위한 프로퍼티
	private int start;
	private int end;

	// 페이징 블럭 처리를 위한 프로퍼티
	private int pagingBlock = 5; // 한 블럭당 보여줄 페이지 수
	private int prevBlock; // 이전 5개
	private int nextBlock; // 이후 5개

	// 검색 관련 프로퍼티
	private String findType; // 검색 유형
	private String findKeyword; // 검색 키워드

	// 페이징 처리 연산을 수행하는 메서드
	public void init() {
		this.pageCount = (totalCount - 1) / pageSize + 1;
		if (cpage < 1) {
			cpage = 1;
		} else if (cpage > pageCount) {
			cpage = pageCount;
		}
		// [1] where 절 between을 사용할 경우
		// end = cpage*pageSize;
		// start = end-(pageSize-1);

		// [2] where절 rn> a and rn<b 를 사용할 경우
		start = (cpage - 1) * pageSize;
		end = start + pageSize + 1;
		
		//페이징 블럭 연산
		/*
		 * cpage
		 * [1][2][3][4][5] | [6][7][8][9][10] | [11][12][13][14][15] | [16][17][18][19][20]
		 * 
		 * cpage		pagingblock			prevBlock(이전 5개) 		nextBlock(이후 5개)
		 * 1~5				5					0							6
		 * 6~10									5							11
		 * 11~15 								10							16
		 * 
		 * */
		prevBlock = (cpage-1)/pagingBlock * pagingBlock;
		nextBlock = prevBlock+(pagingBlock+1);
	}
}
