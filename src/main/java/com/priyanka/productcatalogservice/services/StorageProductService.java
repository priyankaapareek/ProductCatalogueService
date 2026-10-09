package com.priyanka.productcatalogservice.services;

import com.priyanka.productcatalogservice.exceptions.ProductNotExistException;
import com.priyanka.productcatalogservice.models.Product;
import com.priyanka.productcatalogservice.models.State;
import com.priyanka.productcatalogservice.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service ("storageProductService")
public class StorageProductService implements IProductService{

    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(Long id) throws ProductNotExistException {
        Optional<Product> optionalProduct = productRepo.findById(id);
        return optionalProduct.orElseThrow(() -> new ProductNotExistException("Product with id " + id + " does not exist"));
    }

//    @Override
//    public Product createProduct(Product product) {
//        Optional<Product> optionalProduct= productRepo.findById(product.getId());
//
//        if(optionalProduct.isEmpty()){
//            return productRepo.save(product);
//        }
//        else{
//            //return exception to duplicate
//            throw new RuntimeException("Product already exists");
//        }
//
//    }
@Override
public Product createProduct(Product product) {
    product.setState(State.ACTIVE);   // optional, see below
    return productRepo.save(product);
}

    @Override
    public Product replaceProduct(Product newProduct, Long id) {
        return null;
    }

    @Override
    public Boolean deleteProductById(Long id) {
       Optional<Product> optionalProduct = productRepo.findById(id);
       if(optionalProduct.isEmpty()){
           return false;
       }
       else{
           Product product = optionalProduct.get();
           product.setState(State.INACTIVE);
           productRepo.save(product);
           return true;
       }
    }
}
