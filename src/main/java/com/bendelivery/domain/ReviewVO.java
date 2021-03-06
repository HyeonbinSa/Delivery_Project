package com.bendelivery.domain;

import java.util.Date;

public class ReviewVO {
	private int review_no;			//리뷰 번호 
	private int star;				//별점 
	private String review_content;  // 리뷰 내용 
	private int member_no;			// 회원 번호 
	private int res_no;				// 식당 번호 
	private int order_no;			// 오더 번호
	private Date reg_date;		 	// 댓글 등록일
	private String res_name;		// 식당명 
	private String member_nickname;	// 닉네임
	private String review_img;		// 리뷰 이미지 
	private String reply_status;	// 답변 여부
	private Date modify_date; 		// 리뷰 수정 
	
	
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public String getReply_status() {
		return reply_status;
	}
	public void setReply_status(String reply_status) {
		this.reply_status = reply_status;
	}
	public String getMember_nickname() {
		return member_nickname;
	}
	public String getReview_img() {
		return review_img;
	}
	public void setReview_img(String review_img) {
		this.review_img = review_img;
	}
	public void setMember_nickname(String member_nickname) {
		this.member_nickname = member_nickname;
	}
	public String getRes_name() {
		return res_name;
	}
	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}
	public int getReview_no() {
		return review_no;
	}
	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public int getRes_no() {
		return res_no;
	}
	public void setRes_no(int res_no) {
		this.res_no = res_no;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	@Override
	public String toString() {
		return "ReviewVO [review_no=" + review_no + ", star=" + star + ", review_content=" + review_content
				+ ", member_no=" + member_no + ", res_no=" + res_no + ", order_no=" + order_no + ", reg_date="
				+ reg_date + ", res_name=" + res_name + ", member_nickname=" + member_nickname + "]";
	}
	
	
	
}
