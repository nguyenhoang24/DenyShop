package com.laptrinhweb.denyweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhweb.denyweb.dto.LoaiSanPhamDTO;
import com.laptrinhweb.denyweb.entity.LoaiSanPhamEntity;

@Component
public class LoaiSanPhamConverter {
	
	public LoaiSanPhamDTO toDto(LoaiSanPhamEntity entity) {
		LoaiSanPhamDTO result = new LoaiSanPhamDTO();
		if(entity.getId() != null) {
			result.setId(entity.getId());
		}
		result.setTenLoaiSanPham(entity.getTenLoaiSanPham());
		result.setMoTaNgan(entity.getMoTaNgan());
		
		return result;
	}
	
	public LoaiSanPhamEntity toEntity(LoaiSanPhamDTO dto) {
		LoaiSanPhamEntity result = new LoaiSanPhamEntity();
		result.setTenLoaiSanPham(dto.getTenLoaiSanPham());
		result.setMoTaNgan(dto.getMoTaNgan());
		
		return result;
	}
	
	public LoaiSanPhamEntity toEntity(LoaiSanPhamEntity entity, LoaiSanPhamDTO dto) {
		entity.setTenLoaiSanPham(dto.getTenLoaiSanPham());
		entity.setMoTaNgan(dto.getMoTaNgan());
		
		return entity;
	}
}
