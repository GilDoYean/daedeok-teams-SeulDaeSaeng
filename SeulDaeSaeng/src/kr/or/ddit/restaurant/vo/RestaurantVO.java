package kr.or.ddit.restaurant.vo;

import java.time.LocalDate;

public class RestaurantVO {

	private int addrNo;
	private String restBizno;
	private String name;
	private String mcls;
	private String scls;
	private String mclsName;
	private String sclsName;
	private int addrPost;
	private String addrBasic;
	private String addrDetail;
	private String lon;
	private String lat;
	private int reviewNo;
	private int usersNo;
	private String bizNo;
	private int reviewStar;
	private String reviewText;
	private String reviewYn;
	private int fileNo;
	private String fileSavepath;
	private String fileOgname;
	private String fileSavednm;
	private int fileSize;
	private String fileExt;
	private String fileDt;
	private String fileYn;
	private int likeCount;
	private int avgReviewStar;
	private int firstpost;
	private int postperpage;
	private int distance;	
	private int totalReview;
	private String nickName;	
	private LocalDate reviewDate;
	
	public int getAddrNo() {
		return addrNo;
	}
	public void setAddrNo(int addrNo) {
		this.addrNo = addrNo;
	}
	public String getRestBizno() {
		return restBizno;
	}
	public void setRestBizno(String restBizno) {
		this.restBizno = restBizno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMcls() {
		return mcls;
	}
	public void setMcls(String mcls) {
		this.mcls = mcls;
	}
	public String getScls() {
		return scls;
	}
	public void setScls(String scls) {
		this.scls = scls;
	}
	public String getMclsName() {
		return mclsName;
	}
	public void setMclsName(String mclsName) {
		this.mclsName = mclsName;
	}
	public String getSclsName() {
		return sclsName;
	}
	public void setSclsName(String sclsName) {
		this.sclsName = sclsName;
	}
	public int getAddrPost() {
		return addrPost;
	}
	public void setAddrPost(int addrPost) {
		this.addrPost = addrPost;
	}
	public String getAddrBasic() {
		return addrBasic;
	}
	public void setAddrBasic(String addrBasic) {
		this.addrBasic = addrBasic;
	}
	public String getAddrDetail() {
		return addrDetail;
	}
	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public int getUsersNo() {
		return usersNo;
	}
	public void setUsersNo(int usersNo) {
		this.usersNo = usersNo;
	}
	public String getBizNo() {
		return bizNo;
	}
	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}
	public int getReviewStar() {
		return reviewStar;
	}
	public void setReviewStar(int reviewStar) {
		this.reviewStar = reviewStar;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	public String getReviewYn() {
		return reviewYn;
	}
	public void setReviewYn(String reviewYn) {
		this.reviewYn = reviewYn;
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getFileSavepath() {
		return fileSavepath;
	}
	public void setFileSavepath(String fileSavepath) {
		this.fileSavepath = fileSavepath;
	}
	public String getFileOgname() {
		return fileOgname;
	}
	public void setFileOgname(String fileOgname) {
		this.fileOgname = fileOgname;
	}
	public String getFileSavednm() {
		return fileSavednm;
	}
	public void setFileSavednm(String fileSavednm) {
		this.fileSavednm = fileSavednm;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	public String getFileDt() {
		return fileDt;
	}
	public void setFileDt(String fileDt) {
		this.fileDt = fileDt;
	}
	public String getFileYn() {
		return fileYn;
	}
	public void setFileYn(String fileYn) {
		this.fileYn = fileYn;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getAvgReviewStar() {
		return avgReviewStar;
	}
	public void setAvgReviewStar(int avgReviewStar) {
		this.avgReviewStar = avgReviewStar;
	}
	public int getFirstpost() {
		return firstpost;
	}
	public void setFirstpost(int firstpost) {
		this.firstpost = firstpost;
	}
	public int getPostperpage() {
		return postperpage;
	}
	public void setPostperpage(int postperpage) {
		this.postperpage = postperpage;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	@Override
	public String toString() {
		return "RestaurantVO [addrNo=" + addrNo + ", restBizno=" + restBizno + ", name=" + name + ", mcls=" + mcls
				+ ", scls=" + scls + ", mclsName=" + mclsName + ", sclsName=" + sclsName + ", addrPost=" + addrPost
				+ ", addrBasic=" + addrBasic + ", addrDetail=" + addrDetail + ", lon=" + lon + ", lat=" + lat
				+ ", reviewNo=" + reviewNo + ", usersNo=" + usersNo + ", bizNo=" + bizNo + ", reviewStar=" + reviewStar
				+ ", reviewText=" + reviewText + ", reviewYn=" + reviewYn + ", fileNo=" + fileNo + ", fileSavepath="
				+ fileSavepath + ", fileOgname=" + fileOgname + ", fileSavednm=" + fileSavednm + ", fileSize="
				+ fileSize + ", fileExt=" + fileExt + ", fileDt=" + fileDt + ", fileYn=" + fileYn + ", likeCount="
				+ likeCount + ", avgReviewStar=" + avgReviewStar + ", firstpost=" + firstpost + ", postperpage="
				+ postperpage + "]";
	}
	public int getTotalReview() {
		return totalReview;
	}
	public void setTotalReview(int totalReview) {
		this.totalReview = totalReview;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public LocalDate getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(LocalDate reviewDate) {
		this.reviewDate = reviewDate;
	}
	
}
