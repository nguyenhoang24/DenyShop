package com.laptrinhweb.denyweb.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.laptrinhweb.denyweb.dto.SanPhamDTO;
import com.laptrinhweb.denyweb.entity.SanPhamEntity;

public interface ISanPhamService {
	List<SanPhamDTO> findAll();
	List<SanPhamDTO> findItem();
	List<SanPhamDTO> findByLoaiSanPham(long id);
	SanPhamDTO findById(long id);
	SanPhamDTO save(SanPhamDTO sanPhamDTO);
	void delete(long[] ids);
	SanPhamEntity findByIdInCart(long id);
	
	Page<SanPhamEntity> pageProducts(int pageNo);
	Page<SanPhamEntity> searchProducts(int pageNo, String keyword);
	List<SanPhamEntity> searchProducts(String keyword);
}
