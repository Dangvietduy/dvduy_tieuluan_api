package com.web.internship_api.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private byte deleted;

	@Lob
	private String password;

	private String username;

	//bi-directional many-to-one association to AccountsRole
	@OneToMany(mappedBy="account", fetch = FetchType.EAGER)
	private List<AccountsRole> accountsRoles;

	//bi-directional many-to-one association to Student
	@OneToMany(mappedBy="account")
	private List<Student> students;

	//bi-directional many-to-one association to Teacher
	@OneToMany(mappedBy="account")
	private List<Teacher> teachers;

	public Account() {
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<AccountsRole> getAccountsRoles() {
		return this.accountsRoles;
	}

	public void setAccountsRoles(List<AccountsRole> accountsRoles) {
		this.accountsRoles = accountsRoles;
	}

	public AccountsRole addAccountsRole(AccountsRole accountsRole) {
		getAccountsRoles().add(accountsRole);
		accountsRole.setAccount(this);

		return accountsRole;
	}

	public AccountsRole removeAccountsRole(AccountsRole accountsRole) {
		getAccountsRoles().remove(accountsRole);
		accountsRole.setAccount(null);

		return accountsRole;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student addStudent(Student student) {
		getStudents().add(student);
		student.setAccount(this);

		return student;
	}

	public Student removeStudent(Student student) {
		getStudents().remove(student);
		student.setAccount(null);

		return student;
	}

	public List<Teacher> getTeachers() {
		return this.teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Teacher addTeacher(Teacher teacher) {
		getTeachers().add(teacher);
		teacher.setAccount(this);

		return teacher;
	}

	public Teacher removeTeacher(Teacher teacher) {
		getTeachers().remove(teacher);
		teacher.setAccount(null);

		return teacher;
	}

}