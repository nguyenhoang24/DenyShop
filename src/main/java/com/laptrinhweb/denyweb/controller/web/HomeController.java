package com.laptrinhweb.denyweb.controller.web;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhweb.denyweb.converter.TaiKhoanConverter;
import com.laptrinhweb.denyweb.dto.LoaiSanPhamDTO;
import com.laptrinhweb.denyweb.dto.SanPhamDTO;
import com.laptrinhweb.denyweb.dto.TaiKhoanDTO;
import com.laptrinhweb.denyweb.entity.Cart;
import com.laptrinhweb.denyweb.entity.CartItem;
import com.laptrinhweb.denyweb.entity.TaiKhoanEntity;
import com.laptrinhweb.denyweb.service.ILoaiSanPhamService;
import com.laptrinhweb.denyweb.service.ISanPhamService;
import com.laptrinhweb.denyweb.service.ITaiKhoanService;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

	@Autowired
	private ISanPhamService sanPhamService;

	@Autowired
	private ILoaiSanPhamService loaiSanPhamService;

	@Autowired
	private ITaiKhoanService taiKhoanService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private TaiKhoanConverter taiKhoanConverter;

	@GetMapping({ "/", "/trang-chu/" })
	public ModelAndView homePage(Model model, Principal principal) {
		ModelAndView modelAndView = new ModelAndView("web/home");
		List<SanPhamDTO> listProduct = sanPhamService.findItem();
		// Page<SanPhamEntity> products = sanPhamService.pageProducts(pageNo);
		List<LoaiSanPhamDTO> listType = loaiSanPhamService.findAll();
		if (principal != null) {
			TaiKhoanEntity taiKhoanEntity = taiKhoanService.findByUserName(principal.getName());
			Cart cart = taiKhoanEntity.getCart();
			model.addAttribute("fullName", taiKhoanEntity.getHoTen());
			if (cart != null) {
				model.addAttribute("totalItems", cart.getTotalItems());
			} else {
				model.addAttribute("totalItems", "0");
			}
		} else {
			model.addAttribute("totalItems", "0");
		}
//		model.addAttribute("size", products.getSize());
//		model.addAttribute("totalPages", products.getTotalPages());
//		model.addAttribute("currentPage", pageNo);
		model.addAttribute("list", listProduct);
		model.addAttribute("listType", listType);
		return modelAndView;
	}

	@RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}

	@RequestMapping(value = "/thoat", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/");
	}

	@GetMapping("/dang-ky")
	public String registerForm(Model model) {
		model.addAttribute("user", new TaiKhoanDTO());
		return "register";
	}

	@PostMapping("/dang-ky")
	public String register(@ModelAttribute("user") TaiKhoanDTO user, @RequestParam("first_name") String first_name,
			@RequestParam("last_name") String last_name, @RequestParam("matKhauNhapLai") String matKhauNhapLai,
			Model model) {
		user.setHoTen(first_name + " " + last_name);
		List<TaiKhoanDTO> list = taiKhoanService.findAll();
		int check = 0;
		for (TaiKhoanDTO item : list) {
			if (item.getTenDangNhap().equals(user.getTenDangNhap())) {
				model.addAttribute("messCheckTenDangNhap", "Tên đăng nhập đã tồn tại!");
				check++;
				break;
			}
		}
		if (check == 0) {
			if (!user.getMatKhau().equals(matKhauNhapLai)) {
				model.addAttribute("messCheckpass", "Mật khẩu nhập lại không khớp!");
				return "register";
			} else {
				user.setMatKhau(passwordEncoder.encode(user.getMatKhau()));
				taiKhoanService.save(user);
				System.out.println(user.getMatKhau());
				return "redirect:/dang-nhap";
			}
		}
		return "register";
	}

	@GetMapping("/change-pass")
	public String changePassForm(Principal principal, Model model) {
		if(principal == null) {
			return "redirect:/dang-nhap";
		}
		List<LoaiSanPhamDTO> listType = loaiSanPhamService.findAll();
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
		model.addAttribute("listType", listType);
		model.addAttribute("fullName", taiKhoanEntity.getHoTen());
		return "changepass";
	}

	@PostMapping("/change-pass")
	public String changePass(@RequestParam("oldPassword") String oldPassword,
							 @RequestParam("newPassword") String newPassword,
							 @RequestParam("reNewPassword") String reNewPassword,
							 Principal principal,
							 Model model) {
		TaiKhoanEntity taiKhoanEntity = taiKhoanService.findByUserName(principal.getName());
		//giao diện
		List<LoaiSanPhamDTO> listType = loaiSanPhamService.findAll();
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
		model.addAttribute("listType", listType);
		model.addAttribute("fullName", taiKhoanEntity.getHoTen());
		
		if(!passwordEncoder.matches(oldPassword, taiKhoanEntity.getMatKhau())) {
			System.out.println(taiKhoanEntity.getMatKhau());
			System.out.println("\n" + passwordEncoder.encode(oldPassword));
			model.addAttribute("checkPass", "Mật khẩu cũ không đúng!");
			return "changepass";
		}
		if(!newPassword.equals(reNewPassword)) {
			model.addAttribute("checkPass", "Mật khẩu nhập lại không đúng!");
			return "changepass";
		}
		taiKhoanService.changePassword(principal.getName(), newPassword);
		model.addAttribute("checkPass", "Đổi mật khẩu thành công!");
		
		return "changepass";
	}
}
