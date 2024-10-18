package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Part;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.FileDetailVO;
import kr.or.ddit.board.vo.FileShareVO;
import kr.or.ddit.board.vo.FilesVO;
import kr.or.ddit.board.vo.HomeworkVO;
import kr.or.ddit.board.vo.HwSubmitVO;
import kr.or.ddit.board.vo.ReplyVO;
import kr.or.ddit.board.vo.StoryVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.UsersVO;

public class BoardServiceImpl implements IBoardService{
	
	private static final String UPLOAD_DIR = "upload_files";
	
    private final IBoardDao boardDao;
	
	private static IBoardService boardService = new BoardServiceImpl();
	
	public BoardServiceImpl() {
		boardDao = BoardDaoImpl.getInstance();
	}
	
	public static IBoardService getInstance() {
		return boardService;
	}

	@Override
	public List<BoardVO> allBoardList() {
		return boardDao.allBoardList();
	}

	@Override
	public BoardVO getBoardDetail(int boardNo) {
		return boardDao.getBoardDetail(boardNo);
	}

	@Override
	public List<BoardVO> selectBoardList(int level) {
		return boardDao.selectBoardList(level);
	}

	@Override
	public MemberVO getwriterDetail(Map<String,Object> parameter) {
		return boardDao.getwriterDetail(parameter);
	}

	@Override
	public List<ReplyVO> getReplyList(int boardNo) {
		return boardDao.getReplyList(boardNo);
	}

	@Override
	public MemberVO getReplyWriterDetail(Map<String,Object> parameter) {
		return boardDao.getReplyWriterDetail(parameter);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return boardDao.deleteBoard(boardNo);
	}

	@Override
	public int insertReply(ReplyVO replyVO) {
		return boardDao.insertReply(replyVO);
	}

	@Override
	public int insertBoard(BoardVO boardVO) {
		return boardDao.insertBoard(boardVO);
	}

	@Override
	public int updateHit(int boardNo) {
		return boardDao.updateHit(boardNo);
	}

	@Override
	public UsersVO getUsersDetail(int usersNo) {
		return boardDao.getUsersDetail(usersNo);
	}

	@Override
	public MemberVO getMemberDetail(int usersNo) {
		return boardDao.getMemberDetail(usersNo);
	}

	@Override
	public List<BoardVO> getClassNoticeList(int level) {
		return boardDao.getClassNoticeList(level);
  }
  
  @Override
	public int updateBoard(Map<String, Object> parameter) {
		return boardDao.updateBoard(parameter);
	}

	@Override
	public int updateReply(Map<String, Object> parameter) {
		return boardDao.updateReply(parameter);
	}

	@Override
	public int insertHomework(Map<String,Object> parameter) {
		return boardDao.insertHomework(parameter);
	}

	@Override
	public List<HomeworkVO> getHwList(int levelChk) {
		return boardDao.getHwList(levelChk);
	}

	@Override
	public HomeworkVO getHwDetail(int hwNo) {
		return boardDao.getHwDetail(hwNo);
	}

	@Override
	public MemberVO getHwWriterDetail(Map<String, Object> parameter) {
		return boardDao.getHwWriterDetail(parameter);
	}

	@Override
	public List<FileDetailVO> getFileList(int classNo) {
		return boardDao.getFileList(classNo);
	}

	@Override
	public int saveFileDetail(FileDetailVO fileDetail) {
		return boardDao.saveFileDetail(fileDetail);
	}

	@Override
	public FileDetailVO getFileDetail(int fileNo) {
		return boardDao.getFileDetail(fileNo);
	}
	
  @Override  
	public int deleteReply(int replyNo) {
		return boardDao.deleteReply(replyNo);
	}

	@Override
	public List<BoardVO> searchAllBoardList(Map<String, Object> parameter) {
		return boardDao.searchAllBoardList(parameter);
	}

	@Override
	public List<BoardVO> searchSelectBoardList(Map<String, Object> parameter) {
		return boardDao.searchSelectBoardList(parameter);
	}

	@Override
	public int reportBoard(int boardNo) {
		return boardDao.reportBoard(boardNo);
	}

	@Override
	public List<BoardVO> searchClassBoardList(Map<String, Object> parameter) {
		return boardDao.searchClassBoardList(parameter);
	}

	@Override
	public int insertFileShare(FileShareVO fileShare) {
		return boardDao.insertFileShare(fileShare);
	}

