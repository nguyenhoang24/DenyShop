package com.laptrinhweb.denyweb.dto;

import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDTO extends BaseDTO{

	private String tenDangNhap;
	private String matKhau;
	private String hoTen;
	private Integer trangThai;
	private List<VaiTroDTO> vaiTro = new ArrayList<>();
	private List<DonHangDTO> listDonHang = new ArrayList<>();
	public String getTenDangNhap() {
		return tenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public Integer getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(Integer trangThai) {
		this.trangThai = trangThai;
	}
	public List<VaiTroDTO> getVaiTro() {
		return vaiTro;
	}
	public void setVaiTro(List<VaiTroDTO> vaiTro) {
		this.vaiTro = vaiTro;
	}
	public List<DonHangDTO> getListDonHang() {
		return listDonHang;
	}
	public void setListDonHang(List<DonHangDTO> listDonHang) {
		this.listDonHang = listDonHang;
	}
	
	
}
