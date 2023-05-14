package com.laptrinhweb.denyweb.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "dọnhang")
public class DonHangEntity extends BaseEntity{
	
	@Column(name = "diachinhanhang")
	private String diaChiNhanHang;
	
	@Column(name = "sodienthoai")
	private String soDienThoai;
	
	@ManyToOne()
    private TaiKhoanEntity taiKhoan;
	
	@OneToMany(mappedBy = "donHang")
	    private List<ChiTietDonHangEntity> listChiTietDonHang = new ArrayList<>();
	
	@Column(name = "tongtien")
	private Double tongTien;
	
	@Column(name="trangthai")
	private Integer trangThai;
	
	@Column(name = "ngaydat")
	private Date ngayDat;

	public String getDiaChiNhanHang() {
		return diaChiNhanHang;
	}

	public void setDiaChiNhanHang(String diaChiNhanHang) {
		this.diaChiNhanHang = diaChiNhanHang;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public TaiKhoanEntity getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoanEntity taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public List<ChiTietDonHangEntity> getListChiTietDonHang() {
		return listChiTietDonHang;
	}

	public void setListChiTietDonHang(List<ChiTietDonHangEntity> listChiTietDonHang) {
		this.listChiTietDonHang = listChiTietDonHang;
	}

	public Double getTongTien() {
		return tongTien;
	}

	public void setTongTien(Double tongTien) {
		this.tongTien = tongTien;
	}

	public Integer getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Integer trangThai) {
		this.trangThai = trangThai;
	}

	public Date getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(Date ngayDat) {
		this.ngayDat = ngayDat;
	}
	
	
}
