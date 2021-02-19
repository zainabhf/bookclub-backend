package com.ga.bookclub.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="Book")
public class Book {
	
	@Id
	@GeneratedValue
	private int id;
	
private String BookName ;
	@Column(length = 6)
	 private int numberOfpages ; 
	
	private String authorName;
	
	  private String  category ;
	  
	  
	  @Column(length = 255)
	  private String description;
	  
		@Column(length = 255)
		  private String image;
		  private Date publish ;
		  
		  @OneToMany(mappedBy="book")
		  private Set<Review> review_book;
		  
	  public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumberOfpages() {
		return numberOfpages;
	}

	public void setNumberOfpages(int numberOfpages) {
		this.numberOfpages = numberOfpages;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getPublish() {
		return publish;
	}

	public void setPublish(Date publish) {
		this.publish = publish;
	}

	public Set<Review> getReview_book() {
		return review_book;
	}

	public void setReview_book(Set<Review> review_book) {
		this.review_book = review_book;
	}

	public String getBookName() {
		return BookName;
	}

	public void setBookName(String bookName) {
		BookName = bookName;
	}




		
	  
	 
}

