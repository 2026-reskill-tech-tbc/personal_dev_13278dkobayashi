package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "items")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 商品ID
	private String name; // 商品名
	private Integer price; // 価格
	private String image; //画像
	@Column(name="genre")
	private Integer genreId; // ジャンルID
	@Transient // 永続化対象外
	private Integer quantity; // 数量
	

	// ゲッター
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public Integer getPrice() {
		return price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public String getImage() {
		return image;
	}
	public Integer getGenreId() {
		return genreId;
	}
	
	
	// セッター
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public void setPrice(Integer price) {
		this.price = price;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}
	
}
