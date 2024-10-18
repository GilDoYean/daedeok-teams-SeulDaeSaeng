package kr.or.ddit.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;

@WebServlet("/displayImg.do")
public class DisplayImageController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IBoardService boardService = BoardServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 // 실제 파일 경로를 동적으로 처리
        String filePath = getServletContext().getRealPath("D:/D_Other/upload_files/test.jpg");
        File imageFile = new File(filePath);
        
        // 이미지 파일 존재 여부 확인
        if (!imageFile.exists()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 에러 반환
            return;
        }

        try (FileInputStream fis = new FileInputStream(imageFile);
             OutputStream out = resp.getOutputStream()) {
             
            resp.setContentType("image/jpeg");
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 에러 반환
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
