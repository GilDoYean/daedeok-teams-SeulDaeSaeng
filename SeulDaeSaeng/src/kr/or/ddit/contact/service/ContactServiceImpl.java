package kr.or.ddit.contact.service;

import java.util.List;

import kr.or.ddit.contact.dao.ContactDaoImpl;
import kr.or.ddit.contact.dao.IContactDao;
import kr.or.ddit.contact.vo.ContactVO;

public class ContactServiceImpl implements IContactService {
	
	private static ContactServiceImpl instance = null;
	private ContactServiceImpl() {}
	public static ContactServiceImpl getInstance() {
		if(instance == null) {
			instance = new ContactServiceImpl();
		}
		return instance;
	}
	
	
	private IContactDao dao = ContactDaoImpl.getInstance();
	
	@Override
	public int insertContact(ContactVO contactVO) {
		return dao.insertContact(contactVO);
	
	}
	
	
	@Override
	public List<ContactVO> selectContactList() {
		return dao.selectContactList();
	}
	
	
	@Override
	public List<ContactVO> selectallList() {
		return dao.selectallList();
	}
	
	
	@Override
	public ContactVO detailList(int qNO) {
		return dao.detailList(qNO);
	}

}
