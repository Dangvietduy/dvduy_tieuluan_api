package com.web.internship_api.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.web.internship_api.entities.Account;
import com.web.internship_api.entities.AccountsRole;
import com.web.internship_api.entities.Role;
import com.web.internship_api.repositories.AccountRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	@Autowired
	private AccountRepository accountRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> accountOptinal = accountRepository.findByUsernameAndDeleted(username, (byte)0);
		if(accountOptinal.isPresent()) {
			User user;
			Account account = accountOptinal.get();
			Collection<? extends GrantedAuthority> grantedAuthorities = getAuthorities(account.getAccountsRoles());
			user = new User(account.getUsername(),account.getPassword(),grantedAuthorities);
			return user;
		}else {
			System.out.println("Account not found! " + username);
			throw new UsernameNotFoundException("Username not found!");
		}
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(Collection<AccountsRole> accountsRoles) {

		return getGrantedAuthorities(getRoleNames(accountsRoles));
	}

	/** Lấy danh sách tên role
	 * @param roleUsers
	 * @return
	 */
	private List<String> getRoleNames(Collection<AccountsRole> accountsRoles) {

		List<String> rolenames = new ArrayList<>();
		for (AccountsRole accountsRole : accountsRoles) {
			Role role = accountsRole.getRole();
			String rolename = role.getName();
			rolenames.add(rolename);
		}
		return rolenames;
	}

	/** Tạo danh sách GrantedAuthority từ danh sách tên Role của người dùng
	 * @param rolenames
	 * @return
	 */
	private List<GrantedAuthority> getGrantedAuthorities(List<String> rolenames) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String rolename : rolenames) {
			authorities.add(new SimpleGrantedAuthority(rolename));
		}
		return authorities;
	}
}
