package com.laptrinhweb.denyweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "loaisanpham")
public class LoaiSanPhamEntity extends BaseEntity {

	@Column(name = "tenloaisanpham")
	private String tenLoaiSanPham;

	@Column(name = "motangan")
	private String moTaNgan;

	@OneToMany(mappedBy = "loaiSanPham")
	private List<SanPhamEntity> listSanPham = new ArrayList<>();

	public String getTenLoaiSanPham() {
		return tenLoaiSanPham;
	}

	public void setTenLoaiSanPham(String tenLoaiSanPham) {
		this.tenLoaiSanPham = tenLoaiSanPham;
	}

	public String getMoTaNgan() {
		return moTaNgan;
	}

	public void setMoTaNgan(String moTaNgan) {
		this.moTaNgan = moTaNgan;
	}

	public List<SanPhamEntity> getListSanPham() {
		return listSanPham;
	}

	public void setListSanPham(List<SanPhamEntity> listSanPham) {
		this.listSanPham = listSanPham;
	}
	
}
