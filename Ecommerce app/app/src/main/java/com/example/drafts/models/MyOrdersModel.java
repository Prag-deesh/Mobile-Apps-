package com.example.drafts.models;

import java.io.Serializable;

public class MyOrdersModel implements Serializable {

    String productImg, productName , productDesc , productPrice, productType, totalQuantity;

    public MyOrdersModel() {
    }

    public MyOrdersModel(String productimg_url, String productName, String productDesc, String productPrice, String productType, String totalQuantity) {
        this.productImg = productImg;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
        this.productType = productType;
        this.totalQuantity = totalQuantity;
    }

    public String getproductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}