package com.example.demo.entity;

import java.util.List;


import javax.persistence.*;

@Entity
@Table(name = "produtos", uniqueConstraints = {@UniqueConstraint(columnNames = "code")})
public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "code", length = 10)
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "status")
	private String status;
	
	public Produto() {};

	public Produto(double price, String code, String name, String category, String status) {
		super();
		this.price = price;
		this.code = code;
		this.name = name;
		this.category = category;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + "code=" + code + ", status=" + status + "]";
	}
	
	
}
