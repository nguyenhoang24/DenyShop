package com.laptrinhweb.denyweb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ngaytao")
	private Date ngayTao;
	
	@Column(name = "ngaydoi")
	private Date ngayDoi;

	public Long getId() {
		return id;
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
