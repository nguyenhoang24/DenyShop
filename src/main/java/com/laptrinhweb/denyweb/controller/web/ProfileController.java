package com.laptrinhweb.denyweb.controller.web;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.laptrinhweb.denyweb.dto.LoaiSanPhamDTO;
import com.laptrinhweb.denyweb.entity.Cart;
import com.laptrinhweb.denyweb.entity.CartItem;
import com.laptrinhweb.denyweb.entity.DonHangEntity;
import com.laptrinhweb.denyweb.entity.TaiKhoanEntity;
import com.laptrinhweb.denyweb.service.ILoaiSanPhamService;
import com.laptrinhweb.denyweb.service.impl.TaiKhoanService;

@Controller
public class ProfileController {
	
	@Autowired
	private TaiKhoanService taiKhoanService;
	
	@Autowired
	private ILoaiSanPhamService loaiSanPhamService;
	
	@GetMapping("/profile")
	public String profile(Principal principal, Model model) {
		if (principal == null) {
			return "redirect:/dang-nhap";
		}
		String username = principal.getName();
		TaiKhoanEntity taiKhoanEntity = taiKhoanService.findByUserName(username);
		Cart cart = taiKhoanEntity.getCart();
		//xử lý đơn hàng
		List<DonHangEntity> orders = taiKhoanEntity.getListDonHang();
		
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
		model.addAttribute("listType", listType);
		model.addAttribute("fullName", taiKhoanEntity.getHoTen());
		model.addAttribute("orders", orders);
		model.addAttribute("quantity", orders.size());
		return "/web/profile";
	}
}
