package kr.or.ddit.board.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

@WebServlet("/board/edit.do")
public class EditBoardController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private IBoardService service = BoardServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		int levelChk = Integer.parseInt(req.getParameter("levelChk"));
		int idx = Integer.parseInt(req.getParameter("idx"));
		IBoardService boardService = BoardServiceImpl.getInstance();
		BoardVO boardDetail = boardService.getBoardDetail(boardNo);
		
		String classBoardChk = req.getParameter("classBoardChk");
		if(classBoardChk!=null) {
			 req.setAttribute("classBoardChk", Integer.parseInt(classBoardChk));
		 }
		req.setAttribute("boardNo", boardNo);
		req.setAttribute("levelChk", levelChk);
		req.setAttribute("idx", idx);
		req.setAttribute("boardDetail", boardDetail);
		 
		req.getRequestDispatcher("/views/board/edit.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		int levelChk = Integer.parseInt(req.getParameter("levelChk"));
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));		
		int idx = Integer.parseInt(req.getParameter("idx"));
		int classBoardChk = 0;
		if(req.getParameter("classBoardChk")!=null) {
			classBoardChk = Integer.parseInt(req.getParameter("classBoardChk"));
		}
		
		Map<String,Object> parameter = new HashMap<>();
		parameter.put("boardNo", boardNo);
		parameter.put("boardTitle", title);
		parameter.put("boardCon", content);
		
		int status = service.updateBoard(parameter);
		if(status > 0) { 	// 성공
			String msg = "정상적으로 수정되었습니다.";
			req.getSession().setAttribute("msg", msg);
			if(classBoardChk != 0) {
				resp.sendRedirect(req.getContextPath() + "/board/detail.do?boardNo=" + boardNo + "&idx=" + idx + "&levelChk=" + levelChk
						+ "&classBoardChk=" + classBoardChk);
			}else {
				resp.sendRedirect(req.getContextPath() + "/board/detail.do?boardNo=" + boardNo + "&idx=" + idx + "&levelChk=" + levelChk);
			}
		}else {				// 실패
			String msg = "수정 실패했습니다.";
			req.getSession().setAttribute("msg", msg);
			if(classBoardChk != 0) {
				resp.sendRedirect(req.getContextPath() + "/board/detail.do?boardNo=" + boardNo + "&idx=" + idx + "&levelChk=" + levelChk
						+ "&classBoardChk=" + classBoardChk);
			}else {
				resp.sendRedirect(req.getContextPath() + "/board/detail.do?boardNo=" + boardNo + "&idx=" + idx + "&levelChk=" + levelChk);
			}
		}
	}
}
