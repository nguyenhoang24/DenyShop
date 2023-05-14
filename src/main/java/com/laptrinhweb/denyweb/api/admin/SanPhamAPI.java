package com.laptrinhweb.denyweb.api.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.laptrinhweb.denyweb.dto.SanPhamDTO;
import com.laptrinhweb.denyweb.service.ISanPhamService;

@RestController(value = "sanPhamAPIOfAdmin")
public class SanPhamAPI {
	
	@Autowired
	private ISanPhamService sanPhamService;
	
	@PostMapping("/api/san-pham")
	public SanPhamDTO createSanPham(@RequestBody SanPhamDTO sanPhamDTO) {
		return sanPhamService.save(sanPhamDTO);
	}
	
	@PutMapping("/api/san-pham")
	public SanPhamDTO updateSanPham(@RequestBody SanPhamDTO sanPhamDTO) {
		return sanPhamService.save(sanPhamDTO);
	}
	
	@PostMapping("/api/upload")
	public String uploadImage(@RequestParam("img") MultipartFile img) {
		String uploadDir = "uploads/";
	    File dir = new File(uploadDir);
	    if (!dir.exists()) {
	        dir.mkdirs();
	    }
	    String fileName = img.getOriginalFilename();
	    Path path = Paths.get(uploadDir + fileName);
	    try {
	        Files.write(path, img.getBytes());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return "Upload successful!";
	}
	
	@DeleteMapping("/api/san-pham")
	public void deleteSanPham(@RequestBody long[] ids) {
		sanPhamService.delete(ids);
	}
}
