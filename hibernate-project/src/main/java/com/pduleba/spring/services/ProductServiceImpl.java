package com.pduleba.spring.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pduleba.hibernate.model.ProductModel;
import com.pduleba.spring.dao.ProductDao;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public void saveAll(Collection<ProductModel> products) {
		productDao.saveAll(products);
	}

	@Override
	public List<ProductModel> getAllProducts() {
		return productDao.getAllProducts();
	}

	@Override
	public void removeAll(List<ProductModel> products) {
		productDao.removeAll(products);
	}
	
}
