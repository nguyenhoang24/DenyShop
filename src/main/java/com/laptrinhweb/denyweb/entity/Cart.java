package com.laptrinhweb.denyweb.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name  =  "cart")
public class Cart extends BaseEntity{
	
	@Column(name = "totalitems")
	private Integer totalItems;
	
	@Column(name = "totalprice")
	private Double totalPrices;
	
	@OneToMany(mappedBy = "cart")
	private Set<CartItem> cartItems;
	
	@OneToOne()
    @JoinColumn(name = "tai_khoan_id", referencedColumnName = "id")
    private TaiKhoanEntity taiKhoan;

	public Integer getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}

	public Double getTotalPrice() {
		return totalPrices;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrices = totalPrice;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public TaiKhoanEntity getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoanEntity taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	
	
	
}
