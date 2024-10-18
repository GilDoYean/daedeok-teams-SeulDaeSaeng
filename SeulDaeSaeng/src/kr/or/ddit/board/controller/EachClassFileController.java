package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.FileDetailVO;

@WebServlet("/file/fileList.do")
public class EachClassFileController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		 IBoardService service = BoardServiceImpl.getInstance();
		 
		 int levelChk = Integer.parseInt(req.getParameter("levelChk"));
		 List<FileDetailVO> fileList = service.getFileList(levelChk);
         req.setAttribute("fileList", fileList);
         req.setAttribute("levelChk", levelChk);
		 req.getRequestDispatcher("/views/board/fileList.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 doGet(req, resp);
	}
}
