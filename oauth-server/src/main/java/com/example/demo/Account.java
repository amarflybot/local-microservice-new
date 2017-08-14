package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by amarendrakumar on 14/08/17.
 */
@Entity
class Account {

	@Id
	@GeneratedValue
	private Long id;

	private String username, password;

	private boolean active;

	Account() {// why JPA why??
	}

	public Account(String username, String password, boolean active) {
		this.username = username;
		this.password = password;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isActive() {
		return active;
	}
}
