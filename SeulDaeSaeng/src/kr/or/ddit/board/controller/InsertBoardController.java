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
import kr.or.ddit.member.vo.MemberVO;

@MultipartConfig
@WebServlet("/board/write.do")
public class InsertBoardController extends HttpServlet {

	private IBoardService service = BoardServiceImpl.getInstance();
	private static final String UPLOAD_DIR = "upload_files";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int levelChk = Integer.parseInt(req.getParameter("levelChk"));
		String classBoardChk = req.getParameter("classBoardChk");
		if(classBoardChk!=null) {
			 req.setAttribute("classBoardChk", Integer.parseInt(classBoardChk));
		 }
		req.setAttribute("levelChk", levelChk);
		req.getRequestDispatcher("/views/board/write.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		BoardVO boardVO = new BoardVO();
		HttpSession session = req.getSession();
		int usersNo = (int) session.getAttribute("usersNo");
		
		int levelChk = Integer.parseInt(req.getParameter("levelChk"));
		int classBoardChk = 0;
		if(req.getParameter("classBoardChk")!=null) {
			classBoardChk = Integer.parseInt(req.getParameter("classBoardChk"));
		}
		int level = Integer.parseInt(req.getParameter("level"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		int idx = Integer.parseInt(req.getParameter("idx"));
		Part filePart = req.getPart("file");
		
		
		if(filePart != null && filePart.getSize() != 0) {
			 int fileStatus = service.insertBoardFile(filePart,levelChk);
			 List<FileDetailVO> fileList = service.getFileList(levelChk);
			 req.setAttribute("fileList", fileList);
		     boardVO.setFileNo(fileList.get(0).getFileNo());
		}
		
		boardVO.setBoardTitle(title);
		boardVO.setBoardCon(content);
		boardVO.setUsersNo(usersNo);
		boardVO.setBoardLevel(level);

		int status = service.insertBoard(boardVO);
		if (status > 0) { // 성공
			if(classBoardChk != 0) {
				resp.sendRedirect(req.getContextPath() + "/board/detail.do?boardNo=" + boardVO.getBoardNo() + "&idx=" + idx
						+ "&levelChk=" + levelChk + "&classBoardChk=" + classBoardChk);
			}else {
				resp.sendRedirect(req.getContextPath() + "/board/detail.do?boardNo=" + boardVO.getBoardNo() + "&idx=" + idx
						+ "&levelChk=" + levelChk);
			}
		} else { // 실패
			String msg = "작성 실패했습니다.";
			 req.getSession().setAttribute("msg", msg);
			 if(levelChk == 0) resp.sendRedirect(req.getContextPath() + "/allBoard.do");
			 else if(levelChk == 1) resp.sendRedirect(req.getContextPath() + "/freeBoard.do");
			 else if(levelChk == 2) resp.sendRedirect(req.getContextPath() + "/studyBoard.do");
			 else if(levelChk == 3) resp.sendRedirect(req.getContextPath() + "/noticeBoard.do");
			 else if(levelChk > 300) {
				 if(classBoardChk == 1) {
					 resp.sendRedirect(req.getContextPath() + "/eachClassNotice.do?levelChk=" + levelChk);
				 } else if(classBoardChk == 2) {
					 resp.sendRedirect(req.getContextPath() + "/eachClassBoard.do?levelChk=" + levelChk);
				 }
			 }
		}
	}
}