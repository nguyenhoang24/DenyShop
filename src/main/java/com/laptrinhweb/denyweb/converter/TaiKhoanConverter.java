package com.laptrinhweb.denyweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhweb.denyweb.dto.TaiKhoanDTO;
import com.laptrinhweb.denyweb.entity.TaiKhoanEntity;

@Component
public class TaiKhoanConverter {
	
	public TaiKhoanDTO toDto(TaiKhoanEntity entity) {
		TaiKhoanDTO dto = new TaiKhoanDTO();
		dto.setHoTen(entity.getHoTen());
		dto.setTenDangNhap(entity.getTenDangNhap());
		dto.setMatKhau(entity.getMatKhau());
		
		return dto;
	}
	
	public TaiKhoanEntity toEntity(TaiKhoanDTO dto) {
		TaiKhoanEntity entity = new TaiKhoanEntity();
		entity.setHoTen(dto.getHoTen());
		entity.setTenDangNhap(dto.getTenDangNhap());
		entity.setMatKhau(dto.getMatKhau());
		
		return entity;
	}
}
