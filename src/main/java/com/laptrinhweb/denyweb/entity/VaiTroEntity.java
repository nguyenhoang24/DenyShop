package com.laptrinhweb.denyweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "vaitro")
public class VaiTroEntity extends BaseEntity {
	
	@Column(name = "loaivaitro")
	private String loaiVaiTro;
	
	@Column(name = "tenvaitro")
	private String tenVaiTro;
	
	@ManyToMany(mappedBy = "vaiTro")
    private List<TaiKhoanEntity> taiKhoan = new ArrayList<>();

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

	public List<TaiKhoanEntity> getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(List<TaiKhoanEntity> taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	
}
