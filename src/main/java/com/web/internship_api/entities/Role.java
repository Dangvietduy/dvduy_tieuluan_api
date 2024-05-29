package com.web.internship_api.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private byte deleted;

	private String name;

	//bi-directional many-to-one association to AccountsRole
	@OneToMany(mappedBy="role")
	@JsonIgnore
	private List<AccountsRole> accountsRoles;

	public Role() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getDeleted() {
		return this.deleted;
	}

	public void setDeleted(byte deleted) {
		this.deleted = deleted;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AccountsRole> getAccountsRoles() {
		return this.accountsRoles;
	}

	public void setAccountsRoles(List<AccountsRole> accountsRoles) {
		this.accountsRoles = accountsRoles;
	}

	public AccountsRole addAccountsRole(AccountsRole accountsRole) {
		getAccountsRoles().add(accountsRole);
		accountsRole.setRole(this);

		return accountsRole;
	}

	public AccountsRole removeAccountsRole(AccountsRole accountsRole) {
		getAccountsRoles().remove(accountsRole);
		accountsRole.setRole(null);

		return accountsRole;
	}

}