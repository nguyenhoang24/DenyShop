package com.laptrinhweb.denyweb.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class SanPhamDTO extends BaseDTO{

	private String tenSanPham;
	private Double giaNhap;
	private Double giaBan;
	private String img;
	private MultipartFile phoTo;
	private Integer soLuong;
	private String moTaSanPham;
    private String loaiSanPham;
	private List<ChiTietDonHangDTO> listChiTietDonHang = new ArrayList<>();
	public String getTenSanPham() {
		return tenSanPham;
	}
	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}
	public Double getGiaNhap() {
		return giaNhap;
	}
	public void setGiaNhap(Double giaNhap) {
		this.giaNhap = giaNhap;
	}
	public Double getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(Double giaBan) {
		this.giaBan = giaBan;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}
	public String getMoTaSanPham() {
		return moTaSanPham;
	}
	public void setMoTaSanPham(String moTaSanPham) {
		this.moTaSanPham = moTaSanPham;
	}
	
	

	public MultipartFile getPhoTo() {
		return phoTo;
	}
	public void setPhoTo(MultipartFile phoTo) {
		this.phoTo = phoTo;
	}
	public String getLoaiSanPham() {
		return loaiSanPham;
	}
	public void setLoaiSanPham(String loaiSanPham) {
		this.loaiSanPham = loaiSanPham;
	}
	public List<ChiTietDonHangDTO> getListChiTietDonHang() {
		return listChiTietDonHang;
	}
	public void setListChiTietDonHang(List<ChiTietDonHangDTO> listChiTietDonHang) {
		this.listChiTietDonHang = listChiTietDonHang;
	}
	
	
}
