package com.ellocartuser.home.adapterandmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetailedModal {

    @SerializedName("product_id")
    @Expose
    public String productId;
    @SerializedName("category_name")
    @Expose
    public String categoryName;
    @SerializedName("subcategory_name")
    @Expose
    public String subcategoryName;
    @SerializedName("product_name")
    @Expose
    public String productName;
    @SerializedName("product_measure")
    @Expose
    public String productMeasure;
    @SerializedName("product_mrp")
    @Expose
    public String productMrp;
    @SerializedName("product_sale")
    @Expose
    public String productSale;
    @SerializedName("product_discount")
    @Expose
    public Integer productDiscount;
    @SerializedName("product_stock")
    @Expose
    public String productStock;
    @SerializedName("product_description")
    @Expose
    public String productDescription;
    @SerializedName("product_type")
    @Expose
    public String productType;
    @SerializedName("product_var")
    @Expose
    public String productVar;
    @SerializedName("product_images")
    @Expose
    public List<String> productImages = null;
    @SerializedName("product_variables")
    @Expose
    public List<ProductVariables> productVariables = null;
    @SerializedName("product_subs")
    @Expose
    public List<ProductSubs> productSubs = null;

    @SerializedName("product_rating")
    @Expose
    public String product_rating;


    public ProductDetailedModal(String productId, String categoryName, String subcategoryName, String productName, String productMeasure, String productMrp, String productSale, Integer productDiscount, String productStock, String productDescription, String productType, String productVar, List<String> productImages, List<ProductVariables> productVariables, List<ProductSubs> productSubs, String product_rating) {
        this.productId = productId;
        this.categoryName = categoryName;
        this.subcategoryName = subcategoryName;
        this.productName = productName;
        this.productMeasure = productMeasure;
        this.productMrp = productMrp;
        this.productSale = productSale;
        this.productDiscount = productDiscount;
        this.productStock = productStock;
        this.productDescription = productDescription;
        this.productType = productType;
        this.productVar = productVar;
        this.productImages = productImages;
        this.productVariables = productVariables;
        this.productSubs = productSubs;
        this.product_rating = product_rating;
    }

    public String getProduct_rating() {
        return product_rating;
    }

    public void setProduct_rating(String product_rating) {
        this.product_rating = product_rating;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductMeasure() {
        return productMeasure;
    }

    public void setProductMeasure(String productMeasure) {
        this.productMeasure = productMeasure;
    }

    public String getProductMrp() {
        return productMrp;
    }

    public void setProductMrp(String productMrp) {
        this.productMrp = productMrp;
    }

    public String getProductSale() {
        return productSale;
    }

    public void setProductSale(String productSale) {
        this.productSale = productSale;
    }

    public Integer getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(Integer productDiscount) {
        this.productDiscount = productDiscount;
    }

    public String getProductStock() {
        return productStock;
    }

    public void setProductStock(String productStock) {
        this.productStock = productStock;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductVar() {
        return productVar;
    }

    public void setProductVar(String productVar) {
        this.productVar = productVar;
    }

    public List<String> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<String> productImages) {
        this.productImages = productImages;
    }

    public List<ProductVariables> getProductVariables() {
        return productVariables;
    }

    public void setProductVariables(List<ProductVariables> productVariables) {
        this.productVariables = productVariables;
    }

    public List<ProductSubs> getProductSubs() {
        return productSubs;
    }

    public void setProductSubs(List<ProductSubs> productSubs) {
        this.productSubs = productSubs;
    }
}