package com.laptrinhweb.denyweb.controller.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laptrinhweb.denyweb.dto.LoaiSanPhamDTO;
import com.laptrinhweb.denyweb.dto.SanPhamDTO;
import com.laptrinhweb.denyweb.entity.Cart;
import com.laptrinhweb.denyweb.entity.SanPhamEntity;
import com.laptrinhweb.denyweb.entity.TaiKhoanEntity;
import com.laptrinhweb.denyweb.service.ILoaiSanPhamService;
import com.laptrinhweb.denyweb.service.ISanPhamService;
import com.laptrinhweb.denyweb.service.ITaiKhoanService;

@Controller(value = "sanPhamControllerOfWeb")
public class SanPhamController {
	
	@Autowired
	private ISanPhamService sanPhamService;
	
	@Autowired
	private ILoaiSanPhamService loaiSanPhamService;
	
	@Autowired
	private ITaiKhoanService taiKhoanService;
	
	@GetMapping("/san-pham")
	public String product(@RequestParam(value = "id", required = false) Long id, Model model, Principal principal) {
		SanPhamDTO dto = new SanPhamDTO();
		if(id!=null) {
			dto = sanPhamService.findById(id);
		}
		if(principal != null) {
			TaiKhoanEntity taiKhoanEntity = taiKhoanService.findByUserName(principal.getName());
			Cart cart = taiKhoanEntity.getCart();
			model.addAttribute("fullName", taiKhoanEntity.getHoTen());
			if(cart != null) {
				model.addAttribute("totalItems", cart.getTotalItems());
			}else {
				model.addAttribute("totalItems", "0");
			}
		}else {
			model.addAttribute("totalItems", "0");
		}
		List<LoaiSanPhamDTO> listType = loaiSanPhamService.findAll();
		model.addAttribute("model", dto);
		model.addAttribute("listType", listType);
		
		return "web/product";
	}
	
	@GetMapping("/danh-sach")
	public String listProduct(@RequestParam(value = "id") Long id, Model model, Principal principal) {
		List<SanPhamDTO> list = sanPhamService.findByLoaiSanPham(id);
		List<LoaiSanPhamDTO> listType = loaiSanPhamService.findAll();
		if(principal != null) {
			TaiKhoanEntity taiKhoanEntity = taiKhoanService.findByUserName(principal.getName());
			Cart cart = taiKhoanEntity.getCart();
			model.addAttribute("fullName", taiKhoanEntity.getHoTen());
			if(cart != null) {
				model.addAttribute("totalItems", cart.getTotalItems());
			}else {
				model.addAttribute("totalItems", "0");
			}
		}else {
			model.addAttribute("totalItems", "0");
		}
		model.addAttribute("list", list);
		model.addAttribute("listType", listType);
		return "web/categories";
	}
	
	@GetMapping("/danh-sach/tim-kiem")
	public String searchProducts(@RequestParam("keyword") String keyword, Model model, Principal principal) {
		if(keyword == "") {
			return "redirect:/";
		}
		List<SanPhamEntity> list = sanPhamService.searchProducts(keyword);
		List<LoaiSanPhamDTO> listType = loaiSanPhamService.findAll();
		if(principal != null) {
			TaiKhoanEntity taiKhoanEntity = taiKhoanService.findByUserName(principal.getName());
			Cart cart = taiKhoanEntity.getCart();
			model.addAttribute("fullName", taiKhoanEntity.getHoTen());
			if(cart != null) {
				model.addAttribute("totalItems", cart.getTotalItems());
			}else {
				model.addAttribute("totalItems", "0");
			}
		}else {
			model.addAttribute("totalItems", "0");
		}
		if(list.size()==0) {
			model.addAttribute("check", "Không có sản phẩm bạn cần tìm.");
		}
		model.addAttribute("list", list);
		model.addAttribute("listType", listType);
		return "web/search-categories";
	}
}
