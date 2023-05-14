package com.laptrinhweb.denyweb.controller.admin;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laptrinhweb.denyweb.entity.DonHangEntity;
import com.laptrinhweb.denyweb.service.IDonHangService;

@Controller(value = "donHangControllerOfAdmin")
public class DonHangController {
	
	@Autowired
	private IDonHangService donHangService;
	
	
	@GetMapping("/quan-tri/don-hang/danh-sach")
	public String getAll(Principal principal, Model model) {
		if(principal == null) {
			return "redirect:/dang-nhap";
		}
		List<DonHangEntity> orders = donHangService.findAllTypeNot();
		List<DonHangEntity> orders2 = donHangService.findAllTypeOk();
		model.addAttribute("orders", orders);
		model.addAttribute("orders2", orders2);
		
		return "/admin/donhang/list";
	}
	
	@PostMapping("/quan-tri/don-hang/danh-sach")
	public String updateOrder(@RequestParam("id") Long id, Principal principal) {
		if(principal == null) {
			return "redirect:/dang-nhap";
		}
		donHangService.updateOrder(id);
		return "redirect:/quan-tri/don-hang/danh-sach";
	}
}
