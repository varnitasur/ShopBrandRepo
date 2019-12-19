package com.example.demo.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Brand;
import com.example.demo.entity.Product;
import com.example.demo.entity.Shop;
import com.example.demo.exception.ControllerException;
import com.example.demo.exception.NoSuchBrandIdException;
import com.example.demo.exception.NoSuchProductException;
import com.example.demo.repository.BrandRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ShopRepository;
import com.example.demo.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	BrandRepository brandRepose;
	@Autowired
	ProductRepository productRepose;
	@Autowired
	ShopRepository shopRepose;
	
	
	ModelMapper modelmapper = new ModelMapper();
	
	@Override
	public String addProduct(ProductDTO productDto) {
		int count=0,flag=0;
		Product product= convertDTOtoEntity(productDto);
		Brand brand=product.getBrand();
		List<Brand> brands= brandRepose.findAll();
		if(brand!=null && brands !=null) {
			for (Brand brand2 : brands) {
				if(brand2.getBrandId()==brand.getBrandId()) {
					count=1;
					break;
				}
			}
		}
		if(count==0) {
			brandRepose.save(brand);
			
		}
		Shop shop=product.getShop();
		List<Shop> shops= shopRepose.findAll();
		if(shop!=null && shops !=null) {
			for (Shop shop2 : shops) 
			{
				if(shop2.getShopId()==shop.getShopId()) {
					flag=1;
					break;
				}
			}
		}
		if(flag==0) {
			shopRepose.save(shop);
			
		}
		product.setBrand(brand);
		product.setShop(shop);
		productRepose.save(product);
		return "Success";
	}

	private Product convertDTOtoEntity(ProductDTO productDto) {
		return modelmapper.map(productDto,Product.class);
	}

	private ProductDTO covertEntityToDTO(Product product) {
		return modelmapper.map(product,ProductDTO.class);
	}


	@Override
	public List<ProductDTO> getByBrand(int brandId) throws ControllerException {
	List<Brand> brands= brandRepose.findAll();
	Brand brand=brands.stream().filter(i->i.getBrandId()==brandId).findAny().orElseThrow(()->new NoSuchBrandIdException("No such Brand Id Exist"));
	
	
	List<Product> products= brand.getProducts().stream().filter(i->i.getCost()>100).collect(Collectors.toList());	
	if(products==null)
	{
		throw new NoSuchProductException("No Such Product Found for this brand");
	}
	List<ProductDTO> productDtos=products.stream().map(i-> covertEntityToDTO(i)).collect(Collectors.toList());
	return productDtos;
	}

//	@Override
//	public List<ProductDTO> getProduct() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
