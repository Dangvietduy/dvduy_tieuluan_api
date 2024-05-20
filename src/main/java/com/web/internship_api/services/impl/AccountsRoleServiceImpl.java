package com.web.internship_api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.internship_api.entities.Account;
import com.web.internship_api.entities.AccountsRole;
import com.web.internship_api.entities.Role;
import com.web.internship_api.repositories.AccountsRolesReposiroty;
import com.web.internship_api.services.AccountsRolesService;

@Service
public class AccountsRoleServiceImpl implements AccountsRolesService{
	@Autowired
	AccountsRolesReposiroty accountsRolesReposiroty;
	@Override
	public AccountsRole createAccountsRole(Account acc, Role role) {
		AccountsRole accountRole = new AccountsRole();
		accountRole.setAccount(acc);
		accountRole.setRole(role);
		return accountsRolesReposiroty.save(accountRole);
	}

	@Override
	public AccountsRole updateAccountsRole(AccountsRole accRole) {
		// TODO Auto-generated method stub
		return null;
	}

}
