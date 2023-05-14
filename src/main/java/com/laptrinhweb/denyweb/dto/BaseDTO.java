package com.laptrinhweb.denyweb.dto;

import java.util.Date;

public class BaseDTO {
	private Long id;
	private Date ngayTao;
	private Date ngayDoi;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Date getNgayTao() {
		return ngayTao;
	}
	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}
	public Date getNgayDoi() {
		return ngayDoi;
	}
	public void setNgayDoi(Date ngayDoi) {
		this.ngayDoi = ngayDoi;
	}
	
	
}
