package com.laptrinhweb.denyweb.service;

import com.laptrinhweb.denyweb.entity.Cart;
import com.laptrinhweb.denyweb.entity.SanPhamEntity;
import com.laptrinhweb.denyweb.entity.TaiKhoanEntity;

public interface ICartService {
	Cart addItemToCart(SanPhamEntity sanPhamEntity, int quantity, TaiKhoanEntity taiKhoanEntity);
	Cart updateItemInCart(SanPhamEntity sanPhamEntity, int quantity, TaiKhoanEntity taiKhoanEntity);
	Cart deleteItemFromCart(SanPhamEntity sanPhamEntity, TaiKhoanEntity taiKhoanEntity);
}
