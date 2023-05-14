package com.laptrinhweb.denyweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhweb.denyweb.entity.VaiTroEntity;

public interface VaiTroRepository extends JpaRepository<VaiTroEntity, Long> {
	List<VaiTroEntity> findOneByLoaiVaiTro(String loaiVaiTro);
}
