package com.laptrinhweb.denyweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhweb.denyweb.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

}
