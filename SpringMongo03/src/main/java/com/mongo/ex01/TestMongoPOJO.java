package com.mongo.ex01;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.UpdateResult;


public class TestMongoPOJO {
	String db = "mydb";
	String table="posts";
	MongoClient mcilent;
	MongoDatabase mdb;
	MongoCollection<PostVO> mcol;
	Scanner sc = new Scanner(System.in);
	
	public TestMongoPOJO() {
		this.mappingPojo();
	}
	
	//Bson문서를 Java Pojo객체에 매핑하는 메서드 ==> 코덱 레지스트리가 필요함
	private void mappingPojo() {
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
	
	public void insertOne() {
		mcol = mdb.getCollection(table, PostVO.class);
		PostVO vo = new PostVO(null,"kim","오늘도 수고 많으셨어여","2022-12-13");
		
		mcol.insertOne(vo);
		
		System.out.println(vo.getTitle()+" 글을 등록했습니다.");
	}
	
	public void insertMany() {
		mcol = mdb.getCollection(table,PostVO.class);
		List<PostVO> postsArr = Arrays.asList(
				new PostVO(null,"이창진","날씨가 춥다","2022-12-14"),
				new PostVO(null,"홍길동","눈 내렸다잇","2022-12-14"),
				new PostVO(null,"김철수","화이트 크리스마스","2022-12-14")
				);
		
		InsertManyResult res = mcol.insertMany(postsArr);
		int n =res.getInsertedIds().size();
		System.out.println(n+"개의 도큐먼트 삽입 완료");
		
	}
	
	public void findAll() {
		mcol = mdb.getCollection(table, PostVO.class);
		FindIterable<PostVO> postArr = mcol.find();
		MongoCursor<PostVO> mcr = postArr.iterator();
		while(mcr.hasNext()) {
			PostVO vo= mcr.next();
			System.out.println(vo);
		}
	}
	
	public void findByAuthor() {
		System.out.println("검색할 작성자명 입력: ");
		String author = sc.nextLine();
		mcol = mdb.getCollection(table, PostVO.class);
		
		//필터 객체를 전달해서 같은 조건의 도큐먼트를 가져오자.
		Bson query = Filters.eq("author", author);
		FindIterable<PostVO> postArr = mcol.find(query);
		for(PostVO vo : postArr) {
			System.out.println(vo);
		}
	}
	
	public void deleteOne() {
		System.out.println("삭제할 글의 작성자명 입력: ");
		String author = sc.nextLine();
		mcol = mdb.getCollection(table, PostVO.class);
		
		//DeleteResult res = mcol.deleteOne(Filters.eq("author",author));
		DeleteResult res = mcol.deleteMany(Filters.eq("author",author));
		System.out.println(res.getDeletedCount()+"개의 도큐먼트가 삭제됨");
	}
	
	public void updateOne() {
		System.out.println("수정할 글의 작성자명을 입력: ");
		String author = sc.nextLine();
		
		System.out.println("수정할 글 제목 입력: ");
		String title = sc.nextLine();
		
		Date date = new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String wdate = sdf.format(date);
		
		mcol = mdb.getCollection(table, PostVO.class);
		
		UpdateResult res = mcol.updateOne(Filters.eq("author",author), 
										Updates.combine(Updates.set("title", title),Updates.set("wdate",wdate)));
		System.out.println(res.getModifiedCount()+"개의 도큐먼트가 수정됨");
	}
	
	public void updateMany() {
		System.out.println("수정할 글의 작성자명을 입력: ");
		String author = sc.nextLine();
		
		System.out.println("수정할 글 제목 입력: ");
		String title = sc.nextLine();
		
		Date date = new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String wdate = sdf.format(date);
		
		mcol = mdb.getCollection(table, PostVO.class);
		
		UpdateResult res = mcol.updateMany(Filters.eq("author",author), 
				Updates.combine(Updates.set("title", title),Updates.set("wdate",wdate)));
		System.out.println(res.getModifiedCount()+"개의 도큐먼트가 수정됨");
	}
	
	public static void main(String[] args) {
		TestMongoPOJO app = new TestMongoPOJO();
		//app.insertMany();
		app.findAll();
		//app.deleteOne();
		//app.findByAuthor();
		app.updateOne();
		
	}
}
