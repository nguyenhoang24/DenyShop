package com.laptrinhweb.denyweb.dto;

import java.util.ArrayList;
import java.util.List;

public class VaiTroDTO extends BaseDTO{

	private String loaiVaiTro;
	private String tenVaiTro;
    private List<TaiKhoanDTO> taiKhoan = new ArrayList<>();
	public String getLoaiVaiTro() {
		return loaiVaiTro;
	}
	public void setLoaiVaiTro(String loaiVaiTro) {
		this.loaiVaiTro = loaiVaiTro;
	}
	public String getTenVaiTro() {
		return tenVaiTro;
	}
	public void setTenVaiTro(String tenVaiTro) {
		this.tenVaiTro = tenVaiTro;
	}
	public List<TaiKhoanDTO> getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(List<TaiKhoanDTO> taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
    
    
}
