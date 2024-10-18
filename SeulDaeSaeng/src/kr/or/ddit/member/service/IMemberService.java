
package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.member.vo.AddressVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.UsersVO;
import kr.or.ddit.restaurant.vo.restLikeVO;


public interface IMemberService {

	/**
	 * 로그인 체크를 위한 메서드, 파라미터로 memberVO와 관리자로그인 체크 여부가 들어간다
	 * @param membervo, isMemberLogin
	 * @return 로그인 성공여부
	 */
	public boolean loginCheck(MemberVO memberVO, boolean isMemberLogin);

	/**
	 * 이메일과 사번이 들어있는 객체로 비밀번호를 찾아서 비밀번호를 반환
	 * @param memberVO
	 * @return usersPass
	 */
	public String forgotPass(MemberVO memberVO);
  
	int memberIdChk(String usersId);

	public int usersJoin(UsersVO usersVo);

	public int addrJoin(AddressVO addrVo);

	public int memberJoin(MemberVO memberVo);

	public UsersVO selectUsers(String usersId);

	public MemberVO selectMember(int usersNo);

	public AddressVO selectAddr(int addrNo);
	
	public int addrModify(AddressVO addrVo);

	public int memberModify(MemberVO memberVo);

	public int memberUnregister(String usersId);

	public int pwModify(Map<String, String> pwModifyMap);

	public List<BoardVO> memberBoardList(int usersNo);
	 
	public MemberVO getMemDetail(String usersId);

	public UsersVO getUsersDetail(String usersId);

	public String checkFindId(MemberVO memberVO);
	
	public String checkFindPw(MemberVO memberVO);

	public String checking(String memName);

	public MemberVO selectScore(String usersId);

	public MemberVO selectOne(String usersId);

	public int getBoardMember(int boardNo);

	public List<MemberVO> getAllMember();

	public List<AddressVO> getAllAddr();
}

