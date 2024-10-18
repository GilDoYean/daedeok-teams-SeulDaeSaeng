
package kr.or.ddit.member.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.member.vo.AddressVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.UsersVO;
import kr.or.ddit.restaurant.vo.restLikeVO;

public interface IMemberDao {

	public boolean loginCheck(MemberVO memberVO, boolean isMemberLogin);

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

	public int getBoardMember(int boardNo);

	public List<MemberVO> getAllMember();

	public List<AddressVO> getAllAddr();

}

