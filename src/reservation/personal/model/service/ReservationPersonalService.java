package reservation.personal.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import common.JDBCTemplate;
import member.model.vo.Member;
import reservation.model.vo.PageData;
import reservation.personal.model.dao.ReservationPersonalDAO;
import reservation.personal.model.vo.PersonalReservation;
import reservation.personal.model.vo.SelfPaperWeight;

public class ReservationPersonalService {
	
	public JDBCTemplate factory;
	public ReservationPersonalDAO reservationDAO;
//	SelfPaperWeight selfPaperWeight;
	public ReservationPersonalService() {
		factory = JDBCTemplate.getConnection();
		if ( reservationDAO == null ) {
		reservationDAO = new ReservationPersonalDAO();
		}
//		selfPaperWeight = new SelfPaperWeight();
	}
	
//	public int insertMemberNumber(SelfPaperWeight selfPaperWeight) {
//		int result = 0;
//		Connection conn = null;
//		try {
//			conn = factory.createConnection();
//			result = new ReservationDAO().insertMemberNumber(conn, selfPaperWeight);
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			JDBCTemplate.close(conn);
//		}
//		return result;
//	}
//	
	//자가문진
//	public int UpdateSlefPaper(SelfPaperWeight selfPaperWeight) {
//		int result = 0;
//		
//		Connection conn = null;
//		try {
//			conn = factory.createConnection();
//			result = new ReservationDAO().selfPaperWeight(conn, selfPaperWeight);
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			JDBCTemplate.close(conn);
//		}
//		
//		return result;
//	}
	
	//자가문진
//	public void selfPaperWeightResult(SelfPaperWeight selfPaper) {
//		Connection conn = null;
//		try {
//			conn = factory.createConnection();
//			reservationDAO.selfPaperWeightResult(conn, selfPaper);
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			JDBCTemplate.close(conn);
//		}
//	}
	
	//자가문진
	public int selfResultInput(SelfPaperWeight selfpaper, String number, String userId) {
		int selfTotalResult = 0;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			selfTotalResult = reservationDAO.selfResultInput(conn, selfpaper, number, userId);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		return selfTotalResult;
	}
	
	//자동문진
	//금지질병(진료) disease
	public String autoDisease(String name, String number) {
//		this.selfPaperWeight = selfPaperWeight;
		String disease = null;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			disease = reservationDAO.autoDisase(conn, name, number);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		return disease;
	}
	
	//자동문진
	//금지검사(검사) inspection
	public String autoInspection(String name, String number) {
//		this.selfPaperWeight = selfPaperWeight;
		String inspection = null;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			inspection = reservationDAO.autoInspection(conn, name, number);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		return inspection;
	}
	
	//자동문진
	//금지약물(약처방) drug
	public String autoDrug(String name, String number) {
//		this.selfPaperWeight = selfPaperWeight;
		String drug = null;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			drug = reservationDAO.autoDrug(conn, name, number);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		return drug;
	}
	
	//최종 결과
	public int autoTotalResult(String disease, String inspection, String drug) {
		int result = reservationDAO.autoTotalResult(disease, inspection, drug);
		return result;
	}
	
	public int selfTotalResult(String body, String in, String out, String drg, String kor, String over, String family) {
		int result = reservationDAO.selfTotalResult(body, in, out, drg, kor, over, family);
		
		return result;
	}
	
	//자동문진 insert
	public int autoPaperInput(String userId) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = reservationDAO.autoPaperInput(conn, userId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	//개인예약
	public int reservationInput(PersonalReservation personalReservation) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			result = reservationDAO.reservationInput(conn, personalReservation);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	
//	public int autoPaperInspection() {
//		int result = 0;
//		Connection conn = null;
//		try {
//			conn = factory.createConnection();
//			result = new ReservationDAO().autoPaperInspection(conn);
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			JDBCTemplate.close(conn);
//		}
//		return result;
//	}
	
	//개인예약조회
//	public ArrayList<PersonalReservation> selectReservationList(String userId){
//		ArrayList<PersonalReservation> rList = null;
//		Connection conn = null;
//		
//		try {
//			conn = factory.createConnection();
//			rList = reservationDAO.selectReservationList(conn, userId);
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			JDBCTemplate.close(conn);
//		}
//		return rList;
//	}
	
	//개인예약 취소
	public int PersonalReservationCancle(int reservationNo, String userId) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			result = reservationDAO.PersonalReservationCancle(conn, reservationNo, userId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	//개인예약 수정
	public int PersonalReservationModify(int reservationNo, String userId) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			result = reservationDAO.PersonalReservationModify(conn, reservationNo, userId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	//이전의 예약정보가 있는지 확인
	public int reservationInquiry(String userId) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			result = reservationDAO.reservationInquiry(conn, userId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	//문진서비스 입력(onLine = N)
	public int houseReservation(String userId, String onLine) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			result = reservationDAO.houseReservation(conn, userId, onLine);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	public PageData personalSelectList(int currentPerPage, String userId) {
		Connection conn = null;
		
		int recordCountPerPage = 5;
		int naviCountPerPage = 5;
		PageData pd = new PageData();
		
		try {
			conn = factory.createConnection();
			pd.setPersonalPageList(reservationDAO.personalSelectList(conn, currentPerPage, recordCountPerPage, userId));
			pd.setPersonalPageNavi(reservationDAO.getPersonalPageNavi(conn, currentPerPage, recordCountPerPage, naviCountPerPage, userId));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	
	public void totalPerCount(String userId) {
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			reservationDAO.totalPerCount(conn, userId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		
	}

	
	//--------------------------------------------------------------------------------------------------------------
	public Member selectMember(String userId, String userPwd) {
		//DB연결을 DAO에서 하는것이 아니라 Service에서 하도록 함.
		//selectMember 메소드 내에서 사용할 수 있는 변수 선언
		Member member = null;
		try {
			Connection conn = factory.createConnection();
			member = new ReservationPersonalDAO().selectOne(userId, userPwd, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}

}
