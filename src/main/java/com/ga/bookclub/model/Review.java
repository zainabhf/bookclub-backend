package com.ga.bookclub.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.*;

@Entity
@Table(name = "review")
public class Review {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(length = 65535)
	private String reviewContent;

	//FK of user
	
	//FK of book
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	
}
