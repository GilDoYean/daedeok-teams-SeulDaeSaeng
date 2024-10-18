package kr.or.ddit.restaurant.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.FileDetailVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.UsersVO;
import kr.or.ddit.restaurant.vo.RestaurantVO;
import kr.or.ddit.restaurant.vo.ReviewVO;
import kr.or.ddit.restaurant.vo.restLikeVO;

public interface IRestaurantDAO {
	public List<RestaurantVO> selectRestaurantType(Map<String, Object> cls);
	public int selectRestaurantTypeTotal(Map<String, Object> cls);
	public String selectMclsName(Map<String, Object> cls);
	public String selectSclsName(Map<String, Object> cls);
	public List<RestaurantVO> selectMclsList();
	public List<RestaurantVO> selectSclsList();
	public List<RestaurantVO> selectSclsList(String mcls);
	public RestaurantVO selectRest(String bizno);
	public List<RestaurantVO> selectRestReview(String restBizno);
	public List<RestaurantVO> selectRestReviewAll(Map<String, Object> cls);
	public List<FileDetailVO> getFileList();
	public int insertFile(FileDetailVO fileDetail);
	public int insertReview(ReviewVO reviewVO);
	public int deleteReview(int reviewNo);
	public List<MemberVO> getLikeMemList();
	public List<RestaurantVO> getMyLikeList(int usersNo);
	public int insertMyLike(Map<String, Object> likeInfo);
	public int updateMyLike(Map<String, Object> likeInfo);
	public List<restLikeVO> restLikeList(String restBizno);
	public List<RestaurantVO> restReviewList();
  public List<RestaurantVO> getReviewsByUserId(int usersNo);
}
