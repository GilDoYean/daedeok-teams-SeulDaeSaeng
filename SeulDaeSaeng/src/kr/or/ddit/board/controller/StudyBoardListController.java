package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

@WebServlet("/studyBoard.do")
public class StudyBoardListController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	IBoardService boardService = BoardServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		int level = 2;
		
		List<BoardVO> boardList = null;
		String searchOption = null;
		if(req.getParameter("searchOption") == null) {
			searchOption = "all";
		}else {
			searchOption = req.getParameter("searchOption");
		}
		String searchText = req.getParameter("searchText");
		if (searchText != null && searchText != "") {
			Map<String, Object> parameter = new HashMap<>();
			parameter.put("boardLevel", level);
			parameter.put("searchText", searchText);
			parameter.put("searchOption", searchOption);
			req.setAttribute("searchText", searchText);
			
			boardList = boardService.searchSelectBoardList(parameter);
		}else {
			boardList = boardService.selectBoardList(level);
		}
		
		if (searchOption != null && searchOption != "") {
			req.setAttribute("searchOption", searchOption);
		}
		
		req.setAttribute("levelChk", level);
		req.setAttribute("boardList", boardList);
		
		req.getRequestDispatcher("/views/board/allBoard.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);

	}
}
