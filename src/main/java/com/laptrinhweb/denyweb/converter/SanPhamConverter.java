package com.laptrinhweb.denyweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhweb.denyweb.dto.SanPhamDTO;
import com.laptrinhweb.denyweb.entity.SanPhamEntity;

@Component
public class SanPhamConverter {
	
	public SanPhamDTO toDto(SanPhamEntity entity) {
		SanPhamDTO dto = new SanPhamDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setTenSanPham(entity.getTenSanPham());
		dto.setImg(entity.getImg());
		dto.setGiaNhap(entity.getGiaNhap());
		dto.setGiaBan(entity.getGiaBan());
		dto.setSoLuong(entity.getSoLuong());
		dto.setMoTaSanPham(entity.getMoTaSanPham());
		dto.setLoaiSanPham(entity.getLoaiSanPham().getTenLoaiSanPham());
		
		return dto;
	}
	
	public SanPhamEntity toEntity(SanPhamDTO dto) {
		SanPhamEntity entity = new SanPhamEntity();
		entity.setTenSanPham(dto.getTenSanPham());
		entity.setImg(dto.getImg());
		entity.setGiaNhap(dto.getGiaNhap());
		entity.setGiaBan(dto.getGiaBan());
		entity.setSoLuong(dto.getSoLuong());
		entity.setMoTaSanPham(dto.getMoTaSanPham());
		
		return entity;
	}
	
	public SanPhamEntity toEntity(SanPhamEntity entity, SanPhamDTO dto) {
		entity.setTenSanPham(dto.getTenSanPham());
		entity.setImg(dto.getImg());
		entity.setGiaNhap(dto.getGiaNhap());
		entity.setGiaBan(dto.getGiaBan());
		entity.setSoLuong(dto.getSoLuong());
		entity.setMoTaSanPham(dto.getMoTaSanPham());
		
		return entity;
	}
}