	@Override
	public int insertBoardFile(Part filePart, int levelChk) {

		String uploadPath = "d:/D_Other/" + UPLOAD_DIR;
        
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()) {
           uploadDir.mkdir();
        }
        FileDetailVO fileDetail = new FileDetailVO();

        	String fileName = filePart.getSubmittedFileName();

        	String orignFileName = fileName; //원본 파일명
        	long fileSize = filePart.getSize(); //파일 크기
        	String fileSavednm = ""; //저장 파읾명
        	String fileSavepath = ""; //저장 파일경로
        		
        	fileSavednm = UUID.randomUUID().toString().replace("-", "");
        		
        	fileSavepath = uploadPath + "/" + fileSavednm;
        	
        	try {
        		filePart.write(fileSavepath); //파일 업로드 처리
			} catch (IOException e) {
					e.printStackTrace();
			}  
        		
        	//확장명 추출
        	String fileExtension = orignFileName.lastIndexOf(".") < 0 ? 
        				"" : orignFileName.substring(orignFileName.lastIndexOf(".") + 1);
        	
        	fileDetail.setFileLevel(levelChk);
        	fileDetail.setFileExt(fileExtension);
        	fileDetail.setFileOgname(orignFileName);
        	fileDetail.setFileSavednm(fileSavednm);
        	fileDetail.setFileSavepath(fileSavepath);
        	fileDetail.setFileSize(fileSize);
        	

            return boardDao.insertBoardFile(fileDetail);
     }

	@Override
	public FileDetailVO getFile(int boardNo) {
		return boardDao.getFile(boardNo);
	}

	@Override
	public MemberVO getHwTeacher(int hwNo) {
		return boardDao.getHwTeacher(hwNo);
	}

	@Override
	public int studentSubmitHw(HwSubmitVO hwSubmit) {
		return boardDao.studentSubmitHw(hwSubmit);
	}

	@Override
	public List<FileDetailVO> mySubmit(Map<String, Object> parameter2) {
		return boardDao.mySubmit(parameter2);
	}

	@Override
	public List<BoardVO> selectClassBoardList(int levelChk) {
		return boardDao.selectClassBoardList(levelChk);
	}
  
	@Override
	public int insertStory(StoryVO storyVO) {
		return boardDao.insertStory(storyVO);
	}

	@Override
	public List<StoryVO> getStoryList(int usersNo) {
		return boardDao.getStoryList(usersNo);
	}

	@Override
	public String getStoryTime(int storyNo) {
		return boardDao.getStoryTime(storyNo);
	}

	@Override
	public StoryVO getStoryOne(int storyNo) {
		return boardDao.getStoryOne(storyNo);
  }
  
  @Override
	public MemberVO writerDetail(int boardNo) {
		return boardDao.writerDetail(boardNo);
	}

  @Override
   public int deleteStory(int storyNo) {
	 return boardDao.deleteStory(storyNo);
   }

  @Override
  public List<StoryVO> allStoryList() {
	return boardDao.allStoryList();
  }

	@Override
	public List<FileDetailVO> getHwFileList(int hwNo) {
		return boardDao.getHwFileList(hwNo);
	}
	
	@Override
	public MemberVO getHwSubmitMem(int fileNo) {
		return boardDao.getHwSubmitMem(fileNo);
	}
	
	@Override
	public int insertFiles() {
		return boardDao.insertFiles();
	}
	
	@Override
	public List<FilesVO> getFiles() {
		return boardDao.getFiles();
	}

	@Override
	public List<BoardVO> getMemberBoardReply(int usersNo) {
		return boardDao.getMemberBoardReply(usersNo);
	}

	@Override
	public List<BoardVO> myBoardList(int usersNo) {
		return boardDao.myBoardList(usersNo);
  }
  
	@Override
	public int deleteHomework(int hwNo) {
		return boardDao.deleteHomework(hwNo);
	}

	@Override
	public List<BoardVO> myCommentList(int usersNo) {
		return boardDao.myBoardList(usersNo);
  }
  
  @Override
	public int editHomework(Map<String, Object> parameter) {
		return boardDao.editHomework(parameter);
  }
  
  @Override
	public int updateStory(int storyNo) {
		return boardDao.updateStory(storyNo);
	}

	@Override
	public int getUserRole(int usersNo) {
		return boardDao.getUserRole(usersNo);
	}
  
}
