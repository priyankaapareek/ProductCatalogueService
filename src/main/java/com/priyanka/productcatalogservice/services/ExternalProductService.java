package com.priyanka.productcatalogservice.services;

import com.priyanka.productcatalogservice.clients.ExternalAPIClient;
import com.priyanka.productcatalogservice.dtos.ExternalProductDTO;
import com.priyanka.productcatalogservice.exceptions.ProductNotExistException;
import com.priyanka.productcatalogservice.models.Product;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("externalProductService")
public class ExternalProductService implements IProductService {

    @Autowired
    private ExternalAPIClient externalAPIClient;

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
      ResponseEntity<ExternalProductDTO[]> externalProductDtos = externalAPIClient.requestForEntity(
              HttpMethod.GET,
              "https://fakestoreapi.com/products/",
              null,
              ExternalProductDTO[].class
       );
       if ( externalProductDtos != null  && externalProductDtos.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
           for(ExternalProductDTO externalProductDto : externalProductDtos.getBody()){
               products.add(externalProductDto.toProduct());  // We are converting our FsDTO into a List
           }
           return products;
       }
       return products; // have to convert fsdto[] into list of products
    }

    @Override
    public Product getProductById(Long id) throws ProductNotExistException{

        ResponseEntity<ExternalProductDTO> externalProductDto = externalAPIClient.requestForEntity(
                HttpMethod.GET,
                "https://fakestoreapi.com/products/{id}",
                null,
                ExternalProductDTO.class,
                id
        );
        if(externalProductDto.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            if(externalProductDto.getBody() != null) {
                return externalProductDto.getBody().toProduct();
            }else{
                throw new ProductNotExistException("Product with id "+ id + " does not exist");
            }
        }else {
            throw new ProductNotExistException("Product with id "+ id + " does not exist");
        }

       // return externalProductDto.toProduct(); // convert dto to product
        // have to map externaldto to product with a helper method
    }
    @Override
    public Product replaceProduct (Product newProduct, Long id) {

        ExternalProductDTO dto = newProduct.toExternalProductDto();
        dto.setId(id);

        ResponseEntity<ExternalProductDTO> response = externalAPIClient.requestForEntity(
                HttpMethod.PUT,
                "https://fakestoreapi.com/products/{id}",
                dto,
                 ExternalProductDTO.class,
                 id
        );
        if(response.getBody() != null &&  response.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            return response.getBody().toProduct();
        }
        return null;
    }

    @Override
    public Boolean deleteProductById(Long id) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }
}
