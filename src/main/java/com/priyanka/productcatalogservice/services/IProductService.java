package com.priyanka.productcatalogservice.services;

import com.priyanka.productcatalogservice.exceptions.ProductNotExistException;
import com.priyanka.productcatalogservice.models.Product;

import java.util.List;

public interface IProductService  {
    List<Product> getAllProducts();
    Product getProductById(Long id) throws ProductNotExistException;
    Product createProduct(Product product);
    Product replaceProduct( Product newProduct, Long id);
}
