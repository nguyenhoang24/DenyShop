package com.laptrinhweb.denyweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhweb.denyweb.entity.LoaiSanPhamEntity;

public interface LoaiSanPhamRepository extends JpaRepository<LoaiSanPhamEntity, Long>{
	
	LoaiSanPhamEntity findOneByTenLoaiSanPham(String tenLoaiSanPham);


}
