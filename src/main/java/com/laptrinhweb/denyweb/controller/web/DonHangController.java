package com.laptrinhweb.denyweb.controller.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laptrinhweb.denyweb.entity.TaiKhoanEntity;
import com.laptrinhweb.denyweb.service.IDonHangService;
import com.laptrinhweb.denyweb.service.impl.TaiKhoanService;

@Controller(value = "donHangControllerOfWeb")
public class DonHangController {

	@Autowired
	private TaiKhoanService taiKhoanService;

	@Autowired
	private IDonHangService donHangService;

	@PostMapping("/add-order")
	public String order(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("address") String address,
						Principal principal, Model model) {
		if (principal == null) {
			return "redirect:/dang-nhap";
		}
		if (phoneNumber != "" && address != "") {
			TaiKhoanEntity taiKhoanEntity = taiKhoanService.findByUserName(principal.getName());
			donHangService.addDonHang(taiKhoanEntity, phoneNumber, address);
		}
		return "redirect:/cart";
	}
}
