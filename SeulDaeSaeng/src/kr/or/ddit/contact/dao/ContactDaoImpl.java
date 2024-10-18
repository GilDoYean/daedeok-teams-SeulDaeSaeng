package kr.or.ddit.contact.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.admin.vo.MemberReqVO;
import kr.or.ddit.contact.vo.ContactVO;
import kr.or.ddit.util.MyBatisUtil;

public class ContactDaoImpl implements IContactDao {
	
	
	private static ContactDaoImpl instance = null;
	private ContactDaoImpl() {}
	public static ContactDaoImpl getInstance() {
		if(instance == null) {
			instance = new ContactDaoImpl();
		}
		return instance;
	}
	
	
	@Override
	public int insertContact(ContactVO contactVO) {
		
		SqlSession session = null;		// 디비와 연결하기 위한 생산품
		int status = 0;
		
		try {
			session = MyBatisUtil.getSqlSession(true);
			System.out.println("vo왔니?" + contactVO.toString());
			status = session.insert("contact.insertContact", contactVO);
			
			if(status > 0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return status;
	}
	
	
	@Override
	public List<ContactVO> selectContactList() {

		SqlSession session = null;
		List<ContactVO> contactList = null;
		
		try {
			session = MyBatisUtil.getSqlSession(true);
			contactList = session.selectList("contact.selectContactList");   // xml 
			for(ContactVO vo : contactList) {
				System.out.println(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return contactList;
	}
	
	
	
	@Override
	public List<ContactVO> selectallList() {
		SqlSession session = null;
		List<ContactVO> allList = null;
		
		try {
			session = MyBatisUtil.getSqlSession(true);	
			allList = session.selectList("contact.selectallList");   // xml 
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return allList;
	}
	
	
	
	@Override
	public ContactVO detailList(int qNO) {
		SqlSession session = null;		// 디비와 연결하기 위한 생산품
		ContactVO contactVO = null;	// 전체 목록 데이터를 받을 변수 
		
		try {
			session = MyBatisUtil.getSqlSession(true);
			contactVO = session.selectOne("contact.detailList", qNO);
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) { 
				session.close();
			}
		}
		return contactVO;
	}
	
	
	

}
