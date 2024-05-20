package com.web.internship_api.services;

import java.util.List;
import java.util.Optional;

import com.web.internship_api.entities.Account;
import com.web.internship_api.models.AccountModel;

public interface AccountService {
	public List<Account> findAll();
	public Optional<Account> findById(int id);
	public Optional<Account> findAccountByUserName(String username);
	public Account createAccount(Account account,String role);
	public Account updateAccount(AccountModel account);
	public Account deleteAccount (Integer id);
	public void forgetPassword(Account acc);
}
