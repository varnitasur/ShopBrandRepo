package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.exception.ControllerException;

@Service
public interface ProductService {

	String addProduct(ProductDTO productDto);

	//List<ProductDTO> getProduct();

	List<ProductDTO> getByBrand(int brandId) throws ControllerException;

}
