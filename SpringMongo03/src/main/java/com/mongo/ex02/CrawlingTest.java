package com.mongo.ex02;
import java.io.IOException;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class CrawlingTest {
	public static void main(String[] args) throws IOException {
		String url = "https://www.melon.com/chart/index.htm";
		//python=> BeautifulSoup, Java=>Jsoup
		Document doc = Jsoup.connect(url).get();
		//System.out.println(doc);
		Elements divEle = doc.select("div.service_list_song");
		
		//앨범 이미지의 url주소 추출해서 출력해보세요
		Elements imgEle = divEle.select(".wrap img");
		/*
		 * for(Element img : imgEle) { System.out.println(img.attr("src")); }
		 */
		
		//System.out.println(divEle);
		//Elements songEle = divEle.select("div.wrap_song_info");
		Elements songEle = divEle.select("tr>td:nth-child(6) div.wrap_song_info");
		//System.out.println(songEle);
		
		int k = 0;
		for(int i=0;i<songEle.size();i++) {
			Element songInfo = songEle.get(i);
			
			//앨범 이미지 추출
			String imgSrc = imgEle.get(i).attr("src");
			
			
			//곡 이름 추출
			String songTitle = songInfo.select("div.ellipsis.rank01 a").text();
			
			//가수 이름 추출
			String singer = songInfo.select("div.ellipsis.rank02>a").text();
			
			System.out.println((i+1)+": \t"+songTitle+"\t"+singer+"\t"+imgSrc);
		}
		
		
		
	}
}
