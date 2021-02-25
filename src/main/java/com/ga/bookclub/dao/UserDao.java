package com.ga.bookclub.dao;

import org.springframework.data.repository.CrudRepository;

import com.ga.bookclub.model.User;

public interface UserDao extends CrudRepository<User, Integer> {
	public User findById(int id);

	public User findByEmailAddress(String emailAddress);

}


 