package reservation.group.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import reservation.group.model.dao.ReservationGroupDAO;
import reservation.model.vo.PageData;

public class ReservationGroupService {
	
	public JDBCTemplate factory;
	public ReservationGroupDAO reservationDAO;
	public ReservationGroupService() {
		factory = JDBCTemplate.getConnection();
		reservationDAO = new ReservationGroupDAO();
	}
	
	public int inputReservation(String userId, String date, String time, String place, String people) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			result = reservationDAO.inputReservation(conn, userId, date, time, place, people);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	public ArrayList<String> selectDisableDate(){
		ArrayList<String> disableDate = new ArrayList<String>();
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			disableDate = reservationDAO.selectDisableDate(conn);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		
		return disableDate;
	}
	
	
	public PageData groupSelectList(int currentGroupPage, String userId) {
		Connection conn = null;
		
		int recordCountPerPage = 5;
		int naviCountPerPage = 5;
		PageData pd = new PageData();
		
		try {
			conn = factory.createConnection();
			pd.setGroupPageList(reservationDAO.groupSelectList(conn, currentGroupPage, recordCountPerPage, userId));
			pd.setGroupPageNavi(reservationDAO.getGroupPageNavi(conn, currentGroupPage, recordCountPerPage, naviCountPerPage, userId));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		
		return pd;
	}
	
	public void totalGroupCount(String userId) {
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			reservationDAO.totalGroupCount(conn, userId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		
	}

	public int groupReservationCancle(int groupReservationNo) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			result = new ReservationGroupDAO().groupReservationCancle(conn, groupReservationNo);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
}
