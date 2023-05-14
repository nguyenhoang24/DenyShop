package com.laptrinhweb.denyweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhweb.denyweb.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
