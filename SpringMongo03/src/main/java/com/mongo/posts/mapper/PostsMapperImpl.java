package com.mongo.posts.mapper;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongo.posts.domain.PostVO;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

@Repository(value = "postsMapperImpl")
public class PostsMapperImpl implements PostsMapper {
	
	@Resource(name="mongoTemplate")
	private MongoTemplate mTemplate;
	
	@Override
	public int insertPosts(PostVO vo) {
		PostVO rvo = mTemplate.insert(vo, "posts");
		System.out.println(rvo);
		return (rvo != null) ? 1:0;
	}

	@Override
	public List<PostVO> listPosts() {
		return mTemplate.findAll(PostVO.class, "posts");
	}

	@Override
	public int deletePosts(String id) {
		DeleteResult res = mTemplate.remove(Query.query(Criteria.where("_id").is(id)), PostVO.class, "posts");
		return (int)res.getDeletedCount();
	}

	@Override
	public int updatePosts(PostVO vo) {
		
		Update uvo = new Update();
		uvo.set("author", vo.getAuthor());
		uvo.set("title", vo.getTitle());
		
		//Update updateVO = Update.update("author", vo.getAuthor()).update("title", vo.getTitle());
		
		UpdateResult res = mTemplate.updateFirst(query(where("_id").is(vo.getId())), uvo, PostVO.class, "posts");
		
		return (int)res.getModifiedCount(); 
	}
	
	@Override
	public PostVO selectPosts(String id) {
		return mTemplate.findOne(Query.query(Criteria.where("_id").is(id)), PostVO.class, "posts");
	}

}
