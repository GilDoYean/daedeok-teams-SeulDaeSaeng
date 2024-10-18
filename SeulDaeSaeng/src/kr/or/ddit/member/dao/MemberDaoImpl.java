package kr.or.ddit.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.member.vo.AddressVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.UsersVO;
import kr.or.ddit.restaurant.vo.restLikeVO;
import kr.or.ddit.util.MyBatisUtil;


public class MemberDaoImpl implements IMemberDao {

   public static IMemberDao instance = null;

   private MemberDaoImpl() {
   }

   public static IMemberDao getInstance() {
      if (instance == null)
         instance = new MemberDaoImpl();
      return instance;
   }

   public boolean loginCheck(MemberVO memberVO, boolean isMemberLogin) {

      boolean isSuccess = false;
      SqlSession session = kr.or.ddit.util.MyBatisUtil.getSqlSession();
      MemberVO result = null;

      try {

         if (isMemberLogin) {
            result = session.selectOne("member.memberCheck", memberVO);
         } else if (isMemberLogin == false) {
            result = session.selectOne("member.loginCheck", memberVO);
         }

         if (result != null) {
            isSuccess = true;
         }

      } catch (PersistenceException e) {
         e.printStackTrace();
         session.rollback();
      } finally {
         session.close();
      }

      return isSuccess;
   }

   /**
    * 이메일과 사번이 들어있는 객체로 비밀번호를 찾아서 비밀번호를 반환
    * 
    * @param memberVO
    * @return usersPass
    */
   @Override
   public String forgotPass(MemberVO memberVO) {

      SqlSession session = MyBatisUtil.getSqlSession();

      String usersPass = null;

      try {

         usersPass = session.selectOne("member.forgotPass", memberVO);

         if (usersPass != null) {
            session.commit();
         }

      } catch (PersistenceException ex) {
         session.rollback();
         ex.printStackTrace();
      } finally {
         session.close();
      }

      return usersPass;
   }

   @Override
   public int memberIdChk(String usersId) {
      
      SqlSession session = null;
      int cnt = 99;
      try {
         session = MyBatisUtil.getSqlSession(true);
         cnt = session.selectOne("member.memberIdChk", usersId);
         
      } catch (Exception e) {
         e.printStackTrace();
      }finally{
         if(session != null) {
            session.close();
         }
      }
      
      return cnt;
   }

   @Override
   public int usersJoin(UsersVO usersVo) {
      
      SqlSession session = null;
      int usersCnt = -1;
      
      try {
         session = MyBatisUtil.getSqlSession();
         usersCnt = session.insert("member.usersJoin", usersVo);
         System.out.println("마이바티스 끝 usersJoin 인서트 => " + usersCnt);
         if (usersCnt > 0) {
            session.commit();
         }

      } catch (PersistenceException ex) {
         session.rollback();
         ex.printStackTrace();
      } finally {
         session.close();
      }

      return usersCnt;
   }

   @Override
   public int addrJoin(AddressVO addrVo) {
      
      SqlSession session = null;
      int addrCnt = -1;
      
      try {
         session = MyBatisUtil.getSqlSession();
         addrCnt = session.insert("member.addrJoin", addrVo);
         System.out.println("마이바티스 끝 addrJoin 인서트 => " + addrCnt);
         if (addrCnt > 0) {
            session.commit();
         }

      } catch (PersistenceException ex) {
         session.rollback();
         ex.printStackTrace();
      } finally {
         session.close();
      }

      return addrCnt;
   }

   @Override
   public int memberJoin(MemberVO memberVo) {
      
      SqlSession session = null;
      int memberCnt = 0;
      
      try {
         session = MyBatisUtil.getSqlSession(true);
         memberCnt = session.insert("member.memberJoin", memberVo);
         System.out.println("마이바티스 끝 memberJoin 인서트 => " + memberCnt);
         if (memberCnt > 0) {
            session.commit();
         }

      } catch (PersistenceException ex) {
         session.rollback();
         ex.printStackTrace();
      } finally {
         session.close();
      }

      return memberCnt;
   }

   @Override
   public UsersVO selectUsers(String usersId) {
      
      SqlSession session = null;
      UsersVO usersVo = null;
      
      try {
         session = MyBatisUtil.getSqlSession(true);
         usersVo = session.selectOne("member.selectUsers", usersId);

      } catch (PersistenceException ex) {
         session.rollback();
         ex.printStackTrace();
      } finally {
         session.close();
      }
      return usersVo;
   }

   @Override
   public MemberVO selectMember(int usersNo) {
      SqlSession session = null;
      MemberVO memberVo = null;
      
      try {
         session = MyBatisUtil.getSqlSession(true);
         memberVo = session.selectOne("member.selectMember", usersNo);

      } catch (PersistenceException ex) {
         session.rollback();
         ex.printStackTrace();
      } finally {
         session.close();
      }
      return memberVo;
   }

   @Override
   public AddressVO selectAddr(int addrNo) {
      SqlSession session = null;
      AddressVO addrVo = null;
      
      try {
         session = MyBatisUtil.getSqlSession(true);
         addrVo = session.selectOne("member.selectAddr", addrNo);

      } catch (PersistenceException ex) {
         session.rollback();
         ex.printStackTrace();
      } finally {
         session.close();
      }
      return addrVo;
   }

