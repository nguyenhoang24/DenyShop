package com.laptrinhweb.denyweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name  =  "sanpham")
public class SanPhamEntity extends BaseEntity{
	
	@Column(name = "tensanpham")
	private String tenSanPham;
	
	@Column(name = "gianhap")
	private Double giaNhap;
	
	@Column(name = "giaban")
	private Double giaBan;
	
	@Column(name = "img")
	private String img;
	
	@Column(name = "soluong")
	private Integer soLuong;
	
	@Column(name = "motasanpham")
	private String moTaSanPham;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private LoaiSanPhamEntity loaiSanPham;
	
	@OneToMany(mappedBy = "sanPham")
	private List<ChiTietDonHangEntity> listChiTietDonHang = new ArrayList<>();
	
	@OneToMany(mappedBy = "sanPham")
    private List<CartItem> listCartItems;

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

	public LoaiSanPhamEntity getLoaiSanPham() {
		return loaiSanPham;
	}

	public void setLoaiSanPham(LoaiSanPhamEntity loaiSanPham) {
		this.loaiSanPham = loaiSanPham;
	}

	public List<ChiTietDonHangEntity> getListChiTietDonHang() {
		return listChiTietDonHang;
	}

	public void setListChiTietDonHang(List<ChiTietDonHangEntity> listChiTietDonHang) {
		this.listChiTietDonHang = listChiTietDonHang;
	}

	public List<CartItem> getListCartItems() {
		return listCartItems;
	}

	public void setListCartItems(List<CartItem> listCartItems) {
		this.listCartItems = listCartItems;
	}
	
	
	
	
}
