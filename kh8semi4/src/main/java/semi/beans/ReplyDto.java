package semi.beans;

import java.util.Date;

public class ReplyDto {
	private int no;
	private String memberId;
	private int productNo;
	private int statPoint;
	private String content;
	private Date time;
	
	public ReplyDto() {
		super();
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getStatPoint() {
		return statPoint;
	}

	public void setStatPoint(int statPoint) {
		this.statPoint = statPoint;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}