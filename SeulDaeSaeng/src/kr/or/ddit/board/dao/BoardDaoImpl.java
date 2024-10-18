package kr.or.ddit.board.dao;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.FileDetailVO;
import kr.or.ddit.board.vo.FileShareVO;
import kr.or.ddit.board.vo.FilesVO;
import kr.or.ddit.board.vo.HomeworkVO;
import kr.or.ddit.board.vo.HwSubmitVO;
import kr.or.ddit.board.vo.ReplyVO;
import kr.or.ddit.board.vo.StoryVO;
import kr.or.ddit.contact.vo.ContactVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.UsersVO;
import kr.or.ddit.util.MyBatisUtil;

public class BoardDaoImpl implements IBoardDao {

	private static IBoardDao boardDao = new BoardDaoImpl();

	private BoardDaoImpl() {
		boardDao = BoardDaoImpl.getInstance();
	}

	public static IBoardDao getInstance() {
		return boardDao;
	}

	@Override
	public List<BoardVO> allBoardList() {

		List<BoardVO> boardList = new ArrayList<BoardVO>();

		SqlSession session = null;

		try {
			session = MyBatisUtil.getSqlSession(true);

			boardList = session.selectList("board.allBoardList");

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return boardList;
	}

	@Override
	public BoardVO getBoardDetail(int boardNo) {

		SqlSession session = MyBatisUtil.getSqlSession(true);

		BoardVO boardDetail = null;

		try {
			boardDetail = session.selectOne("board.getBoardDetail", boardNo);
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return boardDetail;
	}

	@Override
	public List<BoardVO> selectBoardList(int level) {
		List<BoardVO> boardList = new ArrayList<BoardVO>();

		SqlSession session = null;

		try {
			session = MyBatisUtil.getSqlSession(true);
			boardList = session.selectList("board.selectBoardList", level);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return boardList;
	}

	@Override
	public MemberVO getwriterDetail(Map<String, Object> parameter) {

		SqlSession session = MyBatisUtil.getSqlSession(true);

		MemberVO writerDetail = null;

		try {
			writerDetail = session.selectOne("board.getWriterDetail", parameter);
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return writerDetail;
	}

	@Override
	public List<ReplyVO> getReplyList(int boardNo) {

		List<ReplyVO> replyList = new ArrayList<ReplyVO>();

		SqlSession session = null;

		try {

			session = MyBatisUtil.getSqlSession(true);
			replyList = session.selectList("board.getReplyList", boardNo);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return replyList;
	}

	@Override
	public MemberVO getReplyWriterDetail(Map<String, Object> parameter) {
		SqlSession session = MyBatisUtil.getSqlSession(true);

		MemberVO ReplyWriterDetail = null;

		try {
			ReplyWriterDetail = session.selectOne("board.getReplyWriterDetail", parameter);
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return ReplyWriterDetail;
	}

	@Override
	public int deleteBoard(int boardNo) {
		SqlSession session = null;

		int cnt = 0;
		try {
			session = MyBatisUtil.getSqlSession();

			cnt = session.update("board.deleteBoard", boardNo);

			if (cnt > 0) {
				session.commit();
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public int insertBoard(BoardVO boardVO) {

		SqlSession session = null;
		int status = 0;

		try {
			session = MyBatisUtil.getSqlSession();
			status = session.insert("board.insertBoard", boardVO);

			if (status > 0) { // 성공
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return status;
	}

	@Override
	public int insertReply(ReplyVO replyVO) {

		SqlSession session = null;
		int status = 0;
		try {
			session = MyBatisUtil.getSqlSession();

			status = session.insert("board.insertReply", replyVO);

			if (status > 0) {
				session.commit();
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return status;
	}

	@Override
	public int updateHit(int boardNo) {

		SqlSession session = null;
		int hit = 0;
		try {
			session = MyBatisUtil.getSqlSession();

			hit = session.update("board.updateHit", boardNo);

			if (hit > 0) {
				session.commit();
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return hit;
	}

	@Override
	public UsersVO getUsersDetail(int usersNo) {

		SqlSession session = MyBatisUtil.getSqlSession(true);

		UsersVO UsersDetail = null;

		try {
			UsersDetail = session.selectOne("board.getUsersDetail", usersNo);
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return UsersDetail;
	}

	@Override
	public MemberVO getMemberDetail(int usersNo) {

		SqlSession session = MyBatisUtil.getSqlSession(true);

		MemberVO MemberDetail = null;

		try {
			MemberDetail = session.selectOne("board.getMemberDetail", usersNo);
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return MemberDetail;
	}

	@Override
	public List<BoardVO> getClassNoticeList(int level) {
		List<BoardVO> boardList = new ArrayList<BoardVO>();

		SqlSession session = null;

		try {
			session = MyBatisUtil.getSqlSession(true);
			boardList = session.selectList("board.getClassNoticeList", level);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return boardList;
	}

	@Override
	public int updateBoard(Map<String, Object> parameter) {

		SqlSession session = null;
		int status = 0;

		try {
			session = MyBatisUtil.getSqlSession();
			status = session.update("board.updateBoard", parameter);

			if (status > 0) { // 성공
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return status;
	}

	@Override
	public int updateReply(Map<String, Object> parameter) {

		SqlSession session = null;
		int status = 0;

		try {
			session = MyBatisUtil.getSqlSession();
			status = session.update("board.updateReply", parameter);

			if (status > 0) { // 성공
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return status;
	}

	@Override
	public int deleteReply(int replyNo) {

		SqlSession session = null;
		int status = 0;

		try {
			session = MyBatisUtil.getSqlSession();
			status = session.update("board.deleteReply", replyNo);

			if (status > 0) { // 성공
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return status;
	}

	@Override
	public List<BoardVO> searchAllBoardList(Map<String, Object> parameter) {

		List<BoardVO> boardList = new ArrayList<BoardVO>();

		SqlSession session = null;

		try {
			session = MyBatisUtil.getSqlSession(true);
			boardList = session.selectList("board.searchAllBoardList", parameter);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return boardList;
	}

	@Override
	public List<BoardVO> searchSelectBoardList(Map<String, Object> parameter) {

		List<BoardVO> boardList = new ArrayList<BoardVO>();

		SqlSession session = null;

		try {
			session = MyBatisUtil.getSqlSession(true);
			boardList = session.selectList("board.searchSelectBoardList", parameter);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return boardList;
	}

	@Override
	public int reportBoard(int boardNo) {

		SqlSession session = null;
		int status = 0;

		try {
			session = MyBatisUtil.getSqlSession();
			status = session.update("board.reportBoard", boardNo);

			if (status > 0) { // 성공
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return status;
	}

	@Override
	public int insertHomework(Map<String, Object> parameter) {

		SqlSession session = null;
		int status = 0;

		try {

			session = MyBatisUtil.getSqlSession();
			status = session.insert("board.insertHomework", parameter);

			if (status > 0) { // 성공
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return status;
	}

	@Override
	public List<HomeworkVO> getHwList(int levelChk) {

		List<HomeworkVO> hwList = new ArrayList<HomeworkVO>();

		SqlSession session = null;

		try {
			session = MyBatisUtil.getSqlSession(true);

			hwList = session.selectList("board.getHwList", levelChk);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return hwList;
	}

	@Override
	public HomeworkVO getHwDetail(int hwNo) {

		SqlSession session = MyBatisUtil.getSqlSession(true);

		HomeworkVO hwDetail = null;

		try {
			hwDetail = session.selectOne("board.getHwDetail", hwNo);
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return hwDetail;
	}

	@Override
	public MemberVO getHwWriterDetail(Map<String, Object> parameter) {

		SqlSession session = MyBatisUtil.getSqlSession(true);

		MemberVO writerDetail = null;

		try {
			writerDetail = session.selectOne("board.getHwWriterDetail", parameter);
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return writerDetail;
	}

	@Override
	public List<FileDetailVO> getFileList(int classNo) {

		List<FileDetailVO> fileList = new ArrayList<FileDetailVO>();

		SqlSession session = null;

		try {
			session = MyBatisUtil.getSqlSession(true);

			fileList = session.selectList("board.getFileList", classNo);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return fileList;
	}
	/*
	 * 
	 * @Override public int insertFileDetail(FileDetailVO fileDetail) {
	 * 
	 * SqlSession session = null; int status = 0;
	 * 
	 * try { session = MyBatisUtil.getSqlSession(); session.insert("board.saveFile",
	 * fileDetail); status = session.insert("board.saveFileDetail", fileDetail);
	 * 
	 * if(status > 0) { // 성공 session.commit(); } }catch(Exception e) {
	 * e.printStackTrace(); }finally { if(session != null) { session.close(); } }
	 * 
	 * return status; }
	 */

	@Override
	public FileDetailVO getFileDetail(int fileNo) {

		FileDetailVO fileDetail = null;

		SqlSession session = null;

		try {
			session = MyBatisUtil.getSqlSession(true);

			fileDetail = session.selectOne("board.getFileDetail", fileNo);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return fileDetail;
	}

	@Override
	public List<BoardVO> searchClassBoardList(Map<String, Object> parameter) {

		List<BoardVO> boardList = new ArrayList<BoardVO>();

		SqlSession session = null;

		try {
			session = MyBatisUtil.getSqlSession(true);
			boardList = session.selectList("board.searchClassBoardList", parameter);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return boardList;
	}

	@Override
	public int insertFileShare(FileShareVO fileShare) {
		SqlSession session = null;
		int status2 = 0;

		try {
			session = MyBatisUtil.getSqlSession();
			status2 = session.insert("board.insertFileShare", fileShare);

			if (status2 > 0) { // 성공
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return status2;
	}

	@Override
	public int insertBoardFile(FileDetailVO fileDetail) {

		SqlSession session = null;
		int status = 0;

		try {
			session = MyBatisUtil.getSqlSession();
			session.insert("board.saveFiles", fileDetail);
			status = session.insert("board.saveFileDetail", fileDetail);

			if (status > 0) { // 성공
				session.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return status;
	}

	@Override
	public FileDetailVO getFile(int boardNo) {

		FileDetailVO file = null;

		SqlSession session = null;

		try {
			session = MyBatisUtil.getSqlSession(true);

			file = session.selectOne("board.getFile", boardNo);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return file;
	}

	@Override
	public MemberVO getHwTeacher(int hwNo) {

		MemberVO mem = null;
		SqlSession session = null;

		try {
			session = MyBatisUtil.getSqlSession(true);

			mem = session.selectOne("board.getHwTeacher", hwNo);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return mem;
	}

	@Override
	public int studentSubmitHw(HwSubmitVO hwSubmit) {

		SqlSession session = null;
		int status = 0;

		try {

			session = MyBatisUtil.getSqlSession();
			status = session.insert("board.studentSubmitHw", hwSubmit);

			if (status > 0) { // 성공
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return status;
	}

	@Override
	public List<FileDetailVO> mySubmit(Map<String, Object> parameter2) {

		List<FileDetailVO> submit = null;
		SqlSession session = null;

		try {
			session = MyBatisUtil.getSqlSession(true);

			submit = session.selectList("board.mySubmit", parameter2);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return submit;
	}

	@Override
	public int insertStory(StoryVO storyVO) {

		SqlSession session = null;
		int status = 0;

		try {
			session = MyBatisUtil.getSqlSession();
			status = session.insert("board.insertStory", storyVO);

			if (status > 0) {// 성공
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return status;
	}

	@Override
	public List<StoryVO> getStoryList(int usersNo) {

		List<StoryVO> storyVo = null;
		SqlSession session = null;

		try {
			session = MyBatisUtil.getSqlSession(true);

			storyVo = session.selectList("board.getStoryList", usersNo);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return storyVo;
	}

	@Override
	public String getStoryTime(int storyNo) {

		String time = null;
		SqlSession session = null;

		try {
			session = MyBatisUtil.getSqlSession(true);
			time = session.selectOne("board.getStoryTime", storyNo);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return time;
	}

	@Override
	public StoryVO getStoryOne(int storyNo) {

		StoryVO storyOne = null;
		SqlSession session = null;

		try {
			session = MyBatisUtil.getSqlSession(true);

			storyOne = session.selectOne("board.getStoryOne", storyNo);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return storyOne;
	}

	@Override
	public MemberVO writerDetail(int boardNo) {
		SqlSession session = null; // 디비와 연결하기 위한 생산품
		MemberVO memberVO = null; // 전체 목록 데이터를 받을 변수

		try {
			session = MyBatisUtil.getSqlSession(true);
			memberVO = session.selectOne("board.writerDetail", boardNo);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return memberVO;
	}

	@Override
	public List<BoardVO> selectClassBoardList(int levelChk) {

		List<BoardVO> boardList = new ArrayList<BoardVO>();

		SqlSession session = null;

		try {
			session = MyBatisUtil.getSqlSession(true);
			boardList = session.selectList("board.selectClassBoardList", levelChk);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return boardList;
	}


   @Override
   public List<StoryVO> allStoryList() {

      List<StoryVO> allStoryList = new ArrayList<StoryVO>();

      SqlSession session = null;

      try {
         session = MyBatisUtil.getSqlSession(true);
         allStoryList = session.selectList("board.allStoryList");

      } catch (PersistenceException ex) {
         ex.printStackTrace();
      } finally {
         session.close();
      }
      return allStoryList;
   }
  
  @Override
	public List<BoardVO> getMemberBoardReply(int usersNo) {
		
		List<Integer> boardNoList = new ArrayList<Integer>();
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		int cnt = 0;
		BoardVO vo = null;
		SqlSession session = null;

		try {
			session = MyBatisUtil.getSqlSession(true);
			boardNoList = session.selectList("board.getMemberBoardReply", usersNo);
			for(Integer boardNo : boardNoList) {
				vo = session.selectOne("board.getMemberBoardOne", boardNo);
				if(vo != null) {
					boardList.add(vo);
					vo = null;
				}
			}
			
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
    return boardList;
	}

	@Override
	public List<FileDetailVO> getHwFileList(int hwNo) {
		List<FileDetailVO> hwFileList = null;
		SqlSession session = null;

		try {
			session = MyBatisUtil.getSqlSession(true);

			hwFileList = session.selectList("board.getHwFileList", hwNo);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return hwFileList;
	}

	@Override
	public MemberVO getHwSubmitMem(int fileNo) {
		SqlSession session = null;
		MemberVO mem = null;

		try {
			session = MyBatisUtil.getSqlSession(true);
			mem = session.selectOne("board.getHwSubmitMem", fileNo);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return mem;
	}

	@Override
	public int insertFiles() {
		SqlSession session = null;
		int status = 0;

		try {
			session = MyBatisUtil.getSqlSession();
			status = session.insert("board.insertFiles");

			if (status > 0) { // 성공
				session.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return status;
	}

	@Override
	public List<FilesVO> getFiles() {

		List<FilesVO> hwFileList = null;
		SqlSession session = null;

		try {
			session = MyBatisUtil.getSqlSession(true);

			hwFileList = session.selectList("board.getFiles");

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return hwFileList;
	}

	@Override
	public int saveFileDetail(FileDetailVO fileDetail) {
		SqlSession session = null;
		int status = 0;

		try {
			session = MyBatisUtil.getSqlSession();
			session.insert("board.saveFiles", fileDetail);
			status = session.insert("board.saveFileDetail", fileDetail);

			if (status > 0) { // 성공
				session.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return status;
	}

	@Override
	public List<BoardVO> myBoardList(int usersNo) {
		List<BoardVO> myBoardList = null;
		SqlSession session = null;

		try {
			session = MyBatisUtil.getSqlSession(true);

			myBoardList = session.selectList("board.myBoardList", usersNo);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return myBoardList;
  }

	@Override
	public int deleteHomework(int hwNo) {
		SqlSession session = null;
		int status = 0;

		try {
			session = MyBatisUtil.getSqlSession();
			status = session.update("board.deleteHomework", hwNo);

			if (status > 0) { // 성공
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return status;
	}
  
  @Override
  public int deleteStory(int storyNo) {

	  SqlSession session = null;
	  int cnt = 0;
	  try {
		  session = MyBatisUtil.getSqlSession();

		  cnt = session.update("board.deleteStory", storyNo);

		  if (cnt > 0) {
			  session.commit();
		  }
	  } catch (PersistenceException ex) {
		  ex.printStackTrace();
	  } finally {
		  session.close();
	  }
	  return cnt;
  }

	@Override
	public int editHomework(Map<String, Object> parameter) {
	
		SqlSession session = null;
		int status = 0;
	
		try {
			session = MyBatisUtil.getSqlSession();
			status = session.update("board.editHomework", parameter);
	
			if (status > 0) { // 성공
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	
		return status;
	}


	@Override
	public int updateStory(int storyNo) {
		SqlSession session = null;
		int status = 0;
		try {
			session = MyBatisUtil.getSqlSession();
	
			status = session.update("board.updateStory", storyNo);
	
			if (status > 0) {
				session.commit();
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return status;
	}
	
	@Override
	public int getUserRole(int usersNo) {
		int userRole = 0;

		SqlSession session = null;

		try {
			session = MyBatisUtil.getSqlSession(true);

			userRole = session.selectOne("board.getUserRole", usersNo);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return userRole;
	}
}
