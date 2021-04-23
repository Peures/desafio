package com.example.demo.repository;

import java.util.List;

import javax.persistence.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Produto;

@Repository
public interface IProductRepository extends JpaRepository<Produto, Long>{
	List<Produto> findByCategory(String category);
	
	List<Produto> findByNameContaining(String name);
}
