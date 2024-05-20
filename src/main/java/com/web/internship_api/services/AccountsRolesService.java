package com.web.internship_api.services;

import com.web.internship_api.entities.Account;
import com.web.internship_api.entities.AccountsRole;
import com.web.internship_api.entities.Role;

public interface AccountsRolesService {
	AccountsRole createAccountsRole(Account acc , Role role);
	AccountsRole updateAccountsRole(AccountsRole accRole);
}
