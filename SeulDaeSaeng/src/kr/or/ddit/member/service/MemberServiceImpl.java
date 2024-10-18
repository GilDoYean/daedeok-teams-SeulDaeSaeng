package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.AddressVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.UsersVO;
import kr.or.ddit.restaurant.vo.restLikeVO;


public class MemberServiceImpl implements IMemberService{
	
	public static IMemberService instance = null;

	private MemberServiceImpl() {}
	public static IMemberService getInstance() {
		if(instance == null) instance = new MemberServiceImpl();
		return instance;
	}
	IMemberDao memberDao = MemberDaoImpl.getInstance();
	

	@Override
	public boolean loginCheck(MemberVO memberVO, boolean isMemberLogin) {
		return memberDao.loginCheck(memberVO, isMemberLogin);
	}
	/**
	 * 이메일과 사번이 들어있는 객체로 비밀번호를 찾아서 비밀번호를 반환
	 * @param memberVO
	 * @return usersPass
	 */
	@Override
	public String forgotPass(MemberVO memberVO) {
		return memberDao.forgotPass(memberVO);
	}
  
	@Override
	public int memberIdChk(String usersId) {
		return memberDao.memberIdChk(usersId);
	}
	@Override
	public int usersJoin(UsersVO usersVo) {
		return memberDao.usersJoin(usersVo);
	}
	@Override
	public int addrJoin(AddressVO addrVo) {
		return memberDao.addrJoin(addrVo);
	}
	@Override
	public int memberJoin(MemberVO memberVo) {
		return memberDao.memberJoin(memberVo);
	}
	@Override
	public UsersVO selectUsers(String usersId) {
		return memberDao.selectUsers(usersId);
	}
	@Override
	public MemberVO selectMember(int usersNo) {
		return memberDao.selectMember(usersNo);
	}
	@Override
	public AddressVO selectAddr(int addrNo) {
		return memberDao.selectAddr(addrNo);
	}

	@Override
	public String checkFindId(MemberVO memberVO) {
		return memberDao.checkFindId(memberVO);
	}
	@Override
	public String checkFindPw(MemberVO memberVO) {
		return memberDao.checkFindPw(memberVO);
	}
	@Override
	public String checking(String memName) {
		return memberDao.checking(memName);
	}	
	@Override		
	public int addrModify(AddressVO addrVo) {
		return memberDao.addrModify(addrVo);
	}
	@Override
	public int memberModify(MemberVO memberVo) {
		return memberDao.memberModify(memberVo);
	}
	@Override
	public int memberUnregister(String usersId) {
		return memberDao.memberUnregister(usersId);
	}
	@Override
	public int pwModify(Map<String, String> pwModifyMap) {
		return memberDao.pwModify(pwModifyMap);
	}
	@Override
	public List<BoardVO> memberBoardList(int usersNo) {
		return memberDao.memberBoardList(usersNo);
	}
	@Override
	public MemberVO getMemDetail(String usersId) {
		return memberDao.getMemDetail(usersId);
	}
	@Override
	public UsersVO getUsersDetail(String usersId) {
		return memberDao.getUsersDetail(usersId);

	}
	@Override
	public MemberVO selectScore(String usersId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MemberVO selectOne(String usersId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getBoardMember(int boardNo) {
		return memberDao.getBoardMember(boardNo);
	}
	@Override
	public List<MemberVO> getAllMember() {
		return memberDao.getAllMember();
	}
	@Override
	public List<AddressVO> getAllAddr() {
		return memberDao.getAllAddr();
	}
}

