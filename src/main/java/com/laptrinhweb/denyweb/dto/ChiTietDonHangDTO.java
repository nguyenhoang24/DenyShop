package com.laptrinhweb.denyweb.dto;

public class ChiTietDonHangDTO extends BaseDTO{
	private Integer soLuong;
	private Double giaBan;
	private Double tongTien;
    private SanPhamDTO sanPham;
    private DonHangDTO donHang;
    
	public Integer getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}
	public Double getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(Double giaBan) {
		this.giaBan = giaBan;
	}
	public Double getTongTien() {
		return tongTien;
	}
	public void setTongTien(Double tongTien) {
		this.tongTien = tongTien;
	}
	public SanPhamDTO getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPhamDTO sanPham) {
		this.sanPham = sanPham;
	}
	public DonHangDTO getDonHang() {
		return donHang;
	}
	public void setDonHang(DonHangDTO donHang) {
		this.donHang = donHang;
	}
	
    
    
}
