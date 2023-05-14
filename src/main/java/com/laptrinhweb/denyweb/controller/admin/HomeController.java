package com.laptrinhweb.denyweb.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "homeControllerOfAdmin")
public class HomeController {
	@GetMapping({ "/quan-tri/trang-chu" })
	public ModelAndView homePage() {
		ModelAndView modelAndView = new ModelAndView("admin/home");

		return modelAndView;
	}

	@GetMapping("/t")
	public String t() {
		return "redirect:/";
	}
}
