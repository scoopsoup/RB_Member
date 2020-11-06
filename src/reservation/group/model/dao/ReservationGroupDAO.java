package reservation.group.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import common.JDBCTemplate;
import reservation.group.model.vo.GroupReservation;
import reservation.personal.model.vo.PersonalReservation;

public class ReservationGroupDAO {
	
	public ReservationGroupDAO() {
		
	}

	public int inputReservation(Connection conn, String userId, String date, String time, String place, String people) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO RES_GROUP VALUES(SEQ_RG_NO.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, date);
			pstmt.setString(2, place);
			pstmt.setString(3, time);
			pstmt.setString(4, "대기");
			pstmt.setString(5, people);
			pstmt.setString(6, userId);
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				JDBCTemplate.close(conn);
			}
			else {
				JDBCTemplate.rollback(conn);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public ArrayList<String> selectDisableDate(Connection conn){
		ArrayList<String> disableDate = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT RG_DATE FROM RES_GROUP WHERE RG_STATE IN ('승인', '대기') AND (RG_DATE > SYSDATE+7)";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			disableDate = new ArrayList<String>();
			while(rset.next()) {
				String date = rset.getString("RG_DATE");
				disableDate.add(date);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return disableDate;
	}
	
	public ArrayList<GroupReservation> groupSelectList(Connection conn, int currentPage, int recordCountPage, String userId){
		ArrayList<GroupReservation> gList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT RES_GROUP.*, ROW_NUMBER() OVER(ORDER BY RG_NO DESC) AS NUM FROM RES_GROUP WHERE USER_ID = ?)WHERE (NUM BETWEEN ? AND ?)";
		int start = currentPage * recordCountPage - (recordCountPage - 1);
		int end = currentPage * recordCountPage;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			gList = new ArrayList<GroupReservation>();
			while(rset.next()) {
				GroupReservation groupReservation = new GroupReservation();
				groupReservation.setRgNo(rset.getInt("RG_NO"));
				groupReservation.setRgDate(rset.getString("RG_DATE"));
				groupReservation.setRgPlace(rset.getString("RG_PLACE"));
				groupReservation.setRgTime(rset.getString("RG_TIME"));
				groupReservation.setRgState(rset.getString("RG_STATE"));
				groupReservation.setRgPeople(rset.getString("RG_PEOPLE"));
				groupReservation.setUserId(rset.getString("USER_ID"));
				gList.add(groupReservation);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return gList;
	}
	
	public String getGroupPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage, String userId) {
		int recordTotalCount = totalGroupCount(conn, userId);
		
		int pageTotalCount = 0;
		if(recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		}
		else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		
		if(currentPage < 1) {
			currentPage = 1;
		}
		else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
		
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(needPrev) {
			sb.append("<a href='/reservation/confirm?currentGroupPage="+(startNavi-1)+"&userId="+userId+"'> < </a>");
		}

		//1 ~ 10까지의 숫자를 for문을 이용해서 생성하고 만들어 주는 부분

		for(int i = startNavi; i <= endNavi; i++) {
			if(i == currentPage) {
				sb.append("<a href='/reservation/confirm?currentGroupPage="+i+"&userId="+userId+"'><b> "+i+" </b></a>");
			}
			else {
				sb.append("<a href='/reservation/confirm?currentGroupPage="+i+"&userId="+userId+"'> "+i+" </a>");
			}
		}
		//다음 버튼을 누를 수 있을 때 다음 버튼을 생성하는 부분
		if(needNext) {
			sb.append("<a href='/reservation/confirm?currentGroupPage="+(endNavi+1)+"&userId="+userId+"'> > </a>");
		}
		//태그를 StringBuilder의 append()메소드를 이용해서 붙인 후에 toString()메소드를 이용하여 String으로 만들어저 리턴
		return sb.toString();
	}
	
	public int totalGroupCount(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM RES_GROUP WHERE USER_ID=?";
		int recourdTotalCount = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				recourdTotalCount = rset.getInt("TOTALCOUNT");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return recourdTotalCount;
		
	}
	
	public int groupReservationCancle(Connection conn, int groupReservationNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query="UPDATE RES_GROUP SET RG_STATE='취소' WHERE RG_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, groupReservationNo);
			result = pstmt.executeUpdate();
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}
			else {
				JDBCTemplate.rollback(conn);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
}
