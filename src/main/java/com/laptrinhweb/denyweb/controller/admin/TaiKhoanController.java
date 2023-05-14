package com.laptrinhweb.denyweb.controller.admin;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laptrinhweb.denyweb.entity.TaiKhoanEntity;
import com.laptrinhweb.denyweb.service.ITaiKhoanService;

@Controller
public class TaiKhoanController {
	
	@Autowired
	private ITaiKhoanService taiKhoanService;
	
	@GetMapping("/quan-tri/tai-khoan/danh-sach")
	public String getAll(Principal principal, Model model) {
		if(principal == null) {
			return "redirect:/dang-nhap";
		}
		List<TaiKhoanEntity> listActive = taiKhoanService.findAllActive();
		List<TaiKhoanEntity> listNotActive = taiKhoanService.findAllNotActive();
		model.addAttribute("listActive", listActive);
		model.addAttribute("listNotActive", listNotActive);
		return "/admin/taikhoan/list";
	}
	
	@PostMapping(value = "/quan-tri/tai-khoan/danh-sach", params = "action=update")
	public String updateCustomor(@RequestParam("id") Long id, Principal principal) {
		if(principal == null) {
			return "redirect:/dang-nhap";
		}
		taiKhoanService.updateAdmin(id);
		return "redirect:/quan-tri/tai-khoan/danh-sach";
	}
	
	@PostMapping(value = "/quan-tri/tai-khoan/danh-sach", params = "action=delete")
	public String deleteCustomor(@RequestParam("id") Long id, Principal principal) {
		if(principal == null) {
			return "redirect:/dang-nhap";
		}
		taiKhoanService.deleteCustomor(id);
		return "redirect:/quan-tri/tai-khoan/danh-sach";
	}
	
	@PostMapping(value = "/quan-tri/tai-khoan/danh-sach", params = "action=restore")
	public String restoreCustomor(@RequestParam("id") Long id, Principal principal) {
		if(principal == null) {
			return "redirect:/dang-nhap";
		}
		taiKhoanService.restoreCustomor(id);
		return "redirect:/quan-tri/tai-khoan/danh-sach";
	}
}
