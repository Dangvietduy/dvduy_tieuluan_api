package com.web.internship_api.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the accounts_roles database table.
 * 
 */
@Entity
@Table(name="accounts_roles")
@NamedQuery(name="AccountsRole.findAll", query="SELECT a FROM AccountsRole a")
public class AccountsRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private byte deleted;

	//bi-directional many-to-one association to Account
	@ManyToOne
	private Account account;

	//bi-directional many-to-one association to Role
	@ManyToOne
	private Role role;

	public AccountsRole() {
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

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}