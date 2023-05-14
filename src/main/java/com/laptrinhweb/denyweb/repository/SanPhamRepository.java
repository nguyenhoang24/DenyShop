package com.laptrinhweb.denyweb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.laptrinhweb.denyweb.entity.SanPhamEntity;

public interface SanPhamRepository extends JpaRepository<SanPhamEntity, Long> {
	
	@Query("select p from SanPhamEntity p")
	Page<SanPhamEntity> pageProduct(Pageable pageable);
	
	@Query("select p from SanPhamEntity p where p.tenSanPham like %?1%")
	Page<SanPhamEntity> searchProducts(String keyword, Pageable pageable);
	
	@Query("select p from SanPhamEntity p where p.tenSanPham like %?1%")
	List<SanPhamEntity> searchProdcts(String keyword);
	
	void deleteByLoaiSanPhamId(long id);
	
	List<SanPhamEntity> findByLoaiSanPhamId(long id);
	
	
}
