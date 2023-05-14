package com.laptrinhweb.denyweb.controller.web;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laptrinhweb.denyweb.dto.LoaiSanPhamDTO;
import com.laptrinhweb.denyweb.entity.Cart;
import com.laptrinhweb.denyweb.entity.CartItem;
import com.laptrinhweb.denyweb.entity.SanPhamEntity;
import com.laptrinhweb.denyweb.entity.TaiKhoanEntity;
import com.laptrinhweb.denyweb.service.ILoaiSanPhamService;
import com.laptrinhweb.denyweb.service.impl.CartService;
import com.laptrinhweb.denyweb.service.impl.SanPhamService;
import com.laptrinhweb.denyweb.service.impl.TaiKhoanService;

@Controller
public class CartController {
	
	@Autowired
	private TaiKhoanService taiKhoanService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private SanPhamService sanPhamService;
	
	@Autowired
	private ILoaiSanPhamService loaiSanPhamService;

	@GetMapping("/cart")
	public String cart(Model model, Principal principal) {
		if (principal == null) {
			return "redirect:/dang-nhap";
		}
		String username = principal.getName();
		TaiKhoanEntity taiKhoanEntity = taiKhoanService.findByUserName(username);
		Cart cart = taiKhoanEntity.getCart();
		if(cart == null) {
			model.addAttribute("check", "Không có sản phẩm trong giỏ hàng");
			model.addAttribute("totalItems", "0");
		}else {
			Set<CartItem> listCartItems = cart.getCartItems();
			model.addAttribute("list", listCartItems);
			model.addAttribute("totalItems", cart.getTotalItems());
			if(cart.getTotalItems()==0) {
				model.addAttribute("check", "Không có sản phẩm trong giỏ hàng");
			}
		}
		List<LoaiSanPhamDTO> listType = loaiSanPhamService.findAll();
		model.addAttribute("cart", cart);
		model.addAttribute("listType", listType);
		model.addAttribute("fullName", taiKhoanEntity.getHoTen());
		
		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		model.addAttribute("today", format.format(today));
		
		return "cart";
	}
	
	@PostMapping("/add-to-cart")
	public String addToCart(@RequestParam("id") Long id,
							@RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity,
							Model model,
							Principal principal,
							HttpServletRequest request) {
		if(principal == null) {
			return "redirect:/dang-nhap";
		}
		SanPhamEntity sanPhamEntity = sanPhamService.findByIdInCart(id);
		String username = principal.getName();
		TaiKhoanEntity taiKhoanEntity = taiKhoanService.findByUserName(username);
		
		cartService.addItemToCart(sanPhamEntity, quantity, taiKhoanEntity);
		
		return "redirect:" + request.getHeader("Referer"); 
	}
	
	@PostMapping(value = "/update-cart", params = "action=update")
	public String updateCart(@RequestParam("id") Long id,
							 @RequestParam("quantity") int quantity,
							 Model model,
							 Principal principal) {
		if(principal == null) {
			return "redirect:/dang-nhap";
		}else {
			String username = principal.getName();
			TaiKhoanEntity taiKhoanEntity = taiKhoanService.findByUserName(username);
			SanPhamEntity sanPhamEntity = sanPhamService.findByIdInCart(id);
			Cart cart = cartService.updateItemInCart(sanPhamEntity, quantity, taiKhoanEntity);
			
			model.addAttribute("cart", cart);
			
			return "redirect:/cart";
		}
		
	}
	@PostMapping(value = "/update-cart", params = "action=delete")
	public String deleteCart(@RequestParam("id") Long id,
						 	Model model,
						 	Principal principal) {
		if(principal == null) {
			return "redirect:/dang-nhap";
		}else {
			String username = principal.getName();
			TaiKhoanEntity taiKhoanEntity = taiKhoanService.findByUserName(username);
			SanPhamEntity sanPhamEntity = sanPhamService.findByIdInCart(id);
			Cart cart = cartService.deleteItemFromCart(sanPhamEntity, taiKhoanEntity);
			
			model.addAttribute("cart", cart);
			
			return "redirect:/cart";
		}
	}
}
