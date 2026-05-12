package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 顧客ID

	private String name; // 名前

	private String address; // 住所

	private String password; // パスワード

	private String email; // メールアドレス

	
	
	// コンストラクタ
	public Account() {
	}

	public Account(String name, String address, String password, String email) {
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

	public String getTel() {
		return password;
	}

	public String getEmail() {
		return email;
	}
}
