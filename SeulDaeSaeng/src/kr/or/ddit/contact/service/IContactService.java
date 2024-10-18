package kr.or.ddit.contact.service;

import java.util.List;

import kr.or.ddit.contact.vo.ContactVO;

public interface IContactService {
	
	public int insertContact(ContactVO contactVO);

	public List<ContactVO> selectContactList();

	public List<ContactVO> selectallList();

	public ContactVO detailList(int qNO);


}
