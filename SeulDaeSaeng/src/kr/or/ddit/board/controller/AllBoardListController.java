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

@WebServlet("/allBoard.do")
public class AllBoardListController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IBoardService boardService = BoardServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int level = 0;
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
			parameter.put("searchText", searchText);
			parameter.put("searchOption", searchOption);
			req.setAttribute("searchText", searchText);
			
			boardList = boardService.searchAllBoardList(parameter);
		}else {
			boardList = boardService.allBoardList();
		}
		
		if (searchOption != null && searchOption != "") {
			req.setAttribute("searchOption", searchOption);
		}
		// levelChk는 detail 진입 전에 어느 목록에 있었는지 저장(전체, 자유 등)
		req.setAttribute("levelChk", level);
		req.setAttribute("boardList", boardList);

		req.getRequestDispatcher("/views/board/allBoard.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);

	}
}