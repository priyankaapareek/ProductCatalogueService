package com.priyanka.productcatalogservice.models;

import com.priyanka.productcatalogservice.dtos.CategoryDTO;
import com.priyanka.productcatalogservice.dtos.ExternalProductDTO;
import com.priyanka.productcatalogservice.dtos.ProductDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Product extends BaseModel {
    private String name;
    private String description;
    private Double price;
    private String imageUrl;

    @ManyToOne (cascade = CascadeType.ALL)
    private Category category;


    public ProductDTO toProductDto(){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(this.name);
        productDTO.setDescription(this.description);
        productDTO.setPrice(this.price);
        productDTO.setImageUrl(this.imageUrl);

        if (this.category != null) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setName(this.category.getName());
            categoryDTO.setDescription(this.category.getDescription());
            productDTO.setCategoryDTO(categoryDTO);
        }

        return productDTO;
    }
    public ExternalProductDTO toExternalProductDto(){
        ExternalProductDTO externalProductDTO = new ExternalProductDTO();
        externalProductDTO.setId(this.getId());
        externalProductDTO.setTitle(this.name);
        externalProductDTO.setDescription(this.description);
        externalProductDTO.setPrice(this.price);
        externalProductDTO.setImage(imageUrl);
        externalProductDTO.setCategory(this.category.getName());
        return externalProductDTO;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
