package com.laptrinhweb.denyweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "chitietdonhang")
public class ChiTietDonHangEntity extends BaseEntity{
	
	@Column(name = "soluong")
	private Integer soLuong;
	
	@Column(name = "giaban")
	private Double giaBan;
	
	@ManyToOne()
    private SanPhamEntity sanPham;
	
	@ManyToOne()
    private DonHangEntity donHang;

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

	public SanPhamEntity getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPhamEntity sanPham) {
		this.sanPham = sanPham;
	}

	public DonHangEntity getDonHang() {
		return donHang;
	}

	public void setDonHang(DonHangEntity donHang) {
		this.donHang = donHang;
	}
	
}
