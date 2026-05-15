package com.example.demo.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 注文ID

	@Column(name = "user_id")
	private Integer userId; // 顧客ID

	@Column(name = "total_price")
	private Integer totalPrice; // 合計金額

	// コンストラクタ
	public Order() {
	}

	public Order(Integer userId, Integer totalPrice) {
		this.userId = userId;
		this.totalPrice = totalPrice;
	}

	// ゲッター
	public Integer getId() {
		return id;
	}

	public Integer getUserId() {
		return userId;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}
}
