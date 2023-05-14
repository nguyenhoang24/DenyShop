package com.laptrinhweb.denyweb.service;

import java.util.List;

import com.laptrinhweb.denyweb.entity.DonHangEntity;
import com.laptrinhweb.denyweb.entity.TaiKhoanEntity;

public interface IDonHangService {
	DonHangEntity addDonHang(TaiKhoanEntity taiKhoanEntity, String soDienThoai, String diaChiNhanHang);
	List<DonHangEntity> findAllTypeNot();
	List<DonHangEntity> findAllTypeOk();
	DonHangEntity updateOrder(Long id);
}
