package com.laptrinhweb.denyweb.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.laptrinhweb.denyweb.entity.TaiKhoanEntity;
import com.laptrinhweb.denyweb.entity.VaiTroEntity;
import com.laptrinhweb.denyweb.repository.TaiKhoanRepository;

@Service
public class CustomerUserDetailService implements UserDetailsService {

	@Autowired
	private TaiKhoanRepository taiKhoanRepository;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TaiKhoanEntity taiKhoanEntity = taiKhoanRepository.findOneByTenDangNhapAndTrangThai(username, 1);

		if (taiKhoanEntity == null) {
			throw new UsernameNotFoundException("User not found");
		}

		List<GrantedAuthority> authorities = new ArrayList<>();

		for (VaiTroEntity vaiTro : taiKhoanEntity.getVaiTro()) {
			authorities.add(new SimpleGrantedAuthority(vaiTro.getLoaiVaiTro()));
		}
		UserDetails userDetails = (UserDetails) new User(taiKhoanEntity.getTenDangNhap(), taiKhoanEntity.getMatKhau(),
				authorities);
		System.out.println(userDetails);
		return userDetails;
	}
}
