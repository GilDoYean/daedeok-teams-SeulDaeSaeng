package kr.or.ddit.board.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.FileDetailVO;
import kr.or.ddit.board.vo.HomeworkVO;
import kr.or.ddit.board.vo.HwSubmitVO;
import kr.or.ddit.board.vo.ReplyVO;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/homework/detail.do")
public class HomeworkDetailController extends HttpServlet{
   
   private static final long serialVersionUID = 1L;
   IBoardService boardService = BoardServiceImpl.getInstance();
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
       int hwNo = Integer.parseInt(req.getParameter("hwNo"));
       int levelChk = Integer.parseInt(req.getParameter("levelChk"));
	   HttpSession session = req.getSession();
	   MemberVO memDetail = (MemberVO)session.getAttribute("memDetail");
       HomeworkVO hwDetail = boardService.getHwDetail(hwNo);
       
       int usersNo = hwDetail.getUsersNo();
       Map<String,Object> parameter = new HashMap<>();
       parameter.put("usersNo",usersNo);
       parameter.put("hwNo",hwNo);
       parameter.put("levelChk",levelChk);
       
       MemberVO writerDetail = boardService.getHwWriterDetail(parameter);
       req.setAttribute("hwDetail", hwDetail);
       req.setAttribute("writerDetail", writerDetail);
       req.setAttribute("levelChk", levelChk);
       
       Map<String,Object> parameter2 = new HashMap<>();
       parameter2.put("usersNo",memDetail.getUsersNo());
       parameter2.put("hwNo",hwNo);
       List<FileDetailVO> mySubmit = boardService.mySubmit(parameter2);
       req.setAttribute("mySubmit", mySubmit);
       
       req.getRequestDispatcher("/views/board/hwView.jsp").forward(req, resp);
   }
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   doGet(req, resp);
   }
}