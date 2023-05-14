package com.laptrinhweb.denyweb.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhweb.denyweb.dto.LoaiSanPhamDTO;
import com.laptrinhweb.denyweb.service.ILoaiSanPhamService;

@RestController(value = "loaiSanPhamAPIofAdmin")
public class LoaiSanPhamAPI {

	@Autowired
	private ILoaiSanPhamService loaiSanPhamService;

	@PostMapping("/api/loai-san-pham")
	public LoaiSanPhamDTO createLoaiSanPham(@RequestBody LoaiSanPhamDTO loaiSanPhamDTO) {
		return loaiSanPhamService.save(loaiSanPhamDTO);
	}

	@PutMapping("/api/loai-san-pham")
	public LoaiSanPhamDTO updateLoaiSanPham(@RequestBody LoaiSanPhamDTO loaiSanPhamDTO) {
		return loaiSanPhamService.save(loaiSanPhamDTO);
	}

	@DeleteMapping("/api/loai-san-pham")
	public void deleteLoaiSanPham(@RequestBody long[] ids) {
		loaiSanPhamService.delete(ids);
	}
}
