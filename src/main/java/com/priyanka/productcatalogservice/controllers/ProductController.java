package com.priyanka.productcatalogservice.controllers;

import com.priyanka.productcatalogservice.dtos.ProductDTO;
import com.priyanka.productcatalogservice.exceptions.ProductNotExistException;
import com.priyanka.productcatalogservice.models.Product;
import com.priyanka.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    @Qualifier("storageProductService")
    private IProductService  productService;

    @GetMapping("/products")
    public List<ProductDTO> getProducts() {

        List <Product> products = productService.getAllProducts();
        List<ProductDTO> productDTOs = new ArrayList<>();  // converting product into productDTOs
        for (Product product : products) {
            productDTOs.add(product.toProductDto());
        }
        return productDTOs;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity <ProductDTO> getProductById(@PathVariable("id") Long id) throws ProductNotExistException {

            Product product = productService.getProductById(id);
            return ResponseEntity.ok(product.toProductDto());
    }

    @PutMapping("/products/{id}")
    public ProductDTO replaceProduct(@PathVariable("id") Long productId ,
                                     @RequestBody ProductDTO productDTO) {

        // Convert ProductDTO to product
        Product product = productDTO.toProduct();

        // Call service layer to replace the product
        Product response = productService.replaceProduct(product, productId);

        // return result
        return response.toProductDto();
    }
    @PostMapping("/products")
    public ProductDTO createProduct (@RequestBody ProductDTO productDTO) {
        Product product = productDTO.toProduct();
        return productService.createProduct(product).toProductDto();
    }
}
