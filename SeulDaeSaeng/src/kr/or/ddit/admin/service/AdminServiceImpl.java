package kr.or.ddit.admin.service;

import java.util.List;

import kr.or.ddit.admin.dao.AdminDaoImpl;
import kr.or.ddit.admin.dao.IAdmindao;
import kr.or.ddit.admin.vo.MemberReqVO;
import kr.or.ddit.contact.vo.ContactVO;

public class AdminServiceImpl implements IAdminService {
	
	private static AdminServiceImpl instance = null;
	private AdminServiceImpl() {}
	public static AdminServiceImpl getInstance() {
		if(instance == null) {
			instance = new AdminServiceImpl();
		}
		return instance;
	}

	private IAdmindao dao = AdminDaoImpl.getInstance();
	
	@Override
	public List<MemberReqVO> selectmemList() {
		return dao.selectmemList();
	}
	
	
	
//	@Override
//	public int AdminAccept(int usersNo) {
//		return dao.AdminAccept(usersNo);
//	}
	
	
	@Override
	public List<MemberReqVO> selectallList() {
		return dao.selectallList();
	}
	
	
	@Override
	public MemberReqVO selectmember(String parameter) {
		return dao.selectmember(parameter);
	}
	@Override
	public int adminMemberYN(int usersNoParam) {
		return dao.adminMemberYN(usersNoParam);
	}
	
//	@Override
//	public List<ContactVO> selectContactList() {
//		return dao.selectContactList();
//	}

}
