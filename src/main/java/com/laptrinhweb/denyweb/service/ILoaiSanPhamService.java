package com.laptrinhweb.denyweb.service;

import java.util.List;

import com.laptrinhweb.denyweb.dto.LoaiSanPhamDTO;

public interface ILoaiSanPhamService {
	List<LoaiSanPhamDTO> findAll();
	LoaiSanPhamDTO findById(long id);
	LoaiSanPhamDTO save(LoaiSanPhamDTO loaiSanPhamDTO);
	void delete(long[] ids);
}
