package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // ユーザーID

	private String name; // 名前

	private String address; // 住所

	private String password; // パスワード

	private String email; // メールアドレス

	// コンストラクタ
	public User() {
	}

	public User(String name, String address, String password, String email) {
		this.name = name;
		this.address = address;
		this.password = password;
		this.email = email;
	}

	// ゲッター
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}

	// セッター
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
