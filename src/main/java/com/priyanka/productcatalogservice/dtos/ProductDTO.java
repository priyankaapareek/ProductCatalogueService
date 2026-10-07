package com.priyanka.productcatalogservice.dtos;

import com.priyanka.productcatalogservice.models.Category;
import com.priyanka.productcatalogservice.models.Product;

public class ProductDTO {
    private String name;
    private String description;
   // private CategoryDTO categoryDTO;
    private Double price;
    private String imageUrl;

    public Product toProduct() {
        Product product = new Product();

        product.setName(this.name);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setImageUrl(this.imageUrl);

//        CategoryDTO categoryDTO = this.categoryDTO;
//        if (this.categoryDTO != null) {
//            Category category = new Category();
//            category.setName(categoryDTO.getName());
//            category.setDescription(categoryDTO.getDescription());
//            product.setCategory(category);
//        }
        return product;
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

//   // public CategoryDTO getCategoryDTO() {
//        return categoryDTO;
//    }

//    public void setCategoryDTO(CategoryDTO categoryDTO) {
//        this.categoryDTO = categoryDTO;
//    }

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
}
