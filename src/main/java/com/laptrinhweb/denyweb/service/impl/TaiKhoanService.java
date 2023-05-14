package com.laptrinhweb.denyweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.laptrinhweb.denyweb.converter.TaiKhoanConverter;
import com.laptrinhweb.denyweb.dto.TaiKhoanDTO;
import com.laptrinhweb.denyweb.entity.TaiKhoanEntity;
import com.laptrinhweb.denyweb.entity.VaiTroEntity;
import com.laptrinhweb.denyweb.repository.TaiKhoanRepository;
import com.laptrinhweb.denyweb.repository.VaiTroRepository;
import com.laptrinhweb.denyweb.service.ITaiKhoanService;

@Service
public class TaiKhoanService implements ITaiKhoanService{
	
	@Autowired
	private TaiKhoanRepository taiKhoanRepository;
	
	@Autowired
	private VaiTroRepository vaiTroRepository;
	
	@Autowired
	private TaiKhoanConverter taiKhoanConverter;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public TaiKhoanDTO save(TaiKhoanDTO taiKhoanDTO) {
		TaiKhoanEntity entity = taiKhoanConverter.toEntity(taiKhoanDTO);
		entity.setTrangThai(1);
		List<VaiTroEntity> vaiTroEntities = vaiTroRepository.findOneByLoaiVaiTro("ROLE_USER");
		entity.setVaiTro(vaiTroEntities);
		taiKhoanRepository.save(entity);
		
		return taiKhoanConverter.toDto(entity);
	}

	@Override
	public List<TaiKhoanDTO> findAll() {
		List<TaiKhoanEntity> listEntity = taiKhoanRepository.findAll();
		List<TaiKhoanDTO> list = new ArrayList<>();
		for (TaiKhoanEntity item : listEntity) {
			TaiKhoanDTO dto = taiKhoanConverter.toDto(item);
			list.add(dto);
		}
		return list;
	}

	@Override
	public TaiKhoanEntity findByUserName(String username) {
		TaiKhoanEntity taiKhoanEntity = taiKhoanRepository.findOneByTenDangNhapAndTrangThai(username, 1);
		return taiKhoanEntity;
	}

	@Override
	public TaiKhoanEntity changePassword(String username, String password) {
		TaiKhoanEntity taiKhoanEntity = taiKhoanRepository.findOneByTenDangNhapAndTrangThai(username, 1);
		taiKhoanEntity.setMatKhau(passwordEncoder.encode(password));
		
		return taiKhoanRepository.save(taiKhoanEntity);
	}

	@Override
	public List<TaiKhoanEntity> findAllActive() {
		return taiKhoanRepository.findByTrangThai(1);
	}

	@Override
	public List<TaiKhoanEntity> findAllNotActive() {
		return taiKhoanRepository.findByTrangThai(0);
	}

	@Override
	public TaiKhoanEntity updateAdmin(Long id) {
		TaiKhoanEntity taiKhoanEntity = taiKhoanRepository.getOne(id);
		List<VaiTroEntity> vaiTroEntities = vaiTroRepository.findOneByLoaiVaiTro("ROLE_ADMIN");
		taiKhoanEntity.setVaiTro(vaiTroEntities);
		
		return taiKhoanRepository.save(taiKhoanEntity);
	}

	@Override
	public TaiKhoanEntity deleteCustomor(Long id) {
		TaiKhoanEntity taiKhoanEntity = taiKhoanRepository.getOne(id);
		List<VaiTroEntity> vaiTroEntities = vaiTroRepository.findOneByLoaiVaiTro("ROLE_USER");
		taiKhoanEntity.setVaiTro(vaiTroEntities);
		taiKhoanEntity.setTrangThai(0);
		
		return taiKhoanRepository.save(taiKhoanEntity);
	}

	@Override
	public TaiKhoanEntity restoreCustomor(Long id) {
		TaiKhoanEntity taiKhoanEntity = taiKhoanRepository.getOne(id);
		taiKhoanEntity.setTrangThai(1);
		
		return taiKhoanRepository.save(taiKhoanEntity);
	}
	
}
