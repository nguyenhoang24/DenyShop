package com.laptrinhweb.denyweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhweb.denyweb.entity.TaiKhoanEntity;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoanEntity, Long>{
	TaiKhoanEntity findOneByTenDangNhapAndTrangThai(String tenDangNhap, int trangThai);
	List<TaiKhoanEntity> findByTrangThai(Integer trangThai);
}
