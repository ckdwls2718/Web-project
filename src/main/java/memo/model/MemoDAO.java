package memo.model;
import java.sql.*;
import java.util.*;

import common.util.DBUtil;

public class MemoDAO {

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public int insertMemo(MemoVO memo) throws SQLException{
		try {
			con = DBUtil.getCon();
			
			//String : immutable (불변성) 원본은 변하지 않음. + (문자열이 결합되서 새로운 객체 생성)
			//StringBuilder, StringBuffer : 문자열을 메모리 버퍼에 넣고 수정,삭제,삽입 등의 편집작업 가능
			
//			String sql = "insert into memo(idx,name,msg,wdate)"
//					+ " values(memo_seq.nextval,?,?,sysdate)";
			
			StringBuilder sb = new StringBuilder("insert into memo(idx,name,msg,wdate) ")
					.append("values(memo_seq.nextval,?,?,sysdate)");
			
			String sql = sb.toString();
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, memo.getName());
			ps.setString(2, memo.getMsg());
			
			int n = ps.executeUpdate();
			
			return n;
		} finally {
			close();
		}
	}
	
	public void close() throws SQLException {
		if(rs != null) rs.close();
		if(ps != null) ps.close();
		if(con != null) con.close();
	}
	
	public List<MemoVO> selectMemoAll() throws SQLException{
		
		try {
			con = DBUtil.getCon();
			StringBuilder sb = new StringBuilder("select idx, rpad(name,12,' ') name, rpad(msg,100,' ') msg,")
					.append(" wdate from memo order by idx desc");
			String sql = sb.toString();
			
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			List<MemoVO> arr = makeList(rs);
			
			return arr;
			
		} finally {
			close();
		}
		
	}
	
	private List<MemoVO> makeList(ResultSet rs) throws SQLException{
		List<MemoVO> arr = new ArrayList<>();
		
		while(rs.next()) {
			int idx = rs.getInt("idx");
			String name = rs.getString("name");
			String msg = rs.getString("msg");
			java.sql.Date wdate = rs.getDate("wdate");
			
			MemoVO vo = new MemoVO(idx,name,msg,wdate);
			
			arr.add(vo);
		}
		
		return arr;
	}

	public int deleteMemo(int idx) throws SQLException {
		try {
			con = DBUtil.getCon();
			StringBuilder sb = new StringBuilder("delete from memo where idx = ?");
			String sql = sb.toString();
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, idx);
			
			int n = ps.executeUpdate();
			
			return n;
			
		}finally {
			close();
		}
		
	}

	public MemoVO selectMemo(int index) throws SQLException{
		try {
			con = DBUtil.getCon();
			String sql = "select idx, name, msg, wdate from memo where idx = ?";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, index);
			
			rs = ps.executeQuery();
			List<MemoVO> arr = makeList(rs);
			if(arr!=null && arr.size() ==1) {
				return arr.get(0);
			}
			return null;
		}finally {
			close();
		}
	}

	public int updateMemo(MemoVO vo)  throws SQLException{
		try {
			con = DBUtil.getCon();
			String sql = "update memo set name = ?, msg = ? where idx = ?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getMsg());
			ps.setInt(3, vo.getIdx());
			
			return ps.executeUpdate();
		} finally {
			close();
		}
	}

	public List<MemoVO> findMemo(int type, String keyword) throws SQLException {
		try {
			con = DBUtil.getCon();
			String sql = (type == 0) ? "select idx, name, msg, wdate from memo where name like ?" :
									   "select idx, name, msg, wdate from memo where msg like ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+keyword+"%");
			
			rs = ps.executeQuery();
			List<MemoVO> vo = makeList(rs);
			
			return vo;
		} finally {
			close();
		}
	}
	
}
