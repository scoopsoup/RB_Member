package reservation.personal.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

//import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import common.JDBCTemplate;
import member.model.vo.Member;
import reservation.personal.model.vo.PersonalReservation;
import reservation.personal.model.vo.SelfPaperWeight;

public class ReservationPersonalDAO {
	
//	SelfPaperWeight selfPaperWeight = new SelfPaperWeight();
	
	public SelfPaperWeight selfPaperWeight;
	public ReservationPersonalDAO() {
		selfPaperWeight = new SelfPaperWeight();
//		selfPaperWeight = new SelfPaperWeight();
	}
	
//	public int insertMemberNumber(Connection conn, SelfPaperWeight selfPaperWeight) {
//		int result = 0;
//		PreparedStatement pstmt = null;
//		String query = "INSERT INTO CHK_RESULT(CR_CODE, CR_NUMBER) VALUES(SEQ_CR_CODE.NEXTVAL, ?)";
//		
//		try {
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, selfPaperWeight.getCrNumber());
//			result = pstmt.executeUpdate();
//			if(result > 0) {
//				JDBCTemplate.commit(conn);
//			}
//			else {
//				JDBCTemplate.rollback(conn);
//			}
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			JDBCTemplate.close(pstmt);
//		}
//		return result;
//	}
	
//	public int selfPaperWeight(Connection conn, SelfPaperWeight selfPaperWeight) {
//		int result = 0;
//		PreparedStatement pstmt = null;
//		String query = "UPDATE CHK_RESULT SET CR_BODY=?, CR_IN=?, CR_OUT=?, CR_DRG=?";
//		
//		try {
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, selfPaperWeight.getCrBody());
//			pstmt.setString(2, selfPaperWeight.getCrIn());
//			pstmt.setString(3, selfPaperWeight.getCrOut());
//			pstmt.setString(4, selfPaperWeight.getCrDrg());
//			result = pstmt.executeUpdate();
//			if(result > 0) {
//				JDBCTemplate.commit(conn);
//			}
//			else {
//				JDBCTemplate.rollback(conn);
//			}
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			JDBCTemplate.close(pstmt);
//		}
//		return result;
//	}
	
