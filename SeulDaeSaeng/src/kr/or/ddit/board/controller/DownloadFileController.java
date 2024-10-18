package kr.or.ddit.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.FileDetailVO;

@MultipartConfig
@WebServlet("/file/download.do")
public class DownloadFileController extends HttpServlet {
	
    private IBoardService service = BoardServiceImpl.getInstance();
	private static final String UPLOAD_DIR = "D:/D_Other/upload_files";
    
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int fileNo = Integer.parseInt(req.getParameter("fileNo"));
		
		
		FileDetailVO fileDetail = service.getFileDetail(fileNo);
		
		/*
		   파일 다운로드 처리륾 위한 Content-Disposition 헤더 설정하기
		   
		  Content-Disposition : inline(default)
		  Content-Disposition : attachment
		  Content-Disposition : attachment;filename = "abc.jpg" //해당이름으로 다운로드
		 
		 */
		
		//파일 다운로드 처리를 위한 응답헤더 정보 설정하기
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename=\""
		+ URLEncoder.encode(fileDetail.getFileOgname(),"UTF-8").replace("\\+", "%20") + "\"");
		
		BufferedInputStream bis = 
				new BufferedInputStream(new FileInputStream(fileDetail.getFileSavepath()));
		
		BufferedOutputStream bos = 
				new BufferedOutputStream(resp.getOutputStream());
		
		int data = 0 ;
		while((data = bis.read()) != -1) {
			bos.write(data);
		}
		
		bis.close();
		bos.close();
	}
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doGet(req, resp);
    }
}
