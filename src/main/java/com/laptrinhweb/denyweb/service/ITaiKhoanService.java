package com.laptrinhweb.denyweb.service;

import java.util.List;

import com.laptrinhweb.denyweb.dto.TaiKhoanDTO;
import com.laptrinhweb.denyweb.entity.TaiKhoanEntity;

public interface ITaiKhoanService {
	TaiKhoanDTO save(TaiKhoanDTO taiKhoanDTO);
	List<TaiKhoanDTO> findAll();
	TaiKhoanEntity findByUserName(String username);
	TaiKhoanEntity changePassword(String username, String password);
	
	List<TaiKhoanEntity> findAllActive();
	List<TaiKhoanEntity> findAllNotActive();
	
	TaiKhoanEntity updateAdmin(Long id);
	TaiKhoanEntity deleteCustomor(Long id);
	TaiKhoanEntity restoreCustomor(Long id);
}
