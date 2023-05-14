package com.laptrinhweb.denyweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhweb.denyweb.converter.LoaiSanPhamConverter;
import com.laptrinhweb.denyweb.dto.LoaiSanPhamDTO;
import com.laptrinhweb.denyweb.entity.LoaiSanPhamEntity;
import com.laptrinhweb.denyweb.repository.LoaiSanPhamRepository;
import com.laptrinhweb.denyweb.repository.SanPhamRepository;
import com.laptrinhweb.denyweb.service.ILoaiSanPhamService;

@Service
public class LoaiSanPhamService implements ILoaiSanPhamService{
	
	@Autowired
	private LoaiSanPhamRepository loaiSanPhamRepository;
	
	@Autowired
	private LoaiSanPhamConverter loaiSanPhamConverter;
	
	@Autowired
	private SanPhamRepository sanPhamRepository;

	@Override
	public List<LoaiSanPhamDTO> findAll() {
		List<LoaiSanPhamEntity> list = loaiSanPhamRepository.findAll();
		List<LoaiSanPhamDTO> listDto = new ArrayList<>();
		for (LoaiSanPhamEntity item : list) {
			LoaiSanPhamDTO dto = loaiSanPhamConverter.toDto(item);
			listDto.add(dto);
		}
		return listDto;
	}

	@SuppressWarnings("deprecation")
	@Override
	public LoaiSanPhamDTO findById(long id) {
		LoaiSanPhamEntity entity = loaiSanPhamRepository.getOne(id);
		
		return loaiSanPhamConverter.toDto(entity);
	}

	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	public LoaiSanPhamDTO save(LoaiSanPhamDTO loaiSanPhamDTO) {
		LoaiSanPhamEntity loaiSanPhamEntity = new LoaiSanPhamEntity();
		if(loaiSanPhamDTO.getId() != null) {
			LoaiSanPhamEntity old = loaiSanPhamRepository.getOne(loaiSanPhamDTO.getId());
			loaiSanPhamEntity = loaiSanPhamConverter.toEntity(old, loaiSanPhamDTO);
		}else {
			loaiSanPhamEntity = loaiSanPhamConverter.toEntity(loaiSanPhamDTO);
		}
		loaiSanPhamEntity = loaiSanPhamRepository.save(loaiSanPhamEntity);
		return loaiSanPhamConverter.toDto(loaiSanPhamEntity);
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for (long id : ids) {
			sanPhamRepository.deleteByLoaiSanPhamId(id);
			loaiSanPhamRepository.deleteById(id);
		}
	}

	
	
}
