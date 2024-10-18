package kr.or.ddit.member.vo;

public class AddressVO {
	
	private int addrNo; /*  */
	private int addrPost; /*  */
	private String addrBasic; /*  */
	private String addrDetail; /*  */
	private String addrLon; /*  */
	private String addrLat; /*  */
	private int addrLevel; /* 1 - 회원 2 - 가게 */
	
	@Override
	public String toString() {
		return "AdressVO [addrNo=" + addrNo + ", addrPost=" + addrPost + ", addrBasic=" + addrBasic + ", addrDetail="
				+ addrDetail + ", addrLon=" + addrLon + ", addrLat=" + addrLat + ", addrLevel=" + addrLevel + "]";
	}
	public AddressVO() {
	}
	public int getAddrNo() {
		return addrNo;
	}
	public void setAddrNo(int addrNo) {
		this.addrNo = addrNo;
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
	public String getAddrLon() {
		return addrLon;
	}
	public void setAddrLon(String addrLon) {
		this.addrLon = addrLon;
	}
	public String getAddrLat() {
		return addrLat;
	}
	public void setAddrLat(String addrLat) {
		this.addrLat = addrLat;
	}
	public int getAddrLevel() {
		return addrLevel;
	}
	public void setAddrLevel(int addrLevel) {
		this.addrLevel = addrLevel;
	}
	
}
