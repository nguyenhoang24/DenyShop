package com.laptrinhweb.denyweb.controller.admin;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhweb.denyweb.dto.SanPhamDTO;
import com.laptrinhweb.denyweb.entity.SanPhamEntity;
import com.laptrinhweb.denyweb.service.impl.LoaiSanPhamService;
import com.laptrinhweb.denyweb.service.impl.SanPhamService;

@Controller(value = "sanPhamControllerOfAdmin")
public class SanPhamController {
	
	@Autowired
	private SanPhamService sanPhamService;
	
	@Autowired
	private LoaiSanPhamService loaiSanPhamService;
	
	@GetMapping("/quan-tri/san-pham/danh-sach")
	public String getList(Model model) {
		List<SanPhamDTO> list = sanPhamService.findAll();
		model.addAttribute("list", list);
		
		return "/admin/sanpham/list";
	}
	
	@GetMapping("/quan-tri/san-pham/danh-sach/{pageNo}")
	private String productsPage(@PathVariable("pageNo") int pageNo, Model model, Principal principal) {
		if(principal == null) {
			return "redirect:/dang-nhap";
		}
		Page<SanPhamEntity> products = sanPhamService.pageProducts(pageNo);
		model.addAttribute("size", products.getSize());
		model.addAttribute("totalPages", products.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("list", products);
		
		return "/admin/sanpham/list";
	}
	
	@GetMapping("/quan-tri/san-pham/tim-kiem/{pageNo}")
	private String searchProdcuts(@PathVariable("pageNo") int pageNo,
								  @RequestParam("keyword") String keyword,
								  Model model,
								  Principal principal) {
		if(principal == null) {
			return "redirect:/dang-nhap";
		}
		Page<SanPhamEntity> products = sanPhamService.searchProducts(pageNo, keyword);
		model.addAttribute("size", products.getSize());
		model.addAttribute("totalPages", products.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("list", products);
		
		return "/admin/sanpham/search-list";
	}
	
	@GetMapping("quan-tri/san-pham/chinh-sua")
	public ModelAndView editSanPham(@RequestParam(value = "id", required = false) Long id, Model model) {
		ModelAndView mav = new ModelAndView("/admin/sanpham/edit");
		SanPhamDTO dto = new SanPhamDTO();
		if(id!=null) {
			dto = sanPhamService.findById(id);
		}
		model.addAttribute("model", dto);
		model.addAttribute("loaiSanPham", loaiSanPhamService.findAll());
		
		return mav;
	}
}
