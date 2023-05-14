package com.laptrinhweb.denyweb.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhweb.denyweb.entity.Cart;
import com.laptrinhweb.denyweb.entity.CartItem;
import com.laptrinhweb.denyweb.entity.SanPhamEntity;
import com.laptrinhweb.denyweb.entity.TaiKhoanEntity;
import com.laptrinhweb.denyweb.repository.CartItemRepository;
import com.laptrinhweb.denyweb.repository.CartRepository;
import com.laptrinhweb.denyweb.service.ICartService;

@Service
public class CartService implements ICartService {

	@Autowired
	private CartItemRepository itemRepository;

	@Autowired
	private CartRepository cartRepository;

	@Override
	public Cart addItemToCart(SanPhamEntity sanPhamEntity, int quantity, TaiKhoanEntity taiKhoanEntity) {
		Cart cart = taiKhoanEntity.getCart(); // lấy giỏ hàng từ tài khoản đăng nhập
		if (cart == null) {
			cart = new Cart();
		}
		Set<CartItem> cartItems = cart.getCartItems(); // lấy danh sách sản phẩm trong giỏ hàng
		CartItem cartItem = findCartItem(cartItems, sanPhamEntity.getId());
		//System.out.println(taiKhoanEntity.getId());
		if (cartItems == null) {
			cartItems = new HashSet<>();
			if (cartItem == null) {
				cartItem = new CartItem();
				cartItem.setSanPham(sanPhamEntity);
				cartItem.setTotalPrice(quantity * sanPhamEntity.getGiaBan());
				cartItem.setQuantity(quantity);
				cartItem.setCart(cart);
				cartItems.add(cartItem);
				itemRepository.save(cartItem);
			}
		} else {
			if (cartItem == null) {
				cartItem = new CartItem();
				cartItem.setSanPham(sanPhamEntity);
				cartItem.setTotalPrice(quantity * sanPhamEntity.getGiaBan());
				cartItem.setQuantity(quantity);
				cartItem.setCart(cart);
				cartItems.add(cartItem);
				itemRepository.save(cartItem);
			} else {
				cartItem.setQuantity(cartItem.getQuantity() + quantity);
				cartItem.setTotalPrice(cartItem.getTotalPrice() + (quantity * sanPhamEntity.getGiaBan()));
				itemRepository.save(cartItem);
			}
		}
		cart.setCartItems(cartItems);
		
		int totalItems = totalItems(cart.getCartItems());
		double totalPrice = totalPrice(cart.getCartItems());
		
		cart.setTotalPrice(totalPrice);
		cart.setTotalItems(totalItems);
		cart.setTaiKhoan(taiKhoanEntity);
		
		return cartRepository.save(cart);
	}
	
	@Override
	public Cart updateItemInCart(SanPhamEntity sanPhamEntity, int quantity, TaiKhoanEntity taiKhoanEntity) {
		Cart cart = taiKhoanEntity.getCart();
		
		Set<CartItem> cartItems = cart.getCartItems();
		
		CartItem item = findCartItem(cartItems, sanPhamEntity.getId());
		item.setQuantity(quantity);
		item.setTotalPrice(quantity * sanPhamEntity.getGiaBan());
		itemRepository.save(item);
		
		int totalItems = totalItems(cartItems);
		double totalPrices = totalPrice(cartItems);
		cart.setTotalItems(totalItems);
		cart.setTotalPrice(totalPrices);
		
		return cartRepository.save(cart);
	}

	@Override
	public Cart deleteItemFromCart(SanPhamEntity sanPhamEntity, TaiKhoanEntity taiKhoanEntity) {
		Cart cart = taiKhoanEntity.getCart();
		
		Set<CartItem> cartItems = cart.getCartItems();
		
		CartItem item = findCartItem(cartItems, sanPhamEntity.getId());
		
		cartItems.remove(item);
		
		itemRepository.delete(item);
		
		int totalItems = totalItems(cartItems);
		double totalPrices = totalPrice(cartItems);
		
		cart.setCartItems(cartItems);
		cart.setTotalItems(totalItems);
		cart.setTotalPrice(totalPrices);
		
		return cartRepository.save(cart);
	}

	private CartItem findCartItem(Set<CartItem> cartItems, Long sanPhamId) {
		if (cartItems == null) {
			return null;
		}
		CartItem cartItem = null;
		for (CartItem item : cartItems) {
			if (item.getSanPham().getId() == sanPhamId) {
				cartItem = item;
			}
		}
		return cartItem;
	}
	
	private int totalItems(Set<CartItem> cartItems) {
		int totalItems = 0;
		for(CartItem item : cartItems) {
			totalItems += item.getQuantity();
		}
		return totalItems;
	}
	
	private double totalPrice(Set<CartItem> cartItems) {
		double totalPrice = 0.0;
		for(CartItem item : cartItems) {
			totalPrice += item.getTotalPrice();
		}
		return totalPrice;
	}
	

}