   @Override
   public int addrModify(AddressVO addrVo) {
      SqlSession session = null;
      int addrCnt = -1;
      
      try {
         session = MyBatisUtil.getSqlSession();
         addrCnt = session.update("member.addrModify", addrVo);
         
         if(addrCnt == 1) {
            session.commit();
         }

      } catch (PersistenceException ex) {
         session.rollback();
         ex.printStackTrace();
      } finally {
         session.close();
      }
      return addrCnt;
   }

   @Override
   public int memberModify(MemberVO memberVo) {
      SqlSession session = null;
      int memberCnt = -1;
      
      try {
         session = MyBatisUtil.getSqlSession();
         memberCnt = session.update("member.memberModify", memberVo);
         
         if(memberCnt == 1) {
            session.commit();
         }

      } catch (PersistenceException ex) {
         session.rollback();
         ex.printStackTrace();
      } finally {
         session.close();
      }
      return memberCnt;
   }

   @Override
   public int memberUnregister(String usersId) {
      SqlSession session = null;
      int memberCnt = -1;
      
      try {
         session = MyBatisUtil.getSqlSession();
         memberCnt = session.update("member.memberUnregister", usersId);
         
         if(memberCnt == 1) {
            session.commit();
         }

      } catch (PersistenceException ex) {
         session.rollback();
         ex.printStackTrace();
      } finally {
         session.close();
      }
      return memberCnt;
   }

   @Override
   public int pwModify(Map<String, String> pwModifyMap) {
      SqlSession session = null;
      int Cnt = -1;
      
      try {
         session = MyBatisUtil.getSqlSession();
         Cnt = session.update("member.pwModify", pwModifyMap);
         
         if(Cnt == 1) {
            session.commit();
         }

      } catch (PersistenceException ex) {
         session.rollback();
         ex.printStackTrace();
      } finally {
         session.close();
      }
      return Cnt;
   }

   @Override
   public List<BoardVO> memberBoardList(int usersNo) {
      
      SqlSession session = null;
      List<BoardVO> memList = null ;
      try {
         session = MyBatisUtil.getSqlSession(true);
         memList = session.selectList("member.memberBoardList", usersNo);
         
      } catch (Exception e) {
         e.printStackTrace();
      }finally{
         if(session != null) {
            session.close();
         }
      }
      return memList;
   }
  
	@Override
	public String checkFindId(MemberVO memberVO) {
		
		SqlSession session = null;
		String memberIdInfo = null;
		
		try {
			session = MyBatisUtil.getSqlSession(true);
			memberIdInfo = session.selectOne("member.checkFindId", memberVO);
		} catch (PersistenceException ex) {

			ex.printStackTrace();
		} finally {
			session.close();
		}
		return memberIdInfo;
		}
  
	@Override
	public String checkFindPw(MemberVO memberVO) {
		
		SqlSession session = null;
		String memberPwInfo = null;
		
		try {
			session = MyBatisUtil.getSqlSession(true);
			memberPwInfo = session.selectOne("member.checkFindPw", memberVO);
		} catch (PersistenceException ex) {
			session.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return memberPwInfo;
	}

	@Override
	public String checking(String memName) {
		
		SqlSession session = null;
		String name = null;
		
		try {
			session = MyBatisUtil.getSqlSession();
			name = session.selectOne("member.checking", memName);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			 if (session != null) {
		            session.close();
		        }
		}
		return name;
	}
	
   //추가_길도연
   @Override
   public MemberVO getMemDetail(String usersId) {
      
      SqlSession session = null;
      MemberVO memDetail = null;
      
      try {
         session = MyBatisUtil.getSqlSession(true);
         memDetail = session.selectOne("member.getMemDetail", usersId);
      } catch (PersistenceException ex) {
         session.rollback();
         ex.printStackTrace();
      } finally {
         session.close();
      }
      return memDetail;
   }

   @Override
   public UsersVO getUsersDetail(String usersId) {
      
      SqlSession session = null;
      UsersVO usersDetail = null;
      
      try {
         session = MyBatisUtil.getSqlSession(true);
         usersDetail = session.selectOne("member.getUsersDetail", usersId);
      } catch (PersistenceException ex) {
         session.rollback();
         ex.printStackTrace();
      } finally {
         session.close();
      }
      return usersDetail;
   }


	@Override
	public int getBoardMember(int boardNo) {
	    SqlSession session = null;
	    int cnt = -1;
	    
	    try {
	       session = MyBatisUtil.getSqlSession();
	       cnt = session.update("report.reportNo", boardNo);
	       
	       if(cnt > 0) {
	    	   session.commit();
	       }
	    } catch (PersistenceException ex) {
	       session.rollback();
	       ex.printStackTrace();
	    } finally {
	       session.close();
	    }
	    return cnt;
	
	}

	@Override
	public List<MemberVO> getAllMember() {
		  SqlSession session = null;
	      List<MemberVO> memList = null;
	      
	      try {
	         session = MyBatisUtil.getSqlSession(true);
	         memList = session.selectList("member.getAllMember");
	      } catch (PersistenceException ex) {
	         session.rollback();
	         ex.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return memList;
	}

	@Override
	public List<AddressVO> getAllAddr() {
		SqlSession session = null;
	      List<AddressVO> addrList = null;
	      
	      try {
	         session = MyBatisUtil.getSqlSession(true);
	         addrList = session.selectList("member.getAllAddr");
	      } catch (PersistenceException ex) {
	         session.rollback();
	         ex.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return addrList;
	}
}

