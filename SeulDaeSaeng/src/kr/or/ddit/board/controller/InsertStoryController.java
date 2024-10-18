package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.FileDetailVO;
import kr.or.ddit.board.vo.StoryVO;
import kr.or.ddit.member.vo.MemberVO;

@MultipartConfig
@WebServlet("/story.do")
public class InsertStoryController extends HttpServlet {
 
	private IBoardService service = BoardServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/board/storyWrite.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		MemberVO memDetail = (MemberVO) session.getAttribute("memDetail");
		StoryVO storyVO = new StoryVO();

		int levelChk = 77; //스토리 file_level=77
		String title = req.getParameter("title");
		Part filePart = req.getPart("file");
		
	    int fileStatus = service.insertBoardFile(filePart,levelChk);
		List<FileDetailVO> fileList = service.getFileList(levelChk);
		req.setAttribute("fileList", fileList);
		storyVO.setFileNo(fileList.get(0).getFileNo());
		storyVO.setStoryCon(title);
		storyVO.setUsersNo(memDetail.getUsersNo());
		
		int status = service.insertStory(storyVO);
		List<StoryVO> storyVo = service.getStoryList(memDetail.getUsersNo());
		req.setAttribute("storyVo", storyVo);
		
		if (status > 0) { 
			resp.sendRedirect(req.getContextPath() + "/member/myPageHome.do");
		}
	}
}