package com.mongo.ex02;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MelonVO {
	
	private int ranking;//순위
	private String songTitle;//제목
	private String singer;//가수
	private String albumImg;//앨범이미지
}
