package com.web.internship_api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.internship_api.entities.Account;


public interface AccountRepository extends JpaRepository<Account, Integer>{
	public List<Account> findByDeleted(byte deleted);
	public Optional<Account> findByIdAndDeleted(int id, byte deleted);
	public Optional<Account> findByUsernameAndDeleted(String username, byte deleted);
	public Account findByUsername(String username);
}
