package kr.or.ddit.admin.dao;

import java.util.List;

import kr.or.ddit.admin.vo.MemberReqVO;
import kr.or.ddit.contact.vo.ContactVO;

public interface IAdmindao {

	public MemberReqVO selectmember(String parameter);

	public List<MemberReqVO> selectmemList();

//	public int AdminAccept(int usersNo);

	public List<MemberReqVO> selectallList();

	public int adminMemberYN(int usersNoParam);

//	public List<ContactVO> selectContactList();

}
