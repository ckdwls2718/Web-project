package com.mongo.ex02;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertManyResult;
public class CrawlingTest2 {
	
	String db = "mydb";
	String table="melonChart_";
	MongoClient mcilent;
	MongoDatabase mdb;
	MongoCollection<MelonVO> mcol;

	
	public static void main(String[] args) throws IOException {
		String url = "https://www.melon.com/chart/index.htm";
		Document doc = Jsoup.connect(url).get();
		Elements divEle = doc.select("div.service_list_song");
		
		Elements imgEle = divEle.select(".wrap img");
		
		Elements songEle = divEle.select("tr>td:nth-child(6) div.wrap_song_info");
		
		List<MelonVO> arr = new ArrayList<>();
		for(int i=0;i<songEle.size();i++) {
			Element songInfo = songEle.get(i);
			
			//앨범 이미지 추출
			String imgSrc = imgEle.get(i).attr("src");
			
			//곡 이름 추출
			String songTitle = songInfo.select("div.ellipsis.rank01 a").text();
			
			//가수 이름 추출
			String singer = songInfo.select("div.ellipsis.rank02>a").text();
			
			//앨범이미지를 다운로드 해보자
			saveAlbumImg((i+1), imgSrc);
			
			arr.add(new MelonVO((i+1),songTitle,singer,imgSrc));
		}
		
		//몽고디비 컬렉션 이름 "melonChart_년월일"
		//new CrawlingTest2().saveMongodb(arr);

	}
	public static synchronized void saveAlbumImg(int num, String imgUrl) {
		try {
			URL url = new URL(imgUrl);
			InputStream is = url.openStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			
			File dir = new File("c:/javadev/crawling/melon_"+getToday());
			if(!dir.exists()) dir.mkdirs();
			
			File target = new File(dir, num+"album.jpg");
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(target));
			
			byte[] data = new byte[1024];
			int n=0;
			while((n=bis.read(data))!=-1) {
				bos.write(data,0,n);
			}
			bos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getToday() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}
	
	public void saveMongodb(List<MelonVO> arr) {
		this.mappingPojo();
		
		table+=getToday();
		
		mcol = mdb.getCollection(table,MelonVO.class);
		
		InsertManyResult res = mcol.insertMany(arr);
		System.out.println(res.getInsertedIds().size()+"개의 도큐먼트 삽입");
	}
	
	public void mappingPojo() {
		ConnectionString conStr = new ConnectionString("mongodb://localhost:27017");
		CodecRegistry pojoCodec = fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodec);
		//몽고디비에서 가져온 Bson데이터를 자바POJO로 인코딩, 디코딩하도록 설정하는 작업
		MongoClientSettings clientSettings = MongoClientSettings.builder()
																.applyConnectionString(conStr)
																.codecRegistry(codecRegistry).build();
		
		mcilent= MongoClients.create(clientSettings);
		mdb = mcilent.getDatabase(db);
	}
}
