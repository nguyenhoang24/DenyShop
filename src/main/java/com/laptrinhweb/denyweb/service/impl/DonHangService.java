package com.laptrinhweb.denyweb.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhweb.denyweb.entity.Cart;
import com.laptrinhweb.denyweb.entity.CartItem;
import com.laptrinhweb.denyweb.entity.ChiTietDonHangEntity;
import com.laptrinhweb.denyweb.entity.DonHangEntity;
import com.laptrinhweb.denyweb.entity.TaiKhoanEntity;
import com.laptrinhweb.denyweb.repository.CartItemRepository;
import com.laptrinhweb.denyweb.repository.CartRepository;
import com.laptrinhweb.denyweb.repository.ChiTietDonHangRepository;
import com.laptrinhweb.denyweb.repository.DonHangRepository;
import com.laptrinhweb.denyweb.service.IDonHangService;

@Service
public class DonHangService implements IDonHangService{
	
	@Autowired
	private DonHangRepository donHangRepository;
	
	@Autowired
	private ChiTietDonHangRepository chiTietDonHangRepository;
	
	@Autowired 
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository itemRepository;

	@Override
	public DonHangEntity addDonHang(TaiKhoanEntity taiKhoanEntity, String soDienThoai, String diaChiNhanHang) {
		Cart cart = taiKhoanEntity.getCart();
		if(cart != null) {
			DonHangEntity donHang = new DonHangEntity();
			donHang.setTaiKhoan(taiKhoanEntity);
			donHang.setSoDienThoai(soDienThoai);
			donHang.setDiaChiNhanHang(diaChiNhanHang);
			donHang.setTongTien(cart.getTotalPrice());
			donHang.setTrangThai(0);
			donHang.setNgayDat(new Date());
			donHangRepository.save(donHang);
			Set<CartItem> cartItems = cart.getCartItems();
			Set<ChiTietDonHangEntity> listChiTietDonHang = new HashSet<>();
			for (CartItem item : cartItems) {
				ChiTietDonHangEntity chiTietDonHang = new ChiTietDonHangEntity();
				chiTietDonHang.setDonHang(donHang);
				chiTietDonHang.setSanPham(item.getSanPham());
				chiTietDonHang.setSoLuong(item.getQuantity());
				chiTietDonHang.setGiaBan(item.getSanPham().getGiaBan());
				listChiTietDonHang.add(chiTietDonHang);
				chiTietDonHangRepository.save(chiTietDonHang);
				itemRepository.delete(item);
			}
			
			cartRepository.delete(cart);
			return donHang;
		}
		
		return null;
	}

	@Override
	public List<DonHangEntity> findAllTypeNot() {
		return donHangRepository.findByTrangThai(0);
	}

	@Override
	public List<DonHangEntity> findAllTypeOk() {
		return donHangRepository.findByTrangThai(1);
	}

	@Override
	public DonHangEntity updateOrder(Long id) {
		DonHangEntity donHang = donHangRepository.getOne(id);
		donHang.setTrangThai(1);
		
		return donHangRepository.save(donHang);
	}

}
