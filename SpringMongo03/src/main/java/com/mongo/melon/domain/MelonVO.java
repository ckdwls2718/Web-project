package com.mongo.melon.domain;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
//@Document(collection="melon_")
public class MelonVO {
	
	@Id
	private String id;
	
	private int ranking; //노래 순위
	
	private String songTitle; //제목
	
	private String singer;	//가수
	
	private String ctime; //수집된 시간 정보
	
	private String albumImage; // 앨범이미지
	
	//private int cntBySinger; //차트에 등록된 가수별 노래수
}
