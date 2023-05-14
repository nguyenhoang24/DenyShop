package com.laptrinhweb.denyweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "taikhoan")
public class TaiKhoanEntity extends BaseEntity {
	
	@Column(name = "tendangnhap")
	private String tenDangNhap;
	
	@Column(name = "matkhau")
	private String matKhau;
	
	@Column(name="hoten")
	private String hoTen;
	
	@Column(name="trangthai")
	private Integer trangThai;

	@ManyToMany()
	    @JoinTable(name = "taikhoan_vaitro",
	        joinColumns = @JoinColumn(name = "taikhoanid"),
	        inverseJoinColumns = @JoinColumn(name = "vaitroid")
	    )
	private List<VaiTroEntity> vaiTro = new ArrayList<>();
	
	@OneToMany(mappedBy = "taiKhoan", cascade = CascadeType.ALL)
	private List<DonHangEntity> listDonHang = new ArrayList<>();
	
	@OneToOne(mappedBy = "taiKhoan")
    private Cart cart;

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

	public List<VaiTroEntity> getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(List<VaiTroEntity> vaiTro) {
		this.vaiTro = vaiTro;
	}

	public List<DonHangEntity> getListDonHang() {
		return listDonHang;
	}

	public void setListDonHang(List<DonHangEntity> listDonHang) {
		this.listDonHang = listDonHang;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	
	
}