//	public void selfPaperWeightResult(Connection conn, SelfPaperWeight selfPaper) {
//		selfPaperWeight.setCrBody(selfPaper.getCrBody());
//		selfPaperWeight.setCrIn(selfPaper.getCrIn());
//		selfPaperWeight.setCrOut(selfPaper.getCrOut());
//		selfPaperWeight.setCrDrg(selfPaper.getCrDrg());
//		selfPaperWeight.setCrKor(selfPaper.getCrKor());
//		selfPaperWeight.setCrOver(selfPaper.getCrOver());
//		selfPaperWeight.setCrFamily(selfPaper.getCrFamily());
//		
//		
//		
//		//자가문진으로 넘어왔다는 뜻은 앞의 자동문진의 값이 모두 Y라는 뜻
////		selfPaperWeight.setCrBanDis("Y");
////		selfPaperWeight.setCrBanCheck("Y");
////		selfPaperWeight.setCrBanDrg("Y");
//		
//		System.out.println(selfPaperWeight.toString());
//
//	}
	
	//자가문진
	public int selfResultInput(Connection conn, SelfPaperWeight selfpaper, String number, String userId) {
		int selfTotalResult = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO CHK_RESULT(CR_CODE, CR_NUMBER, CR_BAN_DIS, CR_BAN_CHECK, CR_BAN_DRG, CR_BODY, CR_IN, CR_OUT, CR_DRG, CR_KOR, CR_OVER, CR_FAMILY, CR_RES, CR_DATE, USER_ID) "
				+ "VALUES(SEQ_CR_CODE.NEXTVAL, ?, 'Y', 'Y', 'Y', ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, number);
			pstmt.setString(2, selfpaper.getCrBody());
			pstmt.setString(3, selfpaper.getCrIn());
			pstmt.setString(4, selfpaper.getCrOut());
			pstmt.setString(5, selfpaper.getCrDrg());
			pstmt.setString(6, selfpaper.getCrKor());
			pstmt.setString(7, selfpaper.getCrOver());
			pstmt.setString(8, selfpaper.getCrFamily());
			pstmt.setString(9, selfPaperWeight.getCrRes());
			pstmt.setString(10, userId);
			selfTotalResult = pstmt.executeUpdate();
			
			if(selfTotalResult > 0) {
				JDBCTemplate.commit(conn);
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
		
		return selfTotalResult;
	}
	
	
	//금지질병(진료) disease
	public String autoDisase(Connection conn, String name, String number) {
//		this.selfPaperWeight = selfPaperWeight;
		String disease = null;
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT HOSPITAL.HP_DATE, HOSPITAL.HP_DIS FROM HOSPITAL JOIN BAN_DIS ON HOSPITAL.HP_DIS = BAN_DIS.BI_NAME WHERE HOSPITAL.HP_NUMBER = ? AND HOSPITAL.HP_NAME = ? AND BAN_DIS.BI_DATE > (SYSDATE - HOSPITAL.HP_DATE)";
		
//		System.out.println(name);
//		System.out.println(number);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, number);
			pstmt.setString(2, name);
			rset = pstmt.executeQuery();
			selfPaperWeight.setCrNumber(number);
			while(rset.next()) { // 겹치는게 있으면 disease값 증가 => 즉, 겹치면 disease는 0이상임
				result++;
			}
//			System.out.println(disease);
			if(result > 0) { // 겹치는게 있으면
				selfPaperWeight.setCrBanDis("N");
				disease = "N";
			}
			else {
				selfPaperWeight.setCrBanDis("Y");
				disease = "Y";
			}
			System.out.println(selfPaperWeight.toString());
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		
		return disease;
	}
	
	//금지검사(검사) inspection
	public String autoInspection(Connection conn, String name, String number) {
//		this.selfPaperWeight = selfPaperWeight;
		int result = 0;
		String inspection = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT HOSPITAL.HP_DATE, HOSPITAL.HP_CHECK FROM HOSPITAL JOIN BAN_CHECK ON HOSPITAL.HP_CHECK = BAN_CHECK.BC_NAME WHERE HOSPITAL.HP_NUMBER = ? AND HOSPITAL.HP_NAME = ? AND BAN_CHECK.BC_DATE > (SYSDATE - HOSPITAL.HP_DATE)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, number);
			pstmt.setString(2, name);
			rset = pstmt.executeQuery();
			selfPaperWeight.setCrNumber(number);
			while(rset.next()) {
				result++;
			}
			if(result > 0) {
				selfPaperWeight.setCrBanCheck("N");
				inspection = "N";
			}
			else {
				selfPaperWeight.setCrBanCheck("Y");
				inspection = "Y";
			}
			System.out.println(selfPaperWeight.toString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		
		return inspection;
	}
	
	
	//금지약물(약처방) drug
	public String autoDrug(Connection conn, String name, String number) {
//		this.selfPaperWeight = selfPaperWeight;
		int result = 0;
		String drug = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT HOSPITAL.HP_DATE, HOSPITAL.HP_MEDI FROM HOSPITAL JOIN BAN_DRG ON HOSPITAL.HP_MEDI = BAN_DRG.BD_NAME WHERE HOSPITAL.HP_NUMBER = ? AND HOSPITAL.HP_NAME = ? AND BAN_DRG.BD_DATE > (SYSDATE - HOSPITAL.HP_DATE)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, number);
			pstmt.setString(2, name);
			rset = pstmt.executeQuery();
			selfPaperWeight.setCrNumber(number);
			while(rset.next()) {
				result++;
			}
			if(result > 0) {
				selfPaperWeight.setCrBanDrg("N");
				drug = "N";
			}
			else {
				selfPaperWeight.setCrBanDrg("Y");
				drug = "Y";
			}
			System.out.println(selfPaperWeight.toString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		return drug;
		//자동문진으로 값을 넣어주고
	}
	
	 //최종결과 - 자동문진
	public int autoTotalResult(String disease, String inspection, String drug) {
		int result = 0;
		if(disease.equals("Y") && inspection.equals("Y") && drug.equals("Y")) {
			selfPaperWeight.setCrRes("Y");
			result = 0;
		}
		else {
			selfPaperWeight.setCrRes("N");
			result = 1;
		}
		System.out.println(selfPaperWeight.toString());
		
		return result;
	}
	
	//최종결과 - 자가문진
	public int selfTotalResult(String body, String in, String out, String drg, String kor, String over, String family) {
		int result = 0;
		if(body.equals("Y") && in.equals("Y") && out.equals("Y") && drg.equals("Y") && kor.equals("Y") && over.equals("Y") && family.equals("Y")) {
			selfPaperWeight.setCrRes("Y");
			result = 0;
		}
		else {
			selfPaperWeight.setCrRes("N");
			result = 1;
		}
		System.out.println(selfPaperWeight.toString());
		return result;
	}
	
	//자동문진 insert
	public int autoPaperInput(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
//		ResultSet rset = null;
		String query = "INSERT INTO CHK_RESULT(CR_CODE, CR_NUMBER, CR_BAN_DIS, CR_BAN_CHECK, CR_BAN_DRG, CR_RES, CR_DATE, USER_ID) VALUES(SEQ_CR_CODE.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE, ?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, selfPaperWeight.getCrNumber());
			pstmt.setString(2, selfPaperWeight.getCrBanDis());
			pstmt.setString(3, selfPaperWeight.getCrBanCheck());
			pstmt.setString(4, selfPaperWeight.getCrBanDrg());
			pstmt.setString(5, selfPaperWeight.getCrRes());
			pstmt.setString(6, userId);
			result = pstmt.executeUpdate();
			if(result > 0) {
				JDBCTemplate.commit(conn);
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
	
	//개인예약
	public int reservationInput(Connection conn, PersonalReservation personalReservation) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String query = "INSERT INTO RES_PRIV VALUES(SEQ_RP_NO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personalReservation.getRpDate());
			pstmt.setString(2, personalReservation.getRpPlace());
			pstmt.setString(3, personalReservation.getRpTime());
			pstmt.setString(4, personalReservation.getRpKind());
			pstmt.setString(5, "승인");
			pstmt.setString(6, personalReservation.getRpOn());
			pstmt.setString(7, personalReservation.getUserId());
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
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
	
	//개인예약조회
//	public ArrayList<PersonalReservation> selectReservationList(Connection conn, String userId){
//		ArrayList<PersonalReservation> rList = null;
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		String query = "SELECT * FROM RES_PRIV WHERE USER_ID = ? AND RP_ON='Y'";
////		String query = "SELECT * FROM RES_PRIV WHERE USER_ID = ?";
//		
//		try {
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, userId);
//			rset = pstmt.executeQuery();
//			rList = new ArrayList<PersonalReservation>();
//			while(rset.next()) {
//				PersonalReservation personalReservation = new PersonalReservation();
//				personalReservation.setRpNo(rset.getInt("RP_NO"));
//				personalReservation.setRpDate(rset.getString("RP_DATE"));
//				personalReservation.setRpPlace(rset.getString("RP_PLACE"));
//				personalReservation.setRpTime(rset.getString("RP_TIME"));
//				personalReservation.setRpKind(rset.getString("RP_KIND"));
//				personalReservation.setRpState(rset.getString("RP_STATE"));
//				personalReservation.setRpOn(rset.getString("RP_ON"));
//				personalReservation.setUserId(userId);
//				rList.add(personalReservation);
//			}
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			JDBCTemplate.close(rset);
//			JDBCTemplate.close(pstmt);
//		}
//		return rList;
//	}
	
//	public void syso() {
//		System.out.println(selfPaperWeight.toString());
//	}
	
//	public int autoPaperInspection(Connection conn) {
//		int result = 0;
//		
//		return result;
//	}
	
	//개인예약 취소
	public int PersonalReservationCancle(Connection conn, int reservationNo, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE RES_PRIV SET RP_STATE='취소' WHERE RP_NO=? AND USER_ID=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reservationNo);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
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
	
	//개인예약 수정
	public int PersonalReservationModify(Connection conn, int reservationNo, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "";
		
		try {
			pstmt = conn.prepareStatement(query);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	
	//이전예약 조회
	public int reservationInquiry(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM RES_PRIV WHERE USER_ID=? AND RP_STATE='승인'";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result++;
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
	
	//문진서비스 입력(onLine = N)
	public int houseReservation(Connection conn, String userId, String onLine) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO RES_PRIV(RP_NO, RP_DATE, RP_ON, USER_ID) VALUES(SEQ_RP_NO.NEXTVAL, TO_CHAR(SYSDATE, 'YYYY-MM-DD'), ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, onLine);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
			if(result > 0) {
				JDBCTemplate.commit(conn);
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
	
	
	
	//개인예약조회(페이징)
		public ArrayList<PersonalReservation> personalSelectList(Connection conn, int currentPage, int recordCountPage, String userId) {
			ArrayList<PersonalReservation> pList = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String query = "SELECT * FROM (SELECT RES_PRIV.*, ROW_NUMBER() OVER(ORDER BY RP_NO DESC) AS NUM FROM RES_PRIV WHERE USER_ID = ? AND RP_ON='Y')WHERE (NUM BETWEEN ? AND ?)";
			int start = currentPage * recordCountPage - (recordCountPage - 1);
			int end = currentPage * recordCountPage;
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, userId);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				rset = pstmt.executeQuery();
				pList = new ArrayList<PersonalReservation>();
				while(rset.next()) {
					PersonalReservation personalReservation = new PersonalReservation();
					personalReservation.setRpNo(rset.getInt("RP_NO"));
					personalReservation.setRpDate(rset.getString("RP_DATE"));
					personalReservation.setRpPlace(rset.getString("RP_PLACE"));
					personalReservation.setRpTime(rset.getString("RP_TIME"));
					personalReservation.setRpKind(rset.getString("RP_KIND"));
					personalReservation.setRpState(rset.getString("RP_STATE"));
					personalReservation.setRpOn(rset.getString("RP_ON"));
					personalReservation.setUserId(userId);
					pList.add(personalReservation);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			return pList;
			
		}
		
		
		public String getPersonalPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage, String userId) {
			int recordTotalCount = totalPerCount(conn, userId);
			
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
				sb.append("<a href='/reservation/confirm?currentPerPage="+(startNavi-1)+"&userId="+userId+"'> < </a>");
			}

			//1 ~ 10까지의 숫자를 for문을 이용해서 생성하고 만들어 주는 부분

			for(int i = startNavi; i <= endNavi; i++) {
				if(i == currentPage) {
					sb.append("<a href='/reservation/confirm?currentPerPage="+i+"&userId="+userId+"'><b> "+i+" </b></a>");
				}
				else {
					sb.append("<a href='/reservation/confirm?currentPerPage="+i+"&userId="+userId+"'> "+i+" </a>");
				}
			}
			//다음 버튼을 누를 수 있을 때 다음 버튼을 생성하는 부분
			if(needNext) {
				sb.append("<a href='/reservation/confirm?currentPerPage="+(endNavi+1)+"&userId="+userId+"'> > </a>");
			}
			//태그를 StringBuilder의 append()메소드를 이용해서 붙인 후에 toString()메소드를 이용하여 String으로 만들어저 리턴
			return sb.toString();
		}
		
		public int totalPerCount(Connection conn, String userId) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String query = "SELECT COUNT(*) AS TOTALCOUNT FROM RES_PRIV WHERE RP_ON='Y' AND USER_ID=?";
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
	
	
	//--------------------------------------------------------------------------------------------------------------
	
	public Member selectOne(String userId, String userPwd, Connection conn) {
//		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MEMBER WHERE USER_ID = '" + userId + "' AND USER_PWD = '" + userPwd+ "'";
		Member member = null;

		try {
			//드라이버 등록
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			//연결 가져오기
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","STUDENT", "STUDENT");
			//statement 생성
			stmt = conn.createStatement();
			//쿼리문 실행 및 결과 가져오기(테이블 형태로)
			rset = stmt.executeQuery(query);
			//결과값이 있다면
			if(rset.next()) {
				member = new Member();
				//MEMBER_ID가져와서 member객체에 넣어주기
				member.setUserId(rset.getString("USER_ID"));
				member.setUserPwd(rset.getString("USER_PWD"));
				member.setUserName(rset.getString("USER_NAME"));
				member.setUserBD(rset.getString("USER_BD"));
				member.setUserGender(rset.getString("USER_GENDER"));
				member.setUserPhone(rset.getString("USER_PHONE"));
				member.setUserAddr(rset.getString("USER_ADDR"));
				member.setUserEmail(rset.getString("USER_EMAIL"));
				member.setUserABO(rset.getString("USER_ABO"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}


		return member;
	}

	


}
