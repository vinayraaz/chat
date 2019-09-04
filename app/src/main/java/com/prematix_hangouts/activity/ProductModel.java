package com.prematix_hangouts.activity;

public class ProductModel {
    public Integer productImage;
    public String productName;

    public ProductModel(Integer productImage, String productName) {
        this.productImage = productImage;
        this.productName = productName;
    }

    public Integer getProductImage() {
        return productImage;
    }

    public void setProductImage(Integer productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
