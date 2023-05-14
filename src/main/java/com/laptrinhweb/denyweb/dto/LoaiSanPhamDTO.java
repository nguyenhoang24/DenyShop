package com.laptrinhweb.denyweb.dto;

import java.util.ArrayList;
import java.util.List;

public class LoaiSanPhamDTO extends BaseDTO{
	private String tenLoaiSanPham;
	private String moTaNgan;
	private List<SanPhamDTO> listSanPham = new ArrayList<>();
	
	public String getTenLoaiSanPham() {
		return tenLoaiSanPham;
	}
	public void setTenLoaiSanPham(String tenLoaiSanPham) {
		this.tenLoaiSanPham = tenLoaiSanPham;
	}
	public List<SanPhamDTO> getListSanPham() {
		return listSanPham;
	}
	public void setListSanPham(List<SanPhamDTO> listSanPham) {
		this.listSanPham = listSanPham;
	}
	public String getMoTaNgan() {
		return moTaNgan;
	}
	public void setMoTaNgan(String moTaNgan) {
		this.moTaNgan = moTaNgan;
	}
	
	
}
