package user.model;

import java.sql.*;
import java.util.*;
import common.util.DBUtil;

public class UserDAO {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	public UserDAO() {
		System.out.println("UserDAO생성자()...");
	}

	public UserVO loginCheck(String userid, String pwd) throws SQLException, NotUserException {
		try {
			con = DBUtil.getCon();

			UserVO user = this.selectUserByUserid(userid);
			if (user == null)
				throw new NotUserException(userid + "란 아이디는 존재하지 않아요");

			// 비밀번호 일치여부를 체크
			String dbPwd = user.getPwd();
			if (!dbPwd.equals(pwd))
				throw new NotUserException("비밀번호가 일치하지 않아요");

			return user;
		} finally {
			close();
		}
	}

	public UserVO selectUserByIdx(int idx) throws SQLException {
		try {
			con = DBUtil.getCon();
			//String sql = new StringBuilder("select member.*, decode(status,0,'활동회원',-1,'정지회원',-2,'탈퇴회원') as statusStr ")
			//		.append("from member where idx=?").toString();
			String sql = "select * from memberView where idx=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, idx);

			rs = ps.executeQuery();
			return makeList(rs).get(0);
		} finally {
			close();
		}
	}

	/*
	  -- status값이 -1 보다 큰 회원들만 모아서 memberView를 생성하자 create view memberView as
	  select member.*, decode(status,0,'활동회원',-1,'정지회원',9,'관리자') statusStr from
	  member where status >= -1
	  select * from memberView; 
	 */

	public UserVO selectUserByUserid(String userid) throws SQLException {
		try {
			con = DBUtil.getCon();
//			String sql = new StringBuilder("select member.*, decode(status,0,'활동회원',-1,'정지회원',-2,'탈퇴회원') as statusStr ")
//					.append("from member where userid=?").toString();
			String sql = "select * from memberView where userid=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			List<UserVO> arr = makeList(rs);
			if (arr != null && arr.size() == 1) {
				UserVO user = arr.get(0);
				return user;
			}
			return null;
		} finally {
			close();
		}
	}

	public boolean idCheck(String userid) throws SQLException {
		try {
			con = DBUtil.getCon();
			String sql = new StringBuilder("select idx from member where userid= ?").toString();
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			return !rs.next(); // true면 아이디가 있다는 얘기 => false 반환 , false면 없다는 얘기 => true 반환
		} finally {
			close();
		}
	}

	public List<UserVO> makeList(ResultSet rs) throws SQLException {
		List<UserVO> arr = new ArrayList<>();
		while (rs.next()) {
			int idx = rs.getInt("idx");
			String name = rs.getString("name");
			String userid = rs.getString("userid");
			String pwd = rs.getString("pwd");
			String hp1 = rs.getString("hp1");
			String hp2 = rs.getString("hp2");
			String hp3 = rs.getString("hp3");
			String post = rs.getString("post");
			String addr1 = rs.getString("addr1");
			String addr2 = rs.getString("addr2");
			java.sql.Date indate = rs.getDate("indate");
			int mileage = rs.getInt("mileage");
			int status = rs.getInt("status");
			String statusStr = rs.getString("statusStr");

			arr.add(new UserVO(idx, name, userid, pwd, hp1, hp2, hp3, post, addr1, addr2, indate, mileage, status,
					statusStr));
		}
		return arr;
	}

	public List<UserVO> listUser() throws SQLException {
		try {
			con = DBUtil.getCon();
			String sql = new StringBuilder("select member.*, decode(status,0,'활동회원',-1,'정지회원',-2,'탈퇴회원') statusStr ")
					.append("from member order by idx desc").toString();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			return makeList(rs);
		} finally {
			close();
		}
	}

	public int insertUser(UserVO user) throws SQLException {
		try {
			con = DBUtil.getCon();
			StringBuilder sb = new StringBuilder("insert into member")
					.append(" values(member_seq.nextval,?,?,?,?,?,?,?,?,?,sysdate,1000,0)");
			String sql = sb.toString();

			ps = con.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getUserid());
			ps.setString(3, user.getPwd());
			ps.setString(4, user.getHp1());
			ps.setString(5, user.getHp2());
			ps.setString(6, user.getHp3());
			ps.setString(7, user.getPost());
			ps.setString(8, user.getAddr1());
			ps.setString(9, user.getAddr2());

			return ps.executeUpdate();

		} finally {
			close();
		}
	}

	public int updateUser(UserVO user) throws SQLException {
		try {
			con = DBUtil.getCon();
			String sql = new StringBuilder("update member set name=?, userid=?, pwd=?, hp1=?, hp2=?, hp3=?,")
					.append(" post=?, addr1=?, addr2=?, status=? where idx=?").toString();
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getUserid());
			ps.setString(3, user.getPwd());
			ps.setString(4, user.getHp1());
			ps.setString(5, user.getHp2());
			ps.setString(6, user.getHp3());
			ps.setString(7, user.getPost());
			ps.setString(8, user.getAddr1());
			ps.setString(9, user.getAddr2());
			ps.setInt(10, user.getStatus());
			ps.setInt(11, user.getIdx());

			return ps.executeUpdate();
		} finally {
			close();
		}
	}

	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {

		}
	}
}
