package reservation.model.vo;

import java.util.ArrayList;

import reservation.group.model.vo.GroupReservation;
import reservation.personal.model.vo.PersonalReservation;

public class PageData {

	private ArrayList<PersonalReservation> personalPageList;
	private ArrayList<GroupReservation> groupPageList;
	private String personalPageNavi;
	private String groupPageNavi;
	
	public PageData() {
		
	}

	public ArrayList<PersonalReservation> getPersonalPageList() {
		return personalPageList;
	}

	public void setPersonalPageList(ArrayList<PersonalReservation> personalPageList) {
		this.personalPageList = personalPageList;
	}

	public ArrayList<GroupReservation> getGroupPageList() {
		return groupPageList;
	}

	public void setGroupPageList(ArrayList<GroupReservation> groupPageList) {
		this.groupPageList = groupPageList;
	}

	public String getPersonalPageNavi() {
		return personalPageNavi;
	}

	public void setPersonalPageNavi(String personalPageNavi) {
		this.personalPageNavi = personalPageNavi;
	}

	public String getGroupPageNavi() {
		return groupPageNavi;
	}

	public void setGroupPageNavi(String groupPageNavi) {
		this.groupPageNavi = groupPageNavi;
	}

	@Override
	public String toString() {
		return "PageData [personalPageList=" + personalPageList + ", groupPageList=" + groupPageList
				+ ", personalPageNavi=" + personalPageNavi + ", groupPageNavi=" + groupPageNavi + "]";
	}
	
	
}
