package kr.or.ddit.contact.dao;

import java.util.List;

import kr.or.ddit.contact.vo.ContactVO;

public interface IContactDao {

	public int insertContact(ContactVO contactVO);

	public List<ContactVO> selectContactList();

	public List<ContactVO> selectallList();

	public ContactVO detailList(int qNO);
}
