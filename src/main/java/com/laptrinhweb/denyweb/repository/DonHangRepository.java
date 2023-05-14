package com.laptrinhweb.denyweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhweb.denyweb.entity.DonHangEntity;

public interface DonHangRepository extends JpaRepository<DonHangEntity, Long>{
	List<DonHangEntity> findByTrangThai(Integer trangThai);
}
