package com.laptrinhweb.denyweb.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhweb.denyweb.dto.LoaiSanPhamDTO;
import com.laptrinhweb.denyweb.service.ILoaiSanPhamService;

@Controller
public class LoaiSanPhamController {

	@Autowired
	private ILoaiSanPhamService loaiSanPhamService;

	@GetMapping("/quan-tri/loai-san-pham/danh-sach")
	public String getList(ModelMap model) {
		List<LoaiSanPhamDTO> list = loaiSanPhamService.findAll();
		model.addAttribute("list", list);

		return "admin/loaisanpham/list";
	}
	
	@GetMapping("/quan-tri/loai-san-pham/chinh-sua")
	public ModelAndView editLoaiSanPham(@RequestParam(value = "id", required = false) Long id, Model model) {
		ModelAndView mav = new ModelAndView("admin/loaisanpham/edit");
		LoaiSanPhamDTO modeldto = new LoaiSanPhamDTO();
		if(id != null) {
			modeldto = loaiSanPhamService.findById(id);
		}
		model.addAttribute("model", modeldto);
		
		return mav;
	}
	
}
