package com.web.internship_api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.internship_api.entities.Account;
import com.web.internship_api.entities.Role;
import com.web.internship_api.models.AccountModel;
import com.web.internship_api.repositories.AccountRepository;
import com.web.internship_api.services.AccountService;
import com.web.internship_api.services.AccountsRolesService;
import com.web.internship_api.services.RoleService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	AccountsRolesService accountsRolesService;
	@Autowired
	RoleService roleService;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public List<Account> findAll() {
		List<Account> accounts = accountRepository.findByDeleted((byte)0);
		return accounts;
	}

	@Override
	public Optional<Account> findAccountByUserName(String username) {
		Optional<Account> account = accountRepository.findByUsernameAndDeleted(username, (byte) 0);
		return account ;
	}

	@Override
	public Account createAccount(Account account,String rolename) {
		Account  acc = accountRepository.save(account);
		Account  findAccount = accountRepository.findByUsername(account.getUsername());
		Role role = roleService.findByName(rolename).get();
		accountsRolesService.createAccountsRole(findAccount, role);
		return acc;
	}

	@Override
	public Account updateAccount(AccountModel accountModel) {
		Optional<Account>  acc = accountRepository.findByUsernameAndDeleted(accountModel.getUsername(),(byte) 0);
		if(acc.isPresent()) {
			if(passwordEncoder.matches(accountModel.getPassword(),acc.get().getPassword())) {
				acc.get().setPassword(passwordEncoder.encode(accountModel.getNewPassword()));
				return accountRepository.save(acc.get());
			}
		}
		return null;
	}

	@Override
	public Account deleteAccount(Integer id) {
		Optional<Account>  acc = accountRepository.findByIdAndDeleted(id, (byte) 0);
		if(acc.isPresent()) {
			acc.get().setDeleted((byte)1);
			return accountRepository.save(acc.get());
		}
		return null;
	}

	@Override
	public Optional<Account> findById(int id) {
		return accountRepository.findByIdAndDeleted(id, (byte)0);
	}

	@Override
	public void forgetPassword(Account acc) {
		accountRepository.save(acc);
	}

}
