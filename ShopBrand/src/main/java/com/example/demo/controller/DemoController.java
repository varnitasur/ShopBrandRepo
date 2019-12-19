package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductDTO;
import com.example.demo.exception.ControllerException;
import com.example.demo.service.ProductService;

/**
 * @author M1055907
 *
 */
@RestController
public class DemoController {
	@Autowired
	ProductService productService;
	
	
	
	@PostMapping("/addProduct")
	public String addProduct(@RequestBody ProductDTO productDto) {
		
		return productService.addProduct(productDto);
	}
	
//	public List<ProductDTO> getProduct(){
//		return productService.getProduct();
//	}
	@GetMapping("/getbyBrand/{brandId}")
	public List<ProductDTO> getbyBrand(@PathVariable int brandId) throws ControllerException{
		return productService.getByBrand(brandId);
		
	}
}
