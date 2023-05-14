package com.laptrinhweb.denyweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhweb.denyweb.converter.SanPhamConverter;
import com.laptrinhweb.denyweb.dto.SanPhamDTO;
import com.laptrinhweb.denyweb.entity.LoaiSanPhamEntity;
import com.laptrinhweb.denyweb.entity.SanPhamEntity;
import com.laptrinhweb.denyweb.repository.LoaiSanPhamRepository;
import com.laptrinhweb.denyweb.repository.SanPhamRepository;
import com.laptrinhweb.denyweb.service.ISanPhamService;

@Service
public class SanPhamService implements ISanPhamService {
	
	@Autowired
	private SanPhamRepository sanPhamRepository;
	
	@Autowired
	private SanPhamConverter sanPhamConverter;
	
	@Autowired
	private LoaiSanPhamRepository loaiSanPhamRepository;
	
	@Override
	public List<SanPhamDTO> findAll() {
		List<SanPhamEntity> list = sanPhamRepository.findAll();
		List<SanPhamDTO> listDto = new ArrayList<>();
		for (SanPhamEntity item : list) {
			SanPhamDTO dto = sanPhamConverter.toDto(item);
			listDto.add(dto);
		}
		return listDto;
	}
	
	@Override
	public List<SanPhamDTO> findItem() {
		List<SanPhamEntity> list = sanPhamRepository.findAll();
		int count = 0;
		List<SanPhamDTO> listDto = new ArrayList<>();
		for (SanPhamEntity item : list) {
			SanPhamDTO dto = sanPhamConverter.toDto(item);
			if(count == 10) {
				break;
			}
			listDto.add(dto);
			count++;
		}
		return listDto;
	}
	
	@Override
	public List<SanPhamDTO> findByLoaiSanPham(long id) {
		List<SanPhamEntity> list = sanPhamRepository.findByLoaiSanPhamId(id);
		List<SanPhamDTO> listDto = new ArrayList<>();
		for (SanPhamEntity item : list) {
			SanPhamDTO dto = sanPhamConverter.toDto(item);
			listDto.add(dto);
		}
		return listDto;
	}

	@Override
	public SanPhamDTO findById(long id) {
		SanPhamEntity entity = sanPhamRepository.getOne(id);
		
		return sanPhamConverter.toDto(entity);
	}

	@Override
	@Transactional
	public SanPhamDTO save(SanPhamDTO sanPhamDTO) {
		LoaiSanPhamEntity loaiSanPhamEntity = loaiSanPhamRepository.findOneByTenLoaiSanPham(sanPhamDTO.getLoaiSanPham());
		SanPhamEntity entity = new SanPhamEntity();
		String image = sanPhamDTO.getImg();
		if(sanPhamDTO.getId() != null) {
			SanPhamEntity oldEntity = sanPhamRepository.getOne(sanPhamDTO.getId());
			oldEntity.setLoaiSanPham(loaiSanPhamEntity);
			entity = sanPhamConverter.toEntity(oldEntity, sanPhamDTO);
		}else {
			if(sanPhamDTO.getImg() == null) {
				image = "Logo.jpg";
			}
			entity = sanPhamConverter.toEntity(sanPhamDTO);
			entity.setLoaiSanPham(loaiSanPhamEntity);
		}
		entity.setImg(image);
		sanPhamRepository.save(entity);
		return sanPhamConverter.toDto(entity);
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for (long id : ids) {
			sanPhamRepository.deleteById(id);
		}
	}

	@Override
	public SanPhamEntity findByIdInCart(long id) {
		SanPhamEntity entity = sanPhamRepository.getOne(id);
		return entity;
	}

	@Override
	public Page<SanPhamEntity> pageProducts(int pageNo) {
		Pageable pageable = PageRequest.of(pageNo, 5);
		Page<SanPhamEntity> productPages = sanPhamRepository.pageProduct(pageable);
		
		return productPages;
	}

	@Override
	public Page<SanPhamEntity> searchProducts(int pageNo, String keyword) {
		Pageable pageable = PageRequest.of(pageNo, 5);
		Page<SanPhamEntity> prodcuts = sanPhamRepository.searchProducts(keyword, pageable);
		
		return prodcuts;
	}

	@Override
	public List<SanPhamEntity> searchProducts(String keyword) {
		return sanPhamRepository.searchProdcts(keyword);
	}
	
}
