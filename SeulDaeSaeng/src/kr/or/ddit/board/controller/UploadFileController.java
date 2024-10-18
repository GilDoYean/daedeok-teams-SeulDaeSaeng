package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.FileDetailVO;
import kr.or.ddit.board.vo.FileShareVO;
import kr.or.ddit.board.vo.FilesVO;
import kr.or.ddit.board.vo.HwSubmitVO;
import kr.or.ddit.member.vo.MemberVO;

@MultipartConfig
@WebServlet("/file/upload.do")
public class UploadFileController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private IBoardService service = BoardServiceImpl.getInstance();
    private static final String UPLOAD_DIR = "upload_files";
   
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          doPost(req,resp);
	}
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
		req.setCharacterEncoding("UTF-8");
    	HttpSession session = req.getSession();
        
		int usersRole = (int)session.getAttribute("usersRole");
        MemberVO memDetail = (MemberVO)session.getAttribute("memDetail");
        int levelChk = Integer.parseInt(req.getParameter("levelChk"));
        int hwNo = 0;
    	if(usersRole==2) {
           hwNo = Integer.parseInt(req.getParameter("hwNo"));
    	}
        
		Collection<Part> parts = req.getParts();
		
        String uploadPath = "d:/D_Other/" + UPLOAD_DIR;
        
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()) {
           uploadDir.mkdir();
        }

        for(Part part : parts) {
        	
			/*
			 * int status3 = service.insertFiles(); List<FilesVO> list = service.getFiles();
			 */
        	
        	String fileName = part.getSubmittedFileName();

        	String orignFileName = fileName; //원본 파일명
        	long fileSize = part.getSize(); //파일 크기
        	String fileSavednm = ""; //저장 파읾명
        	String fileSavepath = ""; //저장 파일경로
        		
        	fileSavednm = UUID.randomUUID().toString().replace("-", "");
        		
        	fileSavepath = uploadPath + "/" + fileSavednm;
        	
        	try {
					part.write(fileSavepath); //파일 업로드 처리
			} catch (IOException e) {
					e.printStackTrace();
			}  
        		
        	//확장명 추출
        	String fileExtension = orignFileName.lastIndexOf(".") < 0 ? 
        				"" : orignFileName.substring(orignFileName.lastIndexOf(".") + 1);
        		
        	FileDetailVO fileDetail = new FileDetailVO();
        	fileDetail.setFileLevel(levelChk);
        	fileDetail.setFileExt(fileExtension);
        	fileDetail.setFileOgname(orignFileName);
        	fileDetail.setFileSavednm(fileSavednm);
        	fileDetail.setFileSavepath(fileSavepath);
        	fileDetail.setFileSize(fileSize);
        	
    		int status = service.saveFileDetail(fileDetail);
    		
    		List<FileDetailVO> fileList = service.getFileList(levelChk);
    		req.setAttribute("fileList", fileList);
    		System.out.println(fileList.get(0).getFileNo());
    		
        	if(usersRole==2) {
        		HwSubmitVO hwSubmit = new HwSubmitVO();
        		hwSubmit.setFileNo(fileList.get(0).getFileNo());
        		hwSubmit.setHwNo(hwNo);
        		hwSubmit.setUsersNo(memDetail.getUsersNo());
        		int submit = service.studentSubmitHw(hwSubmit);
        	}

			if(levelChk>300 && usersRole==1) {
				 FileShareVO fileShare = new FileShareVO();
			     fileShare.setFileNo(fileList.get(0).getFileNo());
			     fileShare.setUsersNo(memDetail.getUsersNo());
			     int status2 = service.insertFileShare(fileShare); 
			}

        }
        
        if(usersRole==1) {
            resp.sendRedirect(req.getContextPath() +"/classTeacherBoard.do?levelChk=" + levelChk);
        }else if(usersRole==2) {
        	resp.sendRedirect(req.getContextPath() +"/homework/detail.do?levelChk=" + levelChk + "&hwNo=" + hwNo);
        }
    }
}
