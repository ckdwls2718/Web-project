package user.model;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class UserDAOMyBatis {
	
	private final String NS = "user.model.UserMapper";
	private String resource = "common/config/mybatis-config.xml";
	private SqlSession ses;
	
	public SqlSessionFactory getSessionFactory() {
		try {
			InputStream is = Resources.getResourceAsStream(resource);
			SqlSessionFactory fac = new SqlSessionFactoryBuilder().build(is);
			return fac;
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void close() {
		if(ses!= null) ses.close();
	}

	public int insertUser(UserVO vo) {
		try {
			ses = getSessionFactory().openSession(true);
			
			int n =ses.insert(NS+".insertUser",vo);
			return n;
		} finally {
			close();
		}
		
	}

	public boolean idCheck(String userid) {
		try {
			ses = getSessionFactory().openSession();
			Integer idx = ses.selectOne(NS+".idCheck", userid);
			
			if(idx==null) {
				return true;
			}
			return false;
		}finally {
			close();
		}
		
	}

	public UserVO loginCheck(String userid, String pwd) throws NotUserException {
		
		UserVO user = selectByUserid(userid);
		
		if(user==null) {
			throw new NotUserException(userid+"란 아이디는 없어요");
		}
		
		if(!user.getPwd().equals(pwd)) {
			throw new NotUserException("비밀번호가 틀렸습니다");
		}
		
		return user;
	}

	public UserVO selectByUserid(String userid) {
		try {
			ses = getSessionFactory().openSession(true);
			UserVO user=ses.selectOne(NS+".selectByUserid",userid);
			return user;
		} finally {
			close();
		}
	}
}
