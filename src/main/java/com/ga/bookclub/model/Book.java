package com.ga.bookclub.model;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name="Book")
public class Book {
	
	@Id
	@GeneratedValue
	private int id;
	

	@Column(length = 6)
	 private int numberOfpages ; 
	
	private String authorName;
	
	  private String  category ;
	  
	  
	  @Column(length = 255)
	  private String description;
	  
	  @Column(length = 255)
	  private String image;
	  private Date publish ;
	  
//	  @OneToMany(mappedBy="book")
//	  private Set<Veiw> Veiwes;
		
	  
	 
}

