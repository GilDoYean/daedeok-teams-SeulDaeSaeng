package kr.or.ddit.restaurant.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Part;

import org.apache.logging.log4j.CloseableThreadContext.Instance;
import org.apache.logging.log4j.core.net.DatagramOutputStream;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.vo.FileDetailVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.UsersVO;
import kr.or.ddit.restaurant.dao.IRestaurantDAO;
import kr.or.ddit.restaurant.dao.RestaurantDAOImpl;
import kr.or.ddit.restaurant.vo.RestaurantVO;
import kr.or.ddit.restaurant.vo.ReviewVO;
import kr.or.ddit.restaurant.vo.restLikeVO;

public class RestaurantServiceImpl implements IRestaurantService {
	
	private static final String UPLOAD_DIR = "upload_files";
	
	private static RestaurantServiceImpl instance = null;
	
	private RestaurantServiceImpl() {}
	
	public static RestaurantServiceImpl getInstance() {
		if(instance == null) {
			instance = new RestaurantServiceImpl();
		}
		return instance;
	}
	
	private IRestaurantDAO dao = RestaurantDAOImpl.getInstance();

	@Override
	public List<RestaurantVO> selectRestaurantType(Map<String, Object> cls) {
		return dao.selectRestaurantType(cls);
	}

	@Override
	public int selectRestaurantTypeTotal(Map<String, Object> cls) {
		return dao.selectRestaurantTypeTotal(cls);
	}

	@Override
	public String selectMclsName(Map<String, Object> cls) {
		return dao.selectMclsName(cls);
	}

	@Override
	public String selectSclsName(Map<String, Object> cls) {
		return dao.selectSclsName(cls);
	}

	@Override
	public List<RestaurantVO> selectMclsList() {
		return dao.selectMclsList();
	}

	@Override
	public List<RestaurantVO> selectSclsList(String mcls) {
		return dao.selectSclsList(mcls);
	}

	@Override
	public List<RestaurantVO> selectSclsList() {
		return dao.selectSclsList();
	}

	@Override
	public RestaurantVO selectRest(String restBizno) {
		return dao.selectRest(restBizno);
	}

	@Override
	public List<RestaurantVO> selectRestReview(String restBizno) {
		return dao.selectRestReview(restBizno);
	}

	@Override
	public List<RestaurantVO> selectRestReviewAll(Map<String, Object> cls) {
		return dao.selectRestReviewAll(cls);
	}

	@Override
	public List<FileDetailVO> getFileList() {
		return dao.getFileList();
	}
	
	@Override
	public int insertFile(Part filePart) {
        
//		String uploadPath = "d:/D_Other/" + UPLOAD_DIR;
		String uploadPath = "D:/A_TeachingMaterial/999_project/SeulDaeSaeng/SualDaeSaeng/WebContent/img/upload_files";
//		String uploadPath = "C:/Users/jyj96/git/SeulDaeSaeng/SeulDaeSaeng/SualDaeSaeng/WebContent/img/upload_files";
        
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()) {
           uploadDir.mkdir();
        }
        FileDetailVO fileDetail = new FileDetailVO();

    	String fileName = filePart.getSubmittedFileName();

    	String orignFileName = fileName; //원본 파일명
    	long fileSize = filePart.getSize(); //파일 크기
    	String fileSavednm = ""; //저장 파읾명
    	String fileSavepath = ""; //저장 파일경로
    		
    	fileSavednm = UUID.randomUUID().toString().replace("-", "");
    		

    	//확장명 추출
    	String fileExtension = orignFileName.lastIndexOf(".") < 0 ? 
    				"" : orignFileName.substring(orignFileName.lastIndexOf(".") + 1);
    	
    	fileSavepath = uploadPath + "/" + fileSavednm+"."+fileExtension;
    	
    	try {
    		filePart.write(fileSavepath); //파일 업로드 처리
		} catch (IOException e) {
				e.printStackTrace();
		}  
    		
    		
    	fileDetail.setFileLevel(5);
    	fileDetail.setFileExt(fileExtension);
    	fileDetail.setFileOgname(orignFileName);
    	fileDetail.setFileSavednm(fileSavednm);
    	fileDetail.setFileSavepath("/img/upload_files/"+fileSavednm+"."+fileExtension);
    	fileDetail.setFileSize(fileSize);
        	
        return dao.insertFile(fileDetail);
     }

	@Override
	public int insertReview(ReviewVO reviewVO) {
		return dao.insertReview(reviewVO);
	}

	@Override
	public int deleteReview(int reviewNo) {
		return dao.deleteReview(reviewNo);
	}

	@Override
	public List<MemberVO> getLikeMemList() {
		return dao.getLikeMemList();
	}

	@Override
	public List<RestaurantVO> getMyLikeList(int usersNo) {
		return dao.getMyLikeList(usersNo);
  }

 @Override
	public List<RestaurantVO> getReviewsByUserId(int usersNo) {
		return dao.getReviewsByUserId(usersNo);
  }
  
  @Override
	public int insertMyLike(Map<String, Object> likeInfo) {
		return dao.insertMyLike(likeInfo);		
	}

	@Override
	public int updateMyLike(Map<String, Object> likeInfo) {
		return dao.updateMyLike(likeInfo);				
	}

	@Override
	public List<restLikeVO> restLikeList(String restBizno) {
		return dao.restLikeList(restBizno);				
	}

	@Override
	public List<RestaurantVO> restReviewList() {
		return dao.restReviewList();		
	}
	
}
